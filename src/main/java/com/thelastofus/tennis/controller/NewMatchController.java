package com.thelastofus.tennis.controller;

import com.thelastofus.tennis.dao.PlayerDAO;
import com.thelastofus.tennis.service.NewMatchService;
import com.thelastofus.tennis.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private final NewMatchService service;
    private final OngoingMatchesService ongoingMatchesService;


    public NewMatchController() {
        this.service = new NewMatchService(new PlayerDAO(), new OngoingMatchesService());
        this.ongoingMatchesService = service.getOngoingMatchesService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match-jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayer = req.getParameter("first-player");
        String secondPlayer = req.getParameter("second-player");
        //check valid is name withoud number or sign
        UUID matchId = service.startMatch(firstPlayer,secondPlayer);
        //неправильно работает не могу достать uuid
        if(matchId != null) {
            resp.sendRedirect("/match-score?uuid=" + matchId.toString());
        }else{
            resp.getWriter().println("Error idi naxyi");
        }
    }

}
