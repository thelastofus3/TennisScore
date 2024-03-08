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
public class MatchScore {
    ArrayList<RegularGamePlayerPoints> points = new ArrayList<>();
    ArrayList<Integer> games = new ArrayList<>();
    ArrayList<Integer> sets = new ArrayList<>();

    public MatchScore() {
        points.add(0,RegularGamePlayerPoints.ZERO);
        points.add(1,RegularGamePlayerPoints.ZERO);
        games.add(0,0);
        games.add(1,0);
        sets.add(0,0);
        sets.add(1,0);
    }
    public void getZeroPoints(){
        points.set(0,RegularGamePlayerPoints.ZERO);
        points.set(1,RegularGamePlayerPoints.ZERO);
    }
    public void increaseGame(EPlayer ePlayer){
        int playerIndex = (ePlayer == EPlayer.PLAYER_ONE) ? 0 : 1;
        int currentGames = games.get(playerIndex);
        games.set(playerIndex,currentGames + 1);
        getZeroPoints();
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
