package com.thelastofus.tennis.service;

import com.thelastofus.tennis.dao.PlayerDAO;
import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.model.Player;

import java.io.FilterOutputStream;
import java.util.List;

public class NewMatchService {
    private final PlayerDAO playerDAO;
    public NewMatchService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public Match startMatch(String firstPlayerName, String secondPlayerName) {
        //get parametr ; +
        //check if players exists in db
        // if exist -> get Player from db -> else(null) create
        // create match Hashmap<UUID,Player>
        //redirect to /match-score?uuid=$match_id

        return new Match();
    }
}
