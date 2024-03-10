package com.thelastofus.tennis.service.score;

import com.thelastofus.tennis.model.Match;

public class TieBreakScoreStrategy implements ScoreStrategy{
    SetMatchScore setMatchScore = new SetMatchScore();
    TieBreak tieBreak = new TieBreak();
    @Override
    public State calculateScore(Match match, EPlayer ePlayer) {
        tieBreak.calculateScore(match,ePlayer);
        return setMatchScore.calculateScore(match,ePlayer);
    }
}
