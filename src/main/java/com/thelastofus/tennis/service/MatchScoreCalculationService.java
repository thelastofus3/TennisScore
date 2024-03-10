package com.thelastofus.tennis.service;

import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.service.score.*;

public class MatchScoreCalculationService {
    RegularScoreStrategy regularScoreStrategy = new RegularScoreStrategy();
    TieBreakScoreStrategy tieBreakScoreStrategy = new TieBreakScoreStrategy();
    public State calculateScore(Match match, EPlayer ePlayer){
        int currentPlayerGames = match.getMatchScore().getGames().get(ePlayer.ordinal());
        int oppositePlayerGames = match.getMatchScore().getGames().get(ePlayer.getOppositePlayer().ordinal());
        if (currentPlayerGames == 6 && oppositePlayerGames == 6){
            return tieBreakScoreStrategy.calculateScore(match,ePlayer);
        } else {
            return regularScoreStrategy.calculateScore(match,ePlayer);
        }
    }
}
