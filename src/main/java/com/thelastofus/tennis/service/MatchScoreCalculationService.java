package com.thelastofus.tennis.service;


import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.model.MatchScore;
import com.thelastofus.tennis.service.score.EPlayer;
import com.thelastofus.tennis.service.score.RegularGamePlayerPoints;

public class MatchScoreCalculationService {
    public void calculateScore(Match match, EPlayer ePlayer){
        RegularGamePlayerPoints currentPlayerPoints = match.getMatchScore().getPoints().get(ePlayer.ordinal());
        RegularGamePlayerPoints oppositePlayerPoints = match.getMatchScore().getPoints().get(ePlayer.getOppositePlayer().ordinal());
        if (currentPlayerPoints == RegularGamePlayerPoints.ZERO || currentPlayerPoints == RegularGamePlayerPoints.FIFTEEN || currentPlayerPoints == RegularGamePlayerPoints.THIRTY){
            increasePoint(match,ePlayer);
        }else if(currentPlayerPoints == RegularGamePlayerPoints.FORTY){
            if(oppositePlayerPoints == RegularGamePlayerPoints.FORTY){
                deuce(match,ePlayer);
            }else{
                increaseGame(match,ePlayer);
            }
        }
    }
    public void increasePoint(Match match, EPlayer ePlayer) {
        RegularGamePlayerPoints currentPoints = match.getMatchScore().getPoints().get(ePlayer.ordinal());
        RegularGamePlayerPoints nextPoints = currentPoints.next();
        match.getMatchScore().getPoints().set(ePlayer.ordinal(), nextPoints);
    }
    public void increaseGame(Match match, EPlayer ePlayer){
        match.getMatchScore().increaseGame(ePlayer);
    }
    //переписать логику
    public void deuce(Match match, EPlayer ePlayer) {
        increasePoint(match, ePlayer);
        RegularGamePlayerPoints currentPlayerPoints = match.getMatchScore().getPoints().get(ePlayer.ordinal());
        RegularGamePlayerPoints oppositePlayerPoints = match.getMatchScore().getPoints().get(ePlayer.getOppositePlayer().ordinal());

        if (currentPlayerPoints == RegularGamePlayerPoints.ADVANTAGE && oppositePlayerPoints != RegularGamePlayerPoints.ADVANTAGE) {
            // Если текущий игрок имеет преимущество и противоположный игрок не имеет преимущества,
            // то текущий игрок выигрывает игру
            increaseGame(match, ePlayer);
        }

        match.getMatchScore().getPoints().set(ePlayer.getOppositePlayer().ordinal(), RegularGamePlayerPoints.SMOOTH);
    }
}
