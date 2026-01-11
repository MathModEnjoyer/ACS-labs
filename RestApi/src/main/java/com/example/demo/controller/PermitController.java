package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.BoatService;
import com.example.demo.service.PermitService;
import com.example.demo.service.SailorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/permit")
public class PermitController {
    private final BoatService boatService;
    private final SailorService sailorService;
    private final PermitService permitService;

    @GetMapping("")
    public String getAll(Model model) {
        List<SailorDto> sailorDtoList = sailorService.findAll();
        List<BoatDto> boatDtoList = new ArrayList<>();
        model.addAttribute("permit", new PermitDto());
        model.addAttribute("changePermit", new ChangePermitDto());
        if (!sailorDtoList.isEmpty()) {
            boatDtoList = boatService.findAllByOwner(sailorDtoList.get(0));
            if (!boatDtoList.isEmpty()) {
                List<BerthDto> berthDtos = permitService.getFreeBerths(boatDtoList.get(0));
                if (!berthDtos.isEmpty()) {
                    model.addAttribute("permit", new PermitDto(null, berthDtos.get(0),
                            0.0, false, boatDtoList.get(0)));
                    model.addAttribute("changePermit", new ChangePermitDto(null, berthDtos.get(0).toString(),
                            boatDtoList.get(0)));
                    model.addAttribute("berths", berthDtos);
                }
            }
        }
        model.addAttribute("sailors", sailorDtoList);
        model.addAttribute("sailor", new SailorDto());
        model.addAttribute("permits", permitService.findAll());
        model.addAttribute("boats", boatDtoList);

        return "all-permits";
    }

    @PostMapping("/sailor")
    public String updateAllBySailorChange(@ModelAttribute SailorDto sailorDto, Model model) {
        updateAllBySailor(sailorDto, model);
        return "all-permits";
    }

    @PostMapping("/boat")
    public String updateAllByBoatChange(@ModelAttribute ChangePermitDto changePermitDto, Model model) {
        updateAllByBoat(changePermitDto, model);
        return "all-permits";
    }

    @PostMapping("/update/boat")
    public String updateAllByBoatChangeForUpdate(@ModelAttribute ChangePermitDto changePermitDto, Model model) {
        updateAllByBoat(changePermitDto, model);
        return "update-permit";
    }

    @PostMapping("")
    public String savePermit(@ModelAttribute PermitDto permitDto, Model model) {
        BoatDto boatDto = boatService.findById(permitDto.getBoat().getId());
        permitDto.setBoat(boatDto);
        permitService.save(permitDto);
        return "redirect:/permit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        permitService.deleteById(id);
        return "redirect:/permit";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        PermitDto permitDto = permitService.findById(id);
        model.addAttribute("permit", permitDto);
        List<SailorDto> sailorDtoList = sailorService.findAll();
        model.addAttribute("changePermit", new ChangePermitDto(permitDto.getId(), permitDto.getBerthDto().toString(), permitDto.getBoat()));
        model.addAttribute("sailor", permitDto.getBoat().getOwner());
        List<BoatDto> boatDtoList = boatService.findAllByOwner(sailorService.findById(permitDto.getBoat().getOwner().getId()));
        model.addAttribute("boats", boatDtoList);
        List<BerthDto> berthDtos = new ArrayList<>();
        berthDtos.add(permitDto.getBerthDto());
        berthDtos.addAll(permitService.getFreeBerths(permitDto.getBoat()));
        model.addAttribute("berths", berthDtos);
        return "update-permit";
    }

    @PutMapping("/{id}")
    public String updateById(@PathVariable("id") Long id, @ModelAttribute PermitDto permitDto, Model model) {
        permitDto.setId(id);
        BoatDto boatDto = boatService.findById(permitDto.getBoat().getId());
        permitDto.setBoat(boatDto);
        permitService.save(permitDto);
        return "redirect:/permit";
    }

    private void updateAllByBoat(ChangePermitDto changePermitDto, Model model) {
        String[] berthParams = changePermitDto.getBerthDto().split(";");
        BerthDto berthDto = new BerthDto();
        if (berthParams.length == 2) {
            berthDto = new BerthDto(Integer.parseInt(berthParams[0]), Integer.parseInt(berthParams[1]));
        }
        List<SailorDto> sailorDtoList = sailorService.findAll();
        BoatDto boatDto = boatService.findById(changePermitDto.getBoat().getId());
        List<BoatDto> boatDtoList = boatService.findAllByOwner(sailorService.findById(boatDto.getOwner().getId()));
        model.addAttribute("sailors", sailorDtoList);
        model.addAttribute("sailor", boatDto.getOwner());
        model.addAttribute("permit", new PermitDto(changePermitDto.getId(), berthDto, 0.0, false, boatDto));
        model.addAttribute("permits", permitService.findAll());
        model.addAttribute("boats", boatDtoList);
        model.addAttribute("changePermit", new ChangePermitDto(changePermitDto.getId(), changePermitDto.getBerthDto(), boatDto));
        model.addAttribute("berths", permitService.getFreeBerths(boatDto));
    }

    private void updateAllBySailor(SailorDto sailorDto, Model model) {
        List<SailorDto> sailorDtoList = sailorService.findAll();
        model.addAttribute("permit", new PermitDto());
        model.addAttribute("changePermit", new ChangePermitDto());
        List<BoatDto> boatDtoList = boatService.findAllByOwner(sailorService.findById(sailorDto.getId()));
        if (!boatDtoList.isEmpty()) {
            List<BerthDto> berthDtos = permitService.getFreeBerths(boatDtoList.get(0));
            if (!berthDtos.isEmpty()) {
                model.addAttribute("permit", new PermitDto(null, berthDtos.get(0),
                        0.0, false, boatDtoList.get(0)));
                model.addAttribute("changePermit", new ChangePermitDto(null, berthDtos.get(0).toString(),
                        boatDtoList.get(0)));
                model.addAttribute("berths", berthDtos);
            }
        }
        model.addAttribute("sailors", sailorDtoList);
        model.addAttribute("sailor", sailorDto);
        model.addAttribute("permits", permitService.findAll());
        model.addAttribute("boats", boatDtoList);
    }
}
