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
    ArrayList<RegularGamePlayerPoints> points ;
    ArrayList<Integer> games ;
    ArrayList<Integer> sets ;
    ArrayList<Integer> tieBreak ;

    public MatchScoreDTO() {
        points = new ArrayList<>(Arrays.asList(RegularGamePlayerPoints.ZERO, RegularGamePlayerPoints.ZERO));
        games = new ArrayList<>(Arrays.asList(0, 0));
        sets = new ArrayList<>(Arrays.asList(0, 0));
        tieBreak = new ArrayList<>(Arrays.asList(0, 0));
    }
}
