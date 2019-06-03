package com.deeps.rpg.player;

/*playerState - represent action allowed to player
COuld be transfeered to Game constants but these are particular t the PLayer

Part of MVP (USer Story 1)
*/

public class PlayerState {
    public static final int PLAYER_STATE_PLAYER_SELECTED = 1;
    public static final int PLAYER_STATE_CHARACTER_SELECTED = 2;
    public static final int PLAYER_STATE_TRAINING_COMPLETED = 3;
    public static final int PLAYER_STATE_AT_LEAST_ONE_FIGHT_DONE = 4;

}
