package com.hiring.football.exception;

public class InvalidLeagueException extends Exception {

    public InvalidLeagueException(String invalidLeague) {
        super(invalidLeague);
    }
}
