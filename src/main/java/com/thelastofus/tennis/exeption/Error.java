package com.thelastofus.tennis.exeption;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Data
class Error extends Exception {
    private int statusCode;
    private LocalDateTime timestamp;
    public Error(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
}
