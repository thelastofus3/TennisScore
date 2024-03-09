package com.thelastofus.tennis.service.score;


import com.thelastofus.tennis.model.Match;

public class PointMatchScore {
    public void calculatePoint(Match match, EPlayer ePlayer){
        RegularGamePlayerPoints currentPlayerPoints = match.getMatchScore().getPoints().get(ePlayer.ordinal());
        RegularGamePlayerPoints oppositePlayerPoints = match.getMatchScore().getPoints().get(ePlayer.getOppositePlayer().ordinal());
        if (currentPlayerPoints == RegularGamePlayerPoints.ZERO || currentPlayerPoints == RegularGamePlayerPoints.FIFTEEN || currentPlayerPoints == RegularGamePlayerPoints.THIRTY){
            increasePoint(match,ePlayer);
        }else if(currentPlayerPoints == RegularGamePlayerPoints.FORTY){
            if(oppositePlayerPoints == RegularGamePlayerPoints.FORTY){
                increasePoint(match,ePlayer);
            }else if(oppositePlayerPoints == RegularGamePlayerPoints.ADVANTAGE){
                setPlayerScore(match,ePlayer.getOppositePlayer(),RegularGamePlayerPoints.FORTY);
            }else{
                increaseGame(match,ePlayer);
            }
        } else if (currentPlayerPoints == RegularGamePlayerPoints.ADVANTAGE) {
            increaseGame(match,ePlayer);
        }
    }
    public void increasePoint(Match match, EPlayer ePlayer) {
        RegularGamePlayerPoints currentPoints = match.getMatchScore().getPoints().get(ePlayer.ordinal());
        RegularGamePlayerPoints nextPoints = currentPoints.next();
        match.getMatchScore().getPoints().set(ePlayer.ordinal(), nextPoints);
    }
    public void increaseGame(Match match, EPlayer ePlayer){
        int playerIndex = (ePlayer == EPlayer.PLAYER_ONE) ? 0 : 1;
        int currentGames = match.getMatchScore().getGames().get(playerIndex);
        match.getMatchScore().getGames().set(playerIndex, currentGames + 1);
        getZeroPoints(match,ePlayer);
    }
    public void setPlayerScore(Match match,EPlayer ePlayer,RegularGamePlayerPoints regularGamePlayerPoints){
        match.getMatchScore().getPoints().set(ePlayer.ordinal(),regularGamePlayerPoints);
    }
    public void getZeroPoints(Match match,EPlayer ePlayer){
        match.getMatchScore().getPoints().set(ePlayer.ordinal(), RegularGamePlayerPoints.ZERO);
        match.getMatchScore().getPoints().set(ePlayer.getOppositePlayer().ordinal(), RegularGamePlayerPoints.ZERO);
    }
}

