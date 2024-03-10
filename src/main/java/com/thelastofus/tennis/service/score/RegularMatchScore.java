package com.thelastofus.tennis.service.score;


import com.thelastofus.tennis.model.Match;

public class RegularMatchScore extends Score{
    @Override
    public State calculateScore(Match match, EPlayer ePlayer){
        RegularGamePlayerPoints currentPlayerPoints = match.getMatchScore().getPoints().get(ePlayer.ordinal());
        RegularGamePlayerPoints oppositePlayerPoints = match.getMatchScore().getPoints().get(ePlayer.getOppositePlayer().ordinal());
        if (currentPlayerPoints == RegularGamePlayerPoints.ZERO || currentPlayerPoints == RegularGamePlayerPoints.FIFTEEN
                || currentPlayerPoints == RegularGamePlayerPoints.THIRTY){
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
        return State.ONGOING;
    }
    @Override
    public void getZeroScore(Match match, EPlayer ePlayer){
        match.getMatchScore().getPoints().set(ePlayer.ordinal(), RegularGamePlayerPoints.ZERO);
        match.getMatchScore().getPoints().set(ePlayer.getOppositePlayer().ordinal(), RegularGamePlayerPoints.ZERO);
    }
}

