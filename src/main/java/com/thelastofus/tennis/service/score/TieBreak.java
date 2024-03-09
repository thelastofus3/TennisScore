package com.thelastofus.tennis.service.score;

import com.thelastofus.tennis.model.Match;

public class TieBreak {

    public void startTieBreak(Match match,EPlayer ePlayer) {
        int currentNumber = match.getMatchScore().getSets().get(ePlayer.ordinal());
        match.getMatchScore().getSets().set(ePlayer.ordinal(),currentNumber + 1);
    }
}
