package com.thelastofus.tennis.controller;

import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.service.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {
    private int page = 1;

    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) config.getServletContext().getAttribute("finishedMatchesPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("page") != null){
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<Match> matches = finishedMatchesPersistenceService.getAllFinishedMatches(page);
        req.setAttribute("page",page);
        if(req.getParameter("filter_by_player_name") != null && !req.getParameter("filter_by_player_name").isEmpty()){
            String playerName = req.getParameter("filter_by_player_name");
            matches = finishedMatchesPersistenceService.findPlayerMatches(page,playerName);
            req.setAttribute("noOfPage",finishedMatchesPersistenceService.getNoOfRecordsForPlayer(playerName));
            req.setAttribute("matches",matches);
            req.setAttribute("filter_by_player_name", playerName);
        }else{
            req.setAttribute("noOfPage",finishedMatchesPersistenceService.getNoOfRecords());
            req.setAttribute("matches",matches);
        }
        req.getRequestDispatcher("/matches-jsp").forward(req,resp);
    }
}
