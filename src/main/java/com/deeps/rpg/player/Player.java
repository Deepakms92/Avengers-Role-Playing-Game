package com.deeps.rpg.player;

import com.deeps.rpg.character.superhero.SuperHero;

import java.io.Serializable;

/*class represent a player
 Each player has to select a superHero to train and fight.
- player has to select hero before training.
- player can only fight if he has hero and trained it.
-part of MVP (User story 1)

*/
public class Player implements Serializable {
    private static int PLAYERS_COUNT = 0;
    private int playerId = ++PLAYERS_COUNT;
    private String playerName;
    private SuperHero superHero;
    private int playerState;


    //Constructors
    public Player(String playerName) {
        this.playerName = playerName;
        this.playerState = PlayerState.PLAYER_STATE_PLAYER_SELECTED;

    }


    //Getters and Setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public SuperHero getSuperHero() {
        return superHero;
    }

    //Setting the player state to initail state
    public void setSuperHero(SuperHero superHero) {
        this.superHero = superHero;
        this.playerState = PlayerState.PLAYER_STATE_CHARACTER_SELECTED;
    }

    public int getPlayerState() {
        return playerState;
    }

    public void setPlayerState(int playerState) {
        this.playerState = playerState;
    }
}
