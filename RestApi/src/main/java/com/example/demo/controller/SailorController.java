package com.example.demo.controller;

import com.example.demo.dto.SailorDto;
import com.example.demo.service.SailorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sailor")
public class SailorController {
    private final SailorService sailorService;

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
        return "redirect:/sailor";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        sailorService.deleteById(id);
        return "redirect:/sailor";
    }

    @PostMapping("")
    public String create(@ModelAttribute SailorDto sailorDto, Model model) {
        sailorService.save(sailorDto);
        return "redirect:/sailor";
    }
}
