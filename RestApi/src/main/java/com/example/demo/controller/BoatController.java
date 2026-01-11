package com.example.demo.controller;

import com.example.demo.dto.BoatDto;
import com.example.demo.dto.SailorDto;
import com.example.demo.service.BoatService;
import com.example.demo.service.SailorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boat")
public class BoatController {
    private final BoatService boatService;
    private final SailorService sailorService;

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
        return "redirect:/boat";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        boatService.deleteById(id);
        return "redirect:/boat";
    }

    @PostMapping("")
    public String create(@ModelAttribute BoatDto boatDto, Model model) {
        boatService.save(boatDto);
        return "redirect:/boat";
    }
}
