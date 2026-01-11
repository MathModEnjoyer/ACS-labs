package com.example.spring.controller;

import com.example.spring.dto.YachtsmanDto;
import com.example.spring.service.BoatService;
import com.example.spring.service.YachtsmanService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/yachtsman")
@RequiredArgsConstructor
public class YachtsmanController {

    private final YachtsmanService yachtsmanService;
    private final BoatService boatService;

    /* -------- GET ------------------------------------------------------ */

    @GetMapping
    public String get(@RequestParam(value = "yachtsmanId", required = false) Long id,
                      Model model) {

        if (id == null) {
            model.addAttribute("yachtsmen", yachtsmanService.getAll());
        } else {
            model.addAttribute("yachtsman", yachtsmanService.getById(id));
            model.addAttribute("boats", boatService.getByYachtsmanId(id));
        }
        return "index";
    }

    /* -------- POST (create / delete) ---------------------------------- */

    @PostMapping
    public String post(@RequestParam("_action") String action,
                       @RequestParam Map<String, String> p,
                       HttpServletRequest request) {

        switch (action) {
            case "create": {
                YachtsmanDto dto = YachtsmanDto.builder()
                        .fullName(p.get("fullName"))
                        .club(p.get("club"))
                        .build();
                yachtsmanService.save(dto);
                return "redirect:/yachtsman";
            }

            case "delete": {
                yachtsmanService.delete(Long.valueOf(p.get("yachtsmanId")));
                return "redirect:/yachtsman";
            }

            default:
                throw new IllegalArgumentException("Unknown _action: " + action);
        }
    }
}
