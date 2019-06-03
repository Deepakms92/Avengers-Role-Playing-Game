package com.deeps.rpg.constants;

/*Constants calss that accomodates all the constants of the game.
Done as a part of MVP
* */

import com.deeps.rpg.powermoves.PowerMovesRepository;

public class GameConstants {
    public static final String SAVED_PLAYER_DIR_NAME = "saved_player";
    public static final String SERIALISED_PLAYER_FILE_TYPE = ".dat";
    public static final String DEFAULT_DIALOGUE_FOR_HERO = "AVENGERS ASSEMBLE";
    public static final String DEFAULT_DIALOGUE_FOR_VILLAIN = "DESTROY THE WORLD";
    public static final String BLOCK_MOVE_ID = PowerMovesRepository.getInstance().getBlock().getMoveId();
    public static final int MAX_BLOCK_LIMIT = 2;
    public static final int MOVE_DAMAGE_NONE = 0;
    public static final int MOVE_DAMAGE_LOW = 5;
    public static final int MOVE_DAMAGE_NORMAL = 7;
    public static final int MOVE_DAMAGE_MEDIUM = 10;
    public static final int MOVE_DAMAGE_HIGH = 15;
    public static final int USER_INPUT_TIME_WINDOW = 6000;
    public static final String TRAINING_ID = "BASIC_TRAINING";
    public static final String TRAINING_ID_FLY = "FLY_TRAINING";

}
