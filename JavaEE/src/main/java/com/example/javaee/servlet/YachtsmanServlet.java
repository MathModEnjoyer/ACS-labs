package com.example.javaee.servlet;

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


@WebServlet(name = "yachtsmanServlet", value = "/yachtsman")
public class YachtsmanServlet extends HttpServlet {

    @Inject private YachtsmanService yachtsmanService;
    @Inject private BoatService      boatService;

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String param = req.getParameter("yachtsmanId");

        if (param == null || param.isBlank()) {     
            req.setAttribute("yachtsmen", yachtsmanService.getAll());
        } else {                                      
            try {
                long id = Long.parseLong(param);
                req.setAttribute("yachtsman", yachtsmanService.getById(id));
                req.setAttribute("boats",      boatService.getByYachtsmanId(id));
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                               "Parameter yachtsmanId must be a number");
                return;
            }
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

   @Override
    protected void doPost(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("_action");

        switch (action == null ? "" : action) {

            case "create":           
                YachtsmanDto dto = YachtsmanDto.builder()
                        .fullName(req.getParameter("fullName"))
                        .club(req.getParameter("club"))
                        .build();
                yachtsmanService.save(dto);   
                resp.sendRedirect(req.getContextPath() + "/yachtsman");
                return;

            case "delete":                
                try {
                    Long id = Long.valueOf(req.getParameter("yachtsmanId"));
                    yachtsmanService.delete(id);
                } catch (NumberFormatException e) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                                "yachtsmanId must be numeric");
                    return;
                }
                resp.sendRedirect(req.getContextPath() + "/yachtsman");
                return;

            default:                          
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "Unknown _action: " + action);
                return;
        }
    }
}
