package com.thelastofus.tennis.service.score;

import com.thelastofus.tennis.model.Match;

public class GameMatchScore {
    private final TieBreak tieBreak = new TieBreak();
    public void calculateGame(Match match, EPlayer ePlayer){
        int currentPlayerGames = match.getMatchScore().getGames().get(ePlayer.ordinal());
        int oppositePlayerGames = match.getMatchScore().getGames().get(ePlayer.getOppositePlayer().ordinal());
        if (currentPlayerGames == 6 && oppositePlayerGames < 5){
            increaseSet(match,ePlayer);
        }else if(currentPlayerGames == 7 && oppositePlayerGames < 6){
            increaseSet(match,ePlayer);
        }else if (currentPlayerGames == 6 && oppositePlayerGames == 6){
            tieBreak.startTieBreak(match,ePlayer);
        }
    }
    public void increaseSet(Match match, EPlayer ePlayer){
        int currentNumber = match.getMatchScore().getSets().get(ePlayer.ordinal());
        match.getMatchScore().getSets().set(ePlayer.ordinal(),currentNumber + 1);
        getZeroGame(match,ePlayer);
    }
    public void getZeroGame(Match match, EPlayer ePlayer){
        match.getMatchScore().getGames().set(ePlayer.ordinal(), 0);
        match.getMatchScore().getGames().set(ePlayer.getOppositePlayer().ordinal(),0);
    }
}
