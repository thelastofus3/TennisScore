package com.thelastofus.tennis.service;

import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.service.score.EPlayer;
import com.thelastofus.tennis.service.score.PointMatchScore;
import com.thelastofus.tennis.service.score.GameMatchScore;

public class MatchScoreCalculationService {
    PointMatchScore regularMatchScore = new PointMatchScore();
    GameMatchScore setMatchScore = new GameMatchScore();
    public void calculateScore(Match match, EPlayer ePlayer){
        regularMatchScore.calculatePoint(match,ePlayer);
        setMatchScore.calculateGame(match,ePlayer);
    }
}
