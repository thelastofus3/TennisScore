package com.thelastofus.tennis.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "Player1")
    private Player playerOne;
    @ManyToOne
    @JoinColumn(name="Player2")
    private Player getPlayerTwo;
    @ManyToOne
    @JoinColumn(name = "Winner")
    private Player winner;
    @Transient
    private MatchScore matchScore;

    public Match(int id, Player playerOne, Player getPlayerTwo, Player winner, MatchScore matchScore) {
        this.id = id;
        this.playerOne = playerOne;
        this.getPlayerTwo = getPlayerTwo;
        this.winner = winner;
        this.matchScore = matchScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getGetPlayerTwo() {
        return getPlayerTwo;
    }

    public void setGetPlayerTwo(Player getPlayerTwo) {
        this.getPlayerTwo = getPlayerTwo;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public MatchScore getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", playerOne=" + playerOne +
                ", getPlayerTwo=" + getPlayerTwo +
                ", winner=" + winner +
                ", matchScore=" + matchScore +
                '}';
    }
}
