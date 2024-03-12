package com.thelastofus.tennis.service;

import com.thelastofus.tennis.dao.MatchDAO;
import com.thelastofus.tennis.model.Match;

public class FinishedMatchesPersistenceService {
    private final MatchDAO matchDAO;

    public FinishedMatchesPersistenceService() {
        this.matchDAO = new MatchDAO();
    }
    public void saveFinishedMatch(Match match){
        matchDAO.save(match);
    }
}
