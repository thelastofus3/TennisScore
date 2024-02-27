package com.thelastofus.tennis.service;

import com.thelastofus.tennis.dao.PlayerDAO;
import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.model.Player;

import java.util.UUID;

public class NewMatchService {
    private final PlayerDAO playerDAO;
    private final OngoingMatchesService ongoingMatchesService;
    public NewMatchService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
        this.ongoingMatchesService = new OngoingMatchesService();
    }

    public Match startMatch(String firstPlayerName, String secondPlayerName) {
        //get parametr ; +
        //check if players exists in db
        // if exist -> get Player from db -> else(null) create
        Player firstPlayer = playerDAO.findByName(firstPlayerName).orElseGet(() -> {
           Player player = new Player(firstPlayerName);
           playerDAO.save(player);
           return player;
        });
        Player secondPlayer = playerDAO.findByName(secondPlayerName).orElseGet(() -> {
           Player player = new Player(secondPlayerName);
           playerDAO.save(player);
           return player;
        });
        //generate UUID
        Match match = new Match();
        match.setPlayerOne(firstPlayer);
        match.setPlayerTwo(secondPlayer);
        UUID matchId = ongoingMatchesService.generateMatchId();
        ongoingMatchesService.add(matchId,match);
        // create match Hashmap<UUID,Player>
        //redirect to /match-score?uuid=$match_id

        return match;
    }
}
