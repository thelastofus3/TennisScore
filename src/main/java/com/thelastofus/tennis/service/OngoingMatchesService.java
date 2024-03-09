package com.thelastofus.tennis.service;

import com.thelastofus.tennis.model.Match;
import com.thelastofus.tennis.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private final Map<UUID, Match> ongoingMatch;

    public OngoingMatchesService() {
        this.ongoingMatch = new ConcurrentHashMap<>();
    }

    public UUID add(Match match){
        UUID matchId = UUID.randomUUID();
        ongoingMatch.put(matchId,match);
        return matchId;
    }
    public Match getMatch(UUID matchId){
        return ongoingMatch.get(matchId);
    }
    public UUID getUUID(Match match){
        for (Map.Entry<UUID, Match> entry : ongoingMatch.entrySet()) {
            if (entry.getValue().equals(match)) {
                return entry.getKey();
            }
        }
        return null;
    }
    public void updateMatch(UUID matchId, Match updatedMatch) {
        ongoingMatch.put(matchId, updatedMatch);
    }

}
