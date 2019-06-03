package com.deeps.rpg;

import com.deeps.rpg.game.GameCreator;
import com.deeps.rpg.helper.GameViewHelper;


/* This is the Game Launcher which launches the Game
 - Starting point of application.
 - Prompt Game story to User and ask to start Game
 Part of MVP(users tory 1,2,3,4).
*/
public class GameLauncher {

    public static void main(String[] args) {
        GameViewHelper.showBackStory();
        boolean startGame = GameViewHelper.askToStartGame();

        if(startGame){
            //start game
            GameCreator game = new GameCreator();
            game.start();
        }else {
            //close application.
            GameViewHelper.sayGoodBye();
            System.exit(0);
        }


    }
}
