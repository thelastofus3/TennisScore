package com.thelastofus.tennis.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Name")
    private String name;
    @OneToMany
    private List<Match> matches;
    public Player(){}

    public Player(int id, String name, List<Match> matches) {
        this.id = id;
        this.name = name;
        this.matches = matches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
