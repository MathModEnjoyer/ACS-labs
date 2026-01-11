package com.example.demo.controller;

import com.example.demo.data.Log;
import com.example.demo.dto.BoatDto;
import com.example.demo.dto.SailorDto;
import com.example.demo.enums.ChangeTypeEnum;
import com.example.demo.enums.TablesEnum;
import com.example.demo.jms.Sender;
import com.example.demo.service.BoatService;
import com.example.demo.service.SailorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boat")
public class BoatController {
    private final BoatService boatService;
    private final SailorService sailorService;
    private final Sender sender;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("sailors", sailorService.findAll());
        model.addAttribute("boat", new BoatDto());
        model.addAttribute("boats", boatService.findAll());
        return "all-boats";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("sailors", sailorService.findAll());
        model.addAttribute("boat", boatService.findById(id));
        return "update-boat";
    }

    @PutMapping("/{id}")
    public String updateById(@PathVariable("id") Long id, @ModelAttribute BoatDto boatDto, Model model) {
        boatDto.setId(id);
        SailorDto owner = sailorService.findById(boatDto.getOwner().getId());
        boatDto.setOwner(owner);
        boatService.save(boatDto);
        sender.send(
                Log.builder()
                        .changeType(ChangeTypeEnum.UPDATE)
                        .table(TablesEnum.BOATS)
                        .value(boatDto.toEntity().toString())
                        .datetime(LocalDateTime.now())
                        .build()
        );
        return "redirect:/boat";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        BoatDto boatDto = boatService.findById(id);
        boatService.deleteById(id);
        if (boatDto != null) {
            sender.send(
                    Log.builder()
                            .changeType(ChangeTypeEnum.CASCADE_DELETE)
                            .table(TablesEnum.BOATS)
                            .value(boatDto.toEntity().toString())
                            .datetime(LocalDateTime.now())
                            .build()
            );
        }
        return "redirect:/boat";
    }

    @PostMapping("")
    public String create(@ModelAttribute BoatDto boatDto, Model model) {
        if (boatDto.getOwner() != null && boatDto.getOwner().getId() != null) {
            SailorDto owner = sailorService.findById(boatDto.getOwner().getId());
            boatDto.setOwner(owner);
        }
        boatService.save(boatDto);
        sender.send(
                Log.builder()
                        .changeType(ChangeTypeEnum.INSERT)
                        .table(TablesEnum.BOATS)
                        .value(boatDto.toEntity().toString())
                        .datetime(LocalDateTime.now())
                        .build()
        );
        return "redirect:/boat";
    }
}
