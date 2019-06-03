package com.deeps.rpg.game;

import com.deeps.rpg.player.Player;

import java.io.File;

public interface Game {
    public void start();
    public void finishGame();
    public void createOrChoosePlayer(int choice);
    public void retrieveSavedPlayer(File playerFile);
    public void showMainMenu();
    public void saveGame(Player player);

}
