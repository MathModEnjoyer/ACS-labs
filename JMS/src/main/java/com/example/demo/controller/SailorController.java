package com.example.demo.controller;

import com.example.demo.data.Log;
import com.example.demo.dto.SailorDto;
import com.example.demo.enums.ChangeTypeEnum;
import com.example.demo.enums.TablesEnum;
import com.example.demo.jms.Sender;
import com.example.demo.service.SailorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sailor")
public class SailorController {
    private final SailorService sailorService;
    private final Sender sender;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("sailors", sailorService.findAll());
        model.addAttribute("sailor", new SailorDto());
        return "all-sailors";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("sailor", sailorService.findById(id));
        return "update-sailor";
    }

    @PutMapping("/{id}")
    public String updateById(@PathVariable("id") Long id, @ModelAttribute SailorDto sailorDto, Model model) {
        sailorDto.setId(id);
        sailorService.save(sailorDto);
        sender.send(
                Log.builder()
                        .changeType(ChangeTypeEnum.UPDATE)
                        .table(TablesEnum.SAILORS)
                        .value(sailorDto.toEntity().toString())
                        .datetime(LocalDateTime.now())
                        .build()
        );
        return "redirect:/sailor";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        SailorDto sailorDto = sailorService.findById(id);
        sailorService.deleteById(id);
        if (sailorDto != null) {
            sender.send(
                    Log.builder()
                            .changeType(ChangeTypeEnum.CASCADE_DELETE)
                            .table(TablesEnum.SAILORS)
                            .value(sailorDto.toEntity().toString())
                            .datetime(LocalDateTime.now())
                            .build()
            );
        }
        return "redirect:/sailor";
    }

    @PostMapping("")
    public String create(@ModelAttribute SailorDto sailorDto, Model model) {
        sailorService.save(sailorDto);
        sender.send(
                Log.builder()
                        .changeType(ChangeTypeEnum.INSERT)
                        .table(TablesEnum.SAILORS)
                        .value(sailorDto.toEntity().toString())
                        .datetime(LocalDateTime.now())
                        .build()
        );
        return "redirect:/sailor";
    }
}
