package com.thelastofus.tennis.service.score;

import com.thelastofus.tennis.model.Match;

public class SetMatchScore extends Score{
    @Override
    public State calculateScore(Match match, EPlayer ePlayer){
        int currentPlayerGames = match.getMatchScore().getGames().get(ePlayer.ordinal());
        int oppositePlayerGames = match.getMatchScore().getGames().get(ePlayer.getOppositePlayer().ordinal());
        int gameDifference = currentPlayerGames - oppositePlayerGames;

        if ((currentPlayerGames == 6 || currentPlayerGames == 7) && gameDifference >= 2) {
            increaseSet(match, ePlayer);
        }

        if (isPlayerWin(match, ePlayer)) {
            return State.PLAYER_ONE_WON;
        } else if (isPlayerWin(match, ePlayer.getOppositePlayer())) {
            return State.PLAYER_TWO_WON;
        }

        return State.ONGOING;
    }

    @Override
    public void getZeroScore(Match match, EPlayer ePlayer){
        match.getMatchScore().getGames().set(ePlayer.ordinal(), 0);
        match.getMatchScore().getGames().set(ePlayer.getOppositePlayer().ordinal(),0);
    }
}
