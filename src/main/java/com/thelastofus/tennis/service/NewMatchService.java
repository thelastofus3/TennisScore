package com.thelastofus.tennis.service;

import com.thelastofus.tennis.dao.PlayerDAO;
import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.model.Player;
import lombok.Getter;

import java.util.UUID;

public class NewMatchService {
    private final PlayerDAO playerDAO;
    @Getter
    private final OngoingMatchesService ongoingMatchesService;
    public NewMatchService(PlayerDAO playerDAO,OngoingMatchesService ongoingMatchesService) {
        this.playerDAO = playerDAO;
        this.ongoingMatchesService = ongoingMatchesService;
    }

    public UUID startMatch(String firstPlayerName, String secondPlayerName) {
        //get parametr ; +
        //check if players exists in db
        // if exist -> get Player from db -> else(null) create
        Player firstPlayer = playerDAO.findByName(firstPlayerName).orElseGet(() -> {
            Player player = new Player(firstPlayerName);
            try {
                playerDAO.save(player);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
           return player;
        });
        Player secondPlayer = playerDAO.findByName(secondPlayerName).orElseGet(() -> {
           Player player = new Player(secondPlayerName);
           try {
               playerDAO.save(player);
           }catch (Exception e){
               e.printStackTrace();
               return null;
           }
           return player;
        });
        //generate UUID
        Match match = new Match();
        match.setPlayerOne(firstPlayer);
        match.setPlayerTwo(secondPlayer);
        ongoingMatchesService.add(match);
        // create match Hashmap<UUID,Player>
        //redirect to /match-score?uuid=$match_id
        System.out.println(match);
        System.out.println(ongoingMatchesService.getUUID(match));
        return ongoingMatchesService.getUUID(match);
    }
}
