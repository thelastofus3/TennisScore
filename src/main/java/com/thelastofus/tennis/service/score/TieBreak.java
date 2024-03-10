package com.thelastofus.tennis.service.score;

import com.thelastofus.tennis.model.Match;

public class TieBreak extends Score{
    @Override
    public State calculateScore(Match match, EPlayer ePlayer) {
        int currentPlayer = match.getMatchScore().getTieBreak().get(ePlayer.ordinal());
        int oppositePlayer = match.getMatchScore().getTieBreak().get(ePlayer.getOppositePlayer().ordinal());
        if(currentPlayer >= 7 && currentPlayer - oppositePlayer >= 2){
            increaseSet(match,ePlayer);
        }else {
            increaseTimeBreak(match,ePlayer);
        }
        return State.ONGOING;
    }
    @Override
    public void getZeroScore(Match match, EPlayer ePlayer){
        match.getMatchScore().getTieBreak().set(ePlayer.ordinal(), 0);
        match.getMatchScore().getTieBreak().set(ePlayer.getOppositePlayer().ordinal(),0);
        match.getMatchScore().getGames().set(ePlayer.ordinal(),0);
        match.getMatchScore().getGames().set(ePlayer.getOppositePlayer().ordinal(),0);
    }

}
