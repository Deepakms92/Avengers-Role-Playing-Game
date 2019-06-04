package com.deeps.rpg.game.impl;

import com.deeps.rpg.character.superhero.SuperHero;
import com.deeps.rpg.character.superhero.SuperHeroBuilder;
import com.deeps.rpg.character.superhero.repo.SuperHeroRepository;
import com.deeps.rpg.constants.GameConstants;
import com.deeps.rpg.exception.GameException;
import com.deeps.rpg.exception.PlayerException;
import com.deeps.rpg.fight.Fight;
import com.deeps.rpg.fight.impl.FightImpl;
import com.deeps.rpg.game.Game;
import com.deeps.rpg.helper.GameViewHelper;
import com.deeps.rpg.player.Player;
import com.deeps.rpg.player.PlayerState;
import com.deeps.rpg.training.AbstractTraining;
import com.deeps.rpg.utils.CommandLineColors;
import com.deeps.rpg.utils.GameUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class GameImpl implements Game {

    private Player userPlayer;
    //constructor
    public GameImpl() {
        initGame();
    }

    //method to initialise variable necessary for gameshowBackStory
    private void initGame() {
        //create player directory if its doesn't exist
        GameUtils.createPlayerDir();
    }

    //method to clean up when game finished and close application.
    public void finishGame() {
        //save game and exit
        saveGame(userPlayer);
        String input= GameViewHelper.toAskForChoice();
        if(input.equalsIgnoreCase("Q")){
            GameViewHelper.sayGoodBye();
            GameViewHelper.aboutTheDeveloper();
            System.exit(0);
        }
        else{
            showMainMenu();
        }

    }


    // method to start game
    @Override
    public void start() {

        //menu to prompt user to choose existing saved player or new player
        showChoosePlayerMenu();

        //show main menu
        showMainMenu();
    }


    //create a new Hero by asking info from player

    private void createNewHero() {
        SuperHeroBuilder superHeroBuilder = GameViewHelper.getInfoofNewSuperHero();
        SuperHero newSuperHero = superHeroBuilder.build();
        GameViewHelper.showSuperHeroDetails(newSuperHero);
        //add hero to repository
        SuperHeroRepository.getInstance().addSuperHero(newSuperHero);
        //assign hero to player
        userPlayer.setSuperHero(newSuperHero);

    }


    //MEthod to show the choose player Menu

    private void showChoosePlayerMenu() {

        int choice = GameViewHelper.askToChoosePlayer();
        createOrChoosePlayer(choice);
    }


    //create or choose existing player according to user choice.
    @Override
    public void createOrChoosePlayer(int choice) {
        switch (choice){
            case 1:
                //create new player
                try {
                    createNewPlayer();
                } catch (PlayerException e) {
                    System.err.println(e.getMessage());
                    showChoosePlayerMenu();
                }
                break;

            case 2:
                // get player from saved player
                try {
                    chooseExistingPlayer();
                } catch (PlayerException e) {
                    System.err.println(e.getMessage());
                    showChoosePlayerMenu();
                }
                break;


        }

    }

    private void chooseExistingPlayer() throws PlayerException {

        String playerName = GameViewHelper.askUserForPlayerName();
        File playerFile = checkIfPlayerAlreadyExist(playerName + GameConstants.SERIALISED_PLAYER_FILE_TYPE);

        if(null != playerFile){
            //player exist
            retrieveSavedPlayer(playerFile);

        }else {

//            GameException.showError("No such player exist"+ CommandLineColors.ANSI_RED);
            throw new PlayerException("No such player exist"+ CommandLineColors.ANSI_RED);

        }
    }


    /* Retrieve player saved info
     *  parameters : playerFile(File) - saved file of player
     */
    @Override
    public void retrieveSavedPlayer(File playerFile) {

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(playerFile);
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            userPlayer = (Player) objectInputStream.readObject();

            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Check if player with @playerName is already saved
     *   parameters : playerName - name provided by user
     *   return : File - if player saved
     *            null - if no player saved with give name
     * */

    private File checkIfPlayerAlreadyExist(String playerName) {
        //find player in existing player list
        File dir = new File(GameConstants.SAVED_PLAYER_DIR_NAME);
        File[] playerList = dir.listFiles();

        //extract player
        if (playerList != null) {
            return Arrays.stream(playerList)
                    .filter(file -> playerName.equalsIgnoreCase(file.getName()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }


    //method to create new player with name asked form user
    private void createNewPlayer() throws PlayerException {
        File playerFile=null;
        String playerName = GameViewHelper.askUserForPlayerName();
        if(!GameUtils.isNumeric(playerName)){
            playerFile = checkIfPlayerAlreadyExist(playerName+GameConstants.SERIALISED_PLAYER_FILE_TYPE);
        }
        else{
            throw new PlayerException("Name should not be in number");
        }


        if(playerFile != null){
            //already exist player with name
            throw new PlayerException("Player already exist with this name"+CommandLineColors.ANSI_RED);


        }else {
            //create new player with given name
            userPlayer = new Player(playerName);
        }
    }


    /*Show main menu to user -
     Create or choose Hero
     Train Hero
     Fight
     Save and quit*/
    @Override
    public void showMainMenu() {

        int choice = GameViewHelper.showMainMenu(userPlayer.getPlayerState());
        try {
            checkUserChoiceWithGameState(choice);

        } catch (GameException e) {
            System.err.println(e.getMessage());
            showMainMenu();

        }
    }

    /* Check user choice and validate is player can perform chosen tasks and perform task according to choice.
     *  parameters : choice - user choice form main menu
     *
     * */
    private void checkUserChoiceWithGameState(int choice) throws GameException {
        if(choice == 4){
            //save player and quit.
            finishGame();

        }else {
            //user want to create/choose character or train or fight
            if(choice <= userPlayer.getPlayerState()){
                //user chose allowable action according to state.
                switch (choice){
                    case 1:
                        createCharacter();
                        break;

                    case 2:
                        train();
                        break;

                    case 3:
                        fight();
                        break;
                }

            }else {

                //user choice is not allowed by game state
                switch (userPlayer.getPlayerState()){
                    case PlayerState.PLAYER_STATE_PLAYER_SELECTED:
                        //Player can't train or fight without Hero.
                        throw new GameException("Please choose Hero first"+CommandLineColors.ANSI_RED);



                    case PlayerState.PLAYER_STATE_CHARACTER_SELECTED:
                        //Player can't fight without training.
                        throw new GameException("Please complete the training first"+CommandLineColors.ANSI_RED);

                }

            }
        }
    }


    @Override
    public void saveGame(Player player) {
        String name = GameConstants.SAVED_PLAYER_DIR_NAME + "/" + player.getPlayerName() + GameConstants.SERIALISED_PLAYER_FILE_TYPE;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(name);
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(player);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    // create new superHEro or choose existing SuperHEro for player
    private void createCharacter() {

        int choice = GameViewHelper.askPlayerToChooseSuperHero();

        switch (choice){
            case 1:
                try {
                    showExistingAvailableHeroes();
                } catch (GameException e) {
                    System.err.println(e.getMessage());
                    showMainMenu();
                }
                showTrainingOption();
                break;

            case 2:
                createNewHero();
                showTrainingOption();
                break;

            case 3:
                showMainMenu();
                break;
        }
    }

    //Method to show the Training Options
    private void showTrainingOption() {
        int choice = GameViewHelper.askToTrainSuperHero();
        switch (choice){
            case 1:
                train();
                break;

            case 2:
                showMainMenu();
                break;
        }
    }
    //methods for fight
    private void fight() {
        System.out.println("Fight."+CommandLineColors.ANSI_CYAN);
        Fight fight = new FightImpl(userPlayer);
        boolean userWon = fight.startFight();

        if(userWon){
            System.out.println("Congrats, You won"+CommandLineColors.ANSI_CYAN);
        }else {
            System.out.println("Try Again"+CommandLineColors.ANSI_RED);
        }

        userPlayer.setPlayerState(PlayerState.PLAYER_STATE_AT_LEAST_ONE_FIGHT_DONE);
        //show main menu
        showMainMenu();
    }

    // method to train User.
    private void train() {

        GameViewHelper.showEssentialTrainingList(userPlayer.getSuperHero().getEssentialTrainings());

        //start trainings
        startHeroTraining(userPlayer.getSuperHero().getEssentialTrainings());
        showBattleOption();
    }


    private void showBattleOption() {

        int choice = GameViewHelper.askForBattle();


        switch (choice){
            case 1:
                //start battle process
                fight();
                break;

            case 2:
                showMainMenu();
                break;
        }

    }

    //Method to start the Super HEro Training
    private void startHeroTraining(List<AbstractTraining> essentialTrainings) {
        for(AbstractTraining training: essentialTrainings){
            //train user
            training.getTrained(userPlayer);
        }
        userPlayer.getSuperHero().setTrainingCompleted(true);
        userPlayer.setPlayerState(PlayerState.PLAYER_STATE_TRAINING_COMPLETED);
    }


    //show existing available heroes
    private void showExistingAvailableHeroes() throws GameException {

        SuperHero superHeroChosen;
        List<String> existingHeroesNames = SuperHeroRepository.getInstance().getActiveHeroesNames();

        String choice = GameViewHelper.askUserToChooseSuperHero(existingHeroesNames);
        superHeroChosen = SuperHeroRepository.getInstance().getSuperHeroByName(choice);

        if(null == superHeroChosen){
            throw new GameException("Something went wrong . Please try again."+CommandLineColors.ANSI_RED);
        }else {
            //show chosen hero details
            GameViewHelper.showSuperHeroDetails(superHeroChosen);
        }
        //assign hero to player
        userPlayer.setSuperHero(superHeroChosen);
    }
}
