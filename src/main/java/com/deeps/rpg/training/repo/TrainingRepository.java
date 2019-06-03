package com.deeps.rpg.training.repo;

import com.deeps.rpg.training.AbstractTraining;
import com.deeps.rpg.training.types.BasicTraining;
import com.deeps.rpg.training.types.FlyTraining;

import java.util.ArrayList;
import java.util.List;

/* Repository for Training
 - Singleton class
 -Part of MVP(User Story 2)

* */
public class TrainingRepository {

    private static TrainingRepository INSTANCE;

    //private constructors for singleton pattern
    private TrainingRepository(){}

    //factory method to get instance of Repository making this repository a singleon class as well
    public static TrainingRepository getInstance(){
        if(null == INSTANCE){
            INSTANCE = new TrainingRepository();
        }

        return INSTANCE;
    }

    /*Method to get all the flytrainings*/
    public FlyTraining getFlyTraining(){
        return new FlyTraining();
    }

    /*Method to get all the basictrainings*/
    public BasicTraining getBasicTraining(){
        return new BasicTraining();
    }

    /*Method to get all the ironMan Traings as he has Both fly and basic moves*/
    public List<AbstractTraining> getIronManTrainings(){
        List trainings = new ArrayList<AbstractTraining>();
        trainings.add(new FlyTraining());

        return trainings;
    }




}
