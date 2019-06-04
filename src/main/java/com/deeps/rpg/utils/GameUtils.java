package com.deeps.rpg.utils;

import com.deeps.rpg.constants.GameConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


/*Class for the Utils that is required for the Gamer*/
public class GameUtils {


    /*Methos that check if the userinput is coorect among the valid userinputs accepts the valid userinput and the user input as parameter*/
    public static boolean isValidInput(String[] validInputs, String userInput){
        if(null != userInput && null != validInputs && validInputs.length > 0){
            return Arrays.asList(validInputs).contains(userInput);
        }else {
            return false;
        }
    }


    /*Overloaded isValid Iput method
    Here the method accepts start ,end and the user INput*/
    public static boolean isValidInput(int start, int end, String userInput){

        int choice;
        if(null != userInput && !userInput.equals("")){
            try {
                choice = Integer.parseInt(userInput);
            }catch (NumberFormatException e){
                return false;
            }

            if(choice >= start && choice <= end){
                return true;
            }
        }
        return false;
    }



   /* Method to create a Directory to save the player*/
    public static void createPlayerDir() {
        //create a dir to save player game.
        String fileName = GameConstants.SAVED_PLAYER_DIR_NAME;
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
