package com.thelastofus.tennis.service.score;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public enum RegularGamePlayerPoints {
    ZERO("0"),FIFTEEN("15"),THIRTY("30"),FORTY("40"),ADVANTAGE("AD"),SMOOTH(" ");

    final String value;
    public RegularGamePlayerPoints next(){
        if(this == SMOOTH) {
            throw new IllegalStateException("Cannot call next() on SMOOTH");
        }else {
            return RegularGamePlayerPoints.values()[this.ordinal() + 1];
        }
    }
}
