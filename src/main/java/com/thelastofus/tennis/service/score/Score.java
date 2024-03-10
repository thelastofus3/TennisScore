package com.thelastofus.tennis.service.score;

import com.thelastofus.tennis.model.Match;

public abstract class Score {
    public abstract State calculateScore(Match match,EPlayer ePlayer);
    public abstract void getZeroScore(Match match,EPlayer ePlayer);
    public boolean isPlayerWin(Match match,EPlayer ePlayer){
        return match.getMatchScore().getSets().get(ePlayer.ordinal()) == 2;
    }
    public void increaseSet(Match match, EPlayer ePlayer){
        int currentNumber = match.getMatchScore().getSets().get(ePlayer.ordinal());
        match.getMatchScore().getSets().set(ePlayer.ordinal(),currentNumber + 1);
        getZeroScore(match,ePlayer);
    }
    public void increaseTimeBreak(Match match, EPlayer ePlayer){
        int currentPlayer = match.getMatchScore().getTieBreak().get(ePlayer.ordinal());
        match.getMatchScore().getTieBreak().set(ePlayer.ordinal(),currentPlayer + 1);
    }
    public void increaseGame(Match match, EPlayer ePlayer){
        int currentNumber = match.getMatchScore().getGames().get(ePlayer.ordinal());
        match.getMatchScore().getGames().set(ePlayer.ordinal(),currentNumber + 1);
        getZeroScore(match,ePlayer);
    }
    public void increasePoint(Match match, EPlayer ePlayer) {
        RegularGamePlayerPoints currentPoints = match.getMatchScore().getPoints().get(ePlayer.ordinal());
        RegularGamePlayerPoints nextPoints = currentPoints.next();
        match.getMatchScore().getPoints().set(ePlayer.ordinal(), nextPoints);
    }
    public void setPlayerScore(Match match,EPlayer ePlayer,RegularGamePlayerPoints regularGamePlayerPoints){
        match.getMatchScore().getPoints().set(ePlayer.ordinal(),regularGamePlayerPoints);
    }
}
