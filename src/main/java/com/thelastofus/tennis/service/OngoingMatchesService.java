package com.thelastofus.tennis.service;

import com.thelastofus.tennis.model.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {
    private final Map<UUID, Match> ongoingMatch;

    public OngoingMatchesService() {
        this.ongoingMatch = new HashMap<>();
    }
    public UUID generateMatchId(){
        return UUID.randomUUID();

    }
    public void add(UUID matchId, Match match){
        ongoingMatch.put(matchId,match);
    }
    public Match getMatch(UUID matchId){
        return ongoingMatch.get(matchId);
    }
}
