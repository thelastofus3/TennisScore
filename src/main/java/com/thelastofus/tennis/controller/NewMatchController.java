package com.thelastofus.tennis.controller;

import com.thelastofus.tennis.service.NewMatchService;
import com.thelastofus.tennis.service.OngoingMatchesService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private  NewMatchService service;
    private OngoingMatchesService ongoingMatchesService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (NewMatchService) config.getServletContext().getAttribute("newMatchService");
        ongoingMatchesService = (OngoingMatchesService) config.getServletContext().getAttribute("onGoingMatchesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match-jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayer = req.getParameter("first-player");
        String secondPlayer = req.getParameter("second-player");
        UUID matchId = service.startMatch(firstPlayer,secondPlayer);
        if(matchId != null) {
            resp.sendRedirect("/match-score?uuid=" + matchId.toString());
        }else{
            throw new Error("Match is null");
        }
    }
}
