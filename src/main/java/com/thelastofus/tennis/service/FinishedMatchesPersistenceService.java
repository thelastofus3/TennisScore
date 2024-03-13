package com.thelastofus.tennis.service;

import com.thelastofus.tennis.dao.MatchDAO;
import com.thelastofus.tennis.model.Match;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final MatchDAO matchDAO;

    public FinishedMatchesPersistenceService() {
        this.matchDAO = new MatchDAO();
    }
    public void saveFinishedMatch(Match match){
        matchDAO.save(match);
    }

    public List<Match> getAllFinishedMatches(int page) {
        return matchDAO.getMatchesForPage(page);
    }
    public long getNoOfRecords(){
        long numberOfElement = matchDAO.getNumberOfElement();
        return (long) Math.ceil((double) numberOfElement / 5);
    }

    public List<Match> findPlayerMatches(int page, String playerName) {
        return matchDAO.getPlayerMatches(page,playerName);
    }

    public long getNoOfRecordsForPlayer(String playerName) {
        long numberOfElement = matchDAO.getNumberOfElementForPlayer(playerName);
        return (long) Math.ceil((double) numberOfElement / 5);
    }
}
