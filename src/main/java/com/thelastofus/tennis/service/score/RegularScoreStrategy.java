package com.thelastofus.tennis.service.score;

import com.thelastofus.tennis.model.Match;

public class RegularScoreStrategy implements ScoreStrategy{
    RegularMatchScore regularMatchScore = new RegularMatchScore();
    SetMatchScore setMatchScore = new SetMatchScore();

    @Override
    public State calculateScore(Match match, EPlayer ePlayer) {
        regularMatchScore.calculateScore(match,ePlayer);
        return setMatchScore.calculateScore(match,ePlayer);
    }
}
