package com.deeps.rpg.exception;

public class GameException extends Exception {

    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable t) {
        super(message, t);
    }

    // SHows all the error that is thrown durin the game
    public static void showError(String error) {
        System.err.println(error);
    }
}
