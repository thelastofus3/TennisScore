package com.thelastofus.tennis.service.score;

public enum EPlayer {
    PLAYER_ONE,PLAYER_TWO;
    public EPlayer getOppositePlayer(){
        return this == PLAYER_ONE ? PLAYER_TWO : PLAYER_ONE;
    }
    public EPlayer getPlayer(){
        return this == PLAYER_ONE ? PLAYER_ONE : PLAYER_TWO;
    }
}
