package com.thelastofus.tennis.dto;

import com.thelastofus.tennis.service.score.RegularGamePlayerPoints;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchScoreDTO {
    ArrayList<RegularGamePlayerPoints> points = new ArrayList<>();
    ArrayList<Integer> games = new ArrayList<>();
    ArrayList<Integer> sets = new ArrayList<>();
    ArrayList<Integer> tieBreak = new ArrayList<>();

    public MatchScoreDTO() {
        points = new ArrayList<>(Arrays.asList(RegularGamePlayerPoints.ZERO, RegularGamePlayerPoints.ZERO));
        games = new ArrayList<>(Arrays.asList(0, 0));
        sets = new ArrayList<>(Arrays.asList(0, 0));
        tieBreak = new ArrayList<>(Arrays.asList(0, 0));
    }
}
