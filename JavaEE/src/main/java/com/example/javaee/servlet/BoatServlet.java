package com.example.javaee.servlet;

import com.example.javaee.dto.BoatDto;
import com.example.javaee.dto.YachtsmanDto;
import com.example.javaee.service.BoatService;
import com.example.javaee.service.YachtsmanService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "boatServlet", urlPatterns = {"/boat"})
public class BoatServlet extends HttpServlet {

@Inject private BoatService      boatService;
@Inject private YachtsmanService yachtsmanService;


@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    String idParam = req.getParameter("boatId");

    if (idParam == null || idParam.isBlank()) {    
        List<BoatDto> boats = boatService.getAll();
        req.setAttribute("boats", boats);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        return;
    }

    Long boatId = Long.parseLong(idParam);

    BoatDto             boat = boatService.getById(boatId);
    List<YachtsmanDto> crew  = yachtsmanService.getByBoatId(boatId);

    req.setAttribute("boat", boat);
    req.setAttribute("crew", crew);

    req.getRequestDispatcher("/index.jsp").forward(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    String action = req.getParameter("_action");

    if ("create".equals(action)) {
        BoatDto dto = BoatDto.builder()
                .name(req.getParameter("name"))
                .boatClass(req.getParameter("boatClass"))
                .build();

        Long yId = Long.valueOf(req.getParameter("yachtsmanId"));
        boatService.saveForYachtsman(dto, yId);

        resp.sendRedirect(req.getContextPath() + "/yachtsman?yachtsmanId=" + yId);
        return;
    }

    if ("delete".equals(action)) {
        Long boatId = Long.valueOf(req.getParameter("boatId"));
        boatService.delete(boatId);

        String yId = req.getParameter("yachtsmanId");
        String url = yId == null ? "/boat" : "/yachtsman?yachtsmanId=" + yId;
        resp.sendRedirect(req.getContextPath() + url);
        return;
    }
    
    if ("update".equals(action)) {
        Long boatId = Long.valueOf(req.getParameter("boatId"));

        BoatDto dto = BoatDto.builder()
                .name(req.getParameter("name"))
                .boatClass(req.getParameter("boatClass"))
                .build();

        boatService.update(dto, boatId);

        resp.sendRedirect(req.getContextPath() + "/boat?boatId=" + boatId);
        return;
    }

    resp.sendRedirect(req.getContextPath() + "/boat");
}
}