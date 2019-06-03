package com.deeps.rpg.exception;

public class PlayerException extends Exception {
    public PlayerException(String message) {
        super(message);
    }

    public PlayerException(String message, Throwable t) {
        super(message, t);
    }

    // SHows all the error that is thrown durin the game
    public static void showError(String error) {
        System.err.println(error);
    }
}
