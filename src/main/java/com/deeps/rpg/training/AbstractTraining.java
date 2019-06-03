package com.deeps.rpg.training;

import com.deeps.rpg.player.Player;
import com.deeps.rpg.powermoves.PowerMoves;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*abstract class represent training
 Each training give set of moves to player
Training is mandatory for fight.
 More trainings can be created by extending this class.

 Part of MVP(User Story 2)*/

public abstract class AbstractTraining implements Serializable {

    public String trainingId;
    public Boolean isCompleted = false;
    public List<PowerMoves> moves = new ArrayList();

    //method to create training procedure.
    public abstract boolean getTrained(Player player);

    public abstract void setTrainingId(String trainingId);

    public String getTrainingId() {
        return trainingId;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

}
