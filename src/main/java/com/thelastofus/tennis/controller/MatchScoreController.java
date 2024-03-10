package com.thelastofus.tennis.controller;

import com.thelastofus.tennis.model.Match;
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
    private EPlayer ePlayer;
    private State state;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ongoingMatchesService = (OngoingMatchesService) config.getServletContext().getAttribute("onGoingMatchesService");
        matchScoreCalculationService = (MatchScoreCalculationService) config.getServletContext().getAttribute("matchScoreCalculationService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidString);
        Match match = ongoingMatchesService.getMatch(uuid);

        System.out.println(ongoingMatchesService);

        req.setAttribute("match",match);

        req.getRequestDispatcher("/match-score-jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("uuid");
        UUID matchId = UUID.fromString(uuidString);
        Match match = ongoingMatchesService.getMatch(matchId);
        String point = req.getParameter("action");
        if(point.equals("Player_1")){
            ePlayer = EPlayer.PLAYER_ONE;
        }else{
            ePlayer = EPlayer.PLAYER_TWO;
        }
        state = matchScoreCalculationService.calculateScore(match,ePlayer);
        resp.sendRedirect("/match-score?uuid=" + matchId.toString());

//        Если матч закончился:
//        Удаляем матч из коллекции текущих матчей -> метод реализовал, осталось только правильно внедрить в код
//        Записываем законченный матч в SQL базу данных -> реализовать MatchDAO
//        Рендерим финальный счёт -> добавь еще одну jsp чтоб круто это выводить и добовлять победителя для отображения и записи в БД

        if (state == State.PLAYER_ONE_WON){
            ongoingMatchesService.removeMatch(matchId);
        }else if (state == State.PLAYER_TWO_WON){
            ongoingMatchesService.removeMatch(matchId);
        }
    }
}
