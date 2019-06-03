package com.deeps.rpg.training.types;

import com.deeps.rpg.constants.GameConstants;
import com.deeps.rpg.player.Player;
import com.deeps.rpg.powermoves.PowerMoves;
import com.deeps.rpg.powermoves.PowerMovesRepository;
import com.deeps.rpg.training.AbstractTraining;
import com.deeps.rpg.utils.CommandLineColors;

import java.util.List;
import java.util.Scanner;

/*Class represents basic moves with fly moves training for superHeros
* Part of MVP(USer STory 2)*/
public class FlyTraining extends AbstractTraining {
    private static Scanner scanner = new Scanner( System.in );



    // COnstructor that sets the fly training level
    public FlyTraining() {
        setTrainingId(GameConstants.TRAINING_ID_FLY);
        this.moves = PowerMovesRepository.getInstance().getFlyingTrainingMoveList();
    }


    //Method to train which is an overriden method takes PLayer as parameter
    @Override
    public boolean getTrained(Player player) {
        System.out.println("Lets start training"+ CommandLineColors.ANSI_GREEN);
        System.out.println("So in this training you will be mastering few of the listed fly Power moves!Here we go"+ CommandLineColors.ANSI_GREEN);
        printMoveList(moves);

        boolean allMovesMastered = true;
        for(PowerMoves move: moves){
            boolean result = practiceMove(move);
            allMovesMastered = allMovesMastered && result;
        }

        //check if training completed
        if(allMovesMastered){
            isCompleted = true;
        }
        return isCompleted;
    }


    /*Method to practice the moves accepts PowerMoves as a paramter*/
    private boolean practiceMove(PowerMoves move) {

        boolean moveToNext = false;
        while (!moveToNext){
            System.out.println(move.getMoveId());
            System.out.println(move.getMoveDescription());
            System.out.println("Press ["+CommandLineColors.ANSI_GREEN + move.getMoveSymbol() + CommandLineColors.ANSI_BLUE+"] to master this move "+CommandLineColors.ANSI_GREEN);
            String action =  scanner.nextLine();


            if(action.equalsIgnoreCase(move.getMoveSymbol())) {
                System.out.println("Congrats!! you have mastered this move"+CommandLineColors.ANSI_GREEN);
                move.setMoveMastered(true);
                moveToNext = true;
            }else {
                moveToNext = false;
                System.out.println("Don't worry try again."+CommandLineColors.ANSI_RED);
            }
        }

        return moveToNext;
    }


    /*Method to print all the available Moves list accepts List of PowerMoves as a parameter*/
    private void printMoveList(List<PowerMoves> movesList) {
        int i = 1;
        for(PowerMoves moves: movesList){
            System.out.println(i++ + ". " + moves.getMoveDescription() + ".");
        }
        System.out.println();

    }

    @Override
    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;

    }
}
