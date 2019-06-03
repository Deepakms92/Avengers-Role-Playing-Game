package com.deeps.rpg.powermoves;

import com.deeps.rpg.constants.GameConstants;

import java.util.ArrayList;
import java.util.List;

/* Repository for PowerMoves
 - Singleton class
 -Part of MVP(User Story 2 and 3)

* */
public class PowerMovesRepository {

    private static PowerMovesRepository INSTANCE;
    private List<PowerMoves> availablePowerMoveList;

    //private constructors for singleton
    private PowerMovesRepository() {
    }

    //factory method to get instance of Repository making this repository a singleon class as well
    public static PowerMovesRepository getInstance(){
        if(null == INSTANCE){
            INSTANCE = new PowerMovesRepository();
        }

        return INSTANCE;
    }

    //Method to get the block and the damage done by it
    public PowerMoves getBlock() { return new PowerMoves("MOVE_BLOCK", "basic defence block", "B", GameConstants.MOVE_DAMAGE_NONE); }

    //Method to get the fly move and the damage done by it
    private PowerMoves getFly() {
        return new PowerMoves("MOVE_FLY", "fly in air", "F", GameConstants.MOVE_DAMAGE_NONE);
    }

    //Method to get the Kick move and the damage done by it
    private PowerMoves getKick() {
        return new PowerMoves("MOVE_KICK", "basic kick", "K", GameConstants.MOVE_DAMAGE_LOW);
    }

    //Method to get the punch move and the damage done by it
    private PowerMoves getPunch() { return new PowerMoves("MOVE_PUNCH", "basic punch", "P", GameConstants.MOVE_DAMAGE_NORMAL); }

    //Method to get the flyKick move and the damage done by it
    private PowerMoves getFlyingKick() { return new PowerMoves("MOVE_FLY_KICK", "flying kick", "FK", GameConstants.MOVE_DAMAGE_MEDIUM); }

    //Method to get the flypunch move and the damage done by it
    private PowerMoves getFlyingPunch() { return new PowerMoves("MOVE_FLY_PUNCH", "flying punch", "FP", GameConstants.MOVE_DAMAGE_MEDIUM); }

    //method to get all the available Power move lists for that SuperHEro() and return availablePowerMoveList
    public List<PowerMoves> getAvailablePowerMovesList() {
        this.availablePowerMoveList = new ArrayList<>();
        availablePowerMoveList.add(getPunch());
        availablePowerMoveList.add(getKick());
        availablePowerMoveList.add(getFly());
        availablePowerMoveList.add(getFlyingKick());
        availablePowerMoveList.add(getFlyingPunch());
        return availablePowerMoveList;
    }

    //Method to get the the flyMove TrainingList
    public List<PowerMoves> getFlyingTrainingMoveList() {
        List<PowerMoves> flyingTrainingMoves = new ArrayList<>();
        flyingTrainingMoves.add(getFly());
        flyingTrainingMoves.add(getFlyingKick());
        flyingTrainingMoves.add(getFlyingPunch());
        return flyingTrainingMoves;
    }

    //Method to get the the Basic TrainingList
    public List<PowerMoves> getBasicTrainingMoveList() {
        List<PowerMoves> basicTrainingMoves = new ArrayList<>();
        basicTrainingMoves.add(getKick());
        basicTrainingMoves.add(getPunch());
        basicTrainingMoves.add(getBlock());
        return basicTrainingMoves;
    }






}
