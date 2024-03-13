package com.thelastofus.tennis.controller;

import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.model.Player;
import com.thelastofus.tennis.service.FinishedMatchesPersistenceService;
import com.thelastofus.tennis.service.MatchScoreCalculationService;
import com.thelastofus.tennis.service.OngoingMatchesService;
import com.thelastofus.tennis.service.score.EPlayer;
import com.thelastofus.tennis.service.score.State;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService ;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private State state;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ongoingMatchesService = (OngoingMatchesService) config.getServletContext().getAttribute("onGoingMatchesService");
        matchScoreCalculationService = (MatchScoreCalculationService) config.getServletContext().getAttribute("matchScoreCalculationService");
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) config.getServletContext().getAttribute("finishedMatchesPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidString);
        Match match = ongoingMatchesService.getMatch(uuid);

        req.setAttribute("match",match);

        req.getRequestDispatcher("/match-score-jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("uuid");
        UUID matchId = UUID.fromString(uuidString);
        Match match = ongoingMatchesService.getMatch(matchId);
        EPlayer ePlayer = getPlayerFromRequest(req);

        state = matchScoreCalculationService.calculateScore(match, ePlayer);

        if (state == State.PLAYER_ONE_WON || state == State.PLAYER_TWO_WON) {
            handleMatchWon(req, resp, state, matchId);
        } else {
            resp.sendRedirect("/match-score?uuid=" + matchId.toString());
        }
    }
    private EPlayer getPlayerFromRequest(HttpServletRequest req) {
        String point = req.getParameter("action");
        return point.equals("Player_1") ? EPlayer.PLAYER_ONE : EPlayer.PLAYER_TWO;
    }

    private void handleMatchWon(HttpServletRequest req, HttpServletResponse resp, State state, UUID matchId) throws IOException {
        Match match = ongoingMatchesService.getMatch(matchId);
        Player winner = (state == State.PLAYER_ONE_WON) ? match.getPlayerOne() : match.getPlayerTwo();
        match.setWinner(winner);

        finishedMatchesPersistenceService.saveFinishedMatch(match);

        req.getSession().setAttribute("match", match);
        resp.sendRedirect("/winner");
        ongoingMatchesService.removeMatch(matchId);
    }
}
