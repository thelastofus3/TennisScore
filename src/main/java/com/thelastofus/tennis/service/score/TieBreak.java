package com.thelastofus.tennis.service.score;

import com.thelastofus.tennis.model.Match;

public class TieBreak {

    public void startTieBreak(Match match,EPlayer ePlayer) {
        int currentNumber = match.getMatchScore().getTieBreak().get(ePlayer.ordinal());
        match.getMatchScore().getTieBreak().set(ePlayer.ordinal(),currentNumber + 1);
    }
}
