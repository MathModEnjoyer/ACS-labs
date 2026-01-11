package com.example.spring.controller;

import com.example.spring.dto.BoatDto;
import com.example.spring.service.BoatService;
import com.example.spring.service.YachtsmanService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/boat")
@RequiredArgsConstructor
public class BoatController {

    private final BoatService boatService;
    private final YachtsmanService yachtsmanService;

    /* -------- GET ------------------------------------------------------ */

    @GetMapping
    public String get(@RequestParam(value = "boatId", required = false) Long id,
                      Model model) {

        if (id == null) {
            model.addAttribute("boats", boatService.getAll());
        } else {
            BoatDto boat = boatService.getById(id);
            model.addAttribute("boat", boat);
            model.addAttribute("crew", yachtsmanService.getByBoatId(id));
        }
        return "index";
    }

    /* -------- POST (create / delete / update) ------------------------- */

    @PostMapping
    public String post(@RequestParam("_action") String action,
                       @RequestParam Map<String, String> p,
                       HttpServletRequest request) {

        switch (action) {
            case "create": {
                BoatDto dto = BoatDto.builder()
                        .name(p.get("name"))
                        .boatClass(p.get("boatClass"))
                        .build();
                Long yId = Long.valueOf(p.get("yachtsmanId"));
                boatService.saveForYachtsman(dto, yId);

                return "redirect:/yachtsman?yachtsmanId=" + yId;
            }

            case "delete": {
                Long boatId = Long.valueOf(p.get("boatId"));
                boatService.delete(boatId);

                String yId = p.get("yachtsmanId");
                return "redirect:" + (yId == null ? "/boat" : "/yachtsman?yachtsmanId=" + yId);
            }

            case "update": {
                Long boatId = Long.valueOf(p.get("boatId"));
                BoatDto dto = BoatDto.builder()
                        .name(p.get("name"))
                        .boatClass(p.get("boatClass"))
                        .build();
                boatService.update(dto, boatId);

                return "redirect:/boat?boatId=" + boatId;
            }

            default:
                throw new IllegalArgumentException("Unknown _action: " + action);
        }
    }
}
