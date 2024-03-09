package com.thelastofus.tennis.model;

import com.thelastofus.tennis.service.score.EPlayer;
import com.thelastofus.tennis.service.score.RegularGamePlayerPoints;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchScore{
    ArrayList<RegularGamePlayerPoints> points = new ArrayList<>();
    ArrayList<Integer> games = new ArrayList<>();
    ArrayList<Integer> sets = new ArrayList<>();
    ArrayList<Integer> tieBreak = new ArrayList<>();

    public MatchScore() {
        points.add(0,RegularGamePlayerPoints.ZERO);
        points.add(1,RegularGamePlayerPoints.ZERO);
        games.add(0,0);
        games.add(1,0);
        sets.add(0,0);
        sets.add(1,0);
        tieBreak.add(0,0);
        tieBreak.add(1,0);
    }
    @Override
    public String toString() {
        return "MatchScore{" +
                "points=" + points +
                ", games=" + games +
                ", sets=" + sets +
                '}';
    }
}
