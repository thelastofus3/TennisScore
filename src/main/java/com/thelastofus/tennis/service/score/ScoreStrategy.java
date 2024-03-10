package com.thelastofus.tennis.service.score;

import com.thelastofus.tennis.model.Match;

public interface ScoreStrategy {
    State calculateScore(Match match, EPlayer ePlayer);
}
