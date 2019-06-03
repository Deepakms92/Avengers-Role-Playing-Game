package com.deeps.rpg.helper;

import com.deeps.rpg.character.superhero.SuperHero;
import com.deeps.rpg.character.superhero.SuperHeroBuilder;
import com.deeps.rpg.character.supervillain.SuperVillain;
import com.deeps.rpg.player.PlayerState;
import com.deeps.rpg.powers.PowerCategory;
import com.deeps.rpg.powers.SuperPower;
import com.deeps.rpg.training.AbstractTraining;
import com.deeps.rpg.utils.CommandLineColors;
import com.deeps.rpg.utils.GameUtils;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*Class GameVieweHelper which helps to TAke the user inputs
Acts a View for the commandline and helps to take the user inputs
Part of MVP (USer Story 1,2,3,4) and enhancements*/
public class GameViewHelper {
    private static final Random random = new Random();
    private static Scanner scanner = new Scanner( System.in );
    private static String[] invalidInputErrors = new String[]{"Please Enter valid Choice ",
            "No, No messing around, be good and try again",
            "This behaviour is not acceptable, Come again with right choice",
            "The whole world is counting on you, save the world with valid option",
            "You are a hero, don't be bad by giving wrong choices",
            "Wrong choices can cost a lot, Don't do it again."};


    // Show the previous story where the game begins
    public static void showBackStory() {
        System.out.println("!!! Welcome to END GAME !!!\n"+ CommandLineColors.ANSI_RESET);
        System.out.println("In previous episode..."+ CommandLineColors.ANSI_RESET);
        System.out.println("See if you wanna skip the fun part and start the game directly, you can skip"+ CommandLineColors.ANSI_RESET);

        boolean skip;
        String questionPrompt = "Do you wanna skip the story or continue?";
        String[] options = new String[] {"1. Skip","2. Continue"};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Say it : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        skip = choice.equalsIgnoreCase("1");

        if(!skip){
            System.out.println("\nSo, The whole universe is at stake coz the mightiest villain ever has " +
                    "been arrived ... THANOS - the great titan."+ CommandLineColors.ANSI_RED);
            scanner.nextLine();
            System.out.println("\nThanos alone defeated the avengers (smashed them in an way) and acquired all the Infinity Stones"+ CommandLineColors.ANSI_RED);
            scanner.nextLine();
            System.out.println("Then he did what he wanted to do with just one SNAP(I hope you are imagining the sound :D, hope you understand we are on CLI)"+ CommandLineColors.ANSI_RED);
            scanner.nextLine();
            System.out.println("So in short, remaining avengers are very tensed and are looking for a recruit who can get all the avengers back .\nNOTE - There won't be a interview for this :P"+ CommandLineColors.ANSI_RED);

        }else {
            System.out.println("You seems to be in hurry. Its ok. Lest start."+ CommandLineColors.ANSI_CYAN);
        }

        scanner.nextLine();
        System.out.println("By the way I am JON SNOW:P, I know nothing.:D (imagine scratching his head :P).\nNOTE - I know JON SNOW is no where related to Avengers :P"+CommandLineColors.ANSI_CYAN);
    }

    public static boolean askToStartGame(){
        boolean startGame;

        String questionPrompt = "So are you ready to SAVE the WORLD - ";
        String[] options = new String[] {"1.Continue","2.Quit"};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Your choice : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        startGame = choice.equalsIgnoreCase("1");

        return startGame;
    }

    public static void sayGoodBye() {
        System.out.print("GoodByes are Hard to Say :D.Still you are forcing me to do that ,very emotional at the moment:P, Nevertheless GOODBYEE:("+CommandLineColors.ANSI_BLUE);
    }

    private static String showOptions(String questionPrompt, String[] options, String answerPrompt, String[] validInputOptions){
        String choice;
        StringBuilder optionStrBuilder = new StringBuilder("\n" + questionPrompt + "\n");
        for (String str: options){
            optionStrBuilder.append(str).append("\n");
        }
        optionStrBuilder.append(answerPrompt);
        String optionStr = optionStrBuilder.toString();

        while (true){
            System.out.print(optionStr);
            choice = scanner.next();
            //validate input
            if(GameUtils.isValidInput(validInputOptions, choice)){
                break;
            }else {
                System.out.println(invalidInputErrors[random.nextInt(invalidInputErrors.length)]);
            }
        }

        return choice;
    }

    public static int askToChoosePlayer() {

        String questionPrompt = "Do you play as new player or existing saved player?";
        String[] options = new String[] {"1. New Player","2. Existing Player"};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Answer : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);
    }

    public static String askUserForPlayerName() {
        System.out.println("\nSo what is awesome name of your new player? :"+CommandLineColors.ANSI_GREEN);
        return scanner.next();
    }


    public static int showMainMenu(int playerState) {

        String questionPrompt = "\nMain Menu";
        String createCharacterOptionsStr;
        if(playerState < PlayerState.PLAYER_STATE_CHARACTER_SELECTED){
            createCharacterOptionsStr = "1. Create or choose super hero";
        }else {
            createCharacterOptionsStr = "1. Change super hero";
        }
        String[] options = new String[] {createCharacterOptionsStr,"2. Training", "3. Fight", "4. Save and Quit"};
        String[] validInputOptions = new String[] {"1","2", "3", "4"};
        String answerPrompt = "What you want to do? : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);
    }


    public static int askPlayerToChooseSuperHero() {

        String questionPrompt = "Hmm... in this case,  you have two choices - ";
        String[] options = new String[] {"1. Choose existing superhero","2. Create new superHero", "3. Main menu"};
        String[] validInputOptions = new String[] {"1","2", "3"};
        String answerPrompt = "Choose : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);

    }

    public static String askUserToChooseSuperHero(List<String> existingSuperHeroesNames) {
        int choiceIndex;
        while (true){
            //prompt existing heroes list
            showSuperHeroesList(existingSuperHeroesNames);
            String choice = scanner.next();
            if(GameUtils.isValidInput(1, existingSuperHeroesNames.size(), choice)){
                choiceIndex = Integer.parseInt(choice);
                break;
            }else {
                //not a valid response . prompt again
                System.out.println("Don't you wanna be Hero? choose again."+CommandLineColors.ANSI_RESET);
            }
        }

        return existingSuperHeroesNames.get(choiceIndex - 1);
    }

    private static void showSuperHeroesList(List<String> existingSuperHeroesNames) {
        System.out.println("\nHere is list of great Heroes for you. Choose wisely"+CommandLineColors.ANSI_BLUE);
        int i = 1;
        for(String superheroName : existingSuperHeroesNames){
            System.out.println(i + "." + superheroName);
            i++;
        }
        System.out.println("Answer"+CommandLineColors.ANSI_GREEN);
    }

    public static void showSuperHeroDetails(SuperHero superHeroChosen) {
        System.out.println("\n");
        System.out.println(superHeroChosen.getName().toUpperCase());
        System.out.println("Strength - " + superHeroChosen.getStrength());
        System.out.println("Level - " + superHeroChosen.getLevel());
        StringBuilder powers = new StringBuilder("Powers - ");
        for(SuperPower power: superHeroChosen.getSuperPowers()){
            powers.append(power.getName()).append(",");
        }
        System.out.println(powers);
        System.out.println("\n");
    }



    public static SuperHeroBuilder getInfoofNewSuperHero() {
        SuperHeroBuilder builder;

        System.out.println("Lets get some details for your hero."+CommandLineColors.ANSI_BLUE);

        System.out.print("What is hero your name? : "+CommandLineColors.ANSI_BLUE);
        String name = scanner.nextLine();
        builder = new SuperHeroBuilder(name);

        System.out.print("What is your real name? : "+CommandLineColors.ANSI_BLUE);
        String realName = scanner.nextLine();
        builder.setRealName(realName);

        System.out.print("What is your Gender ? [M/F] : "+CommandLineColors.ANSI_BLUE);
        String gender = scanner.nextLine();
        builder.setGender(gender);

        System.out.print("What is your height? : "+CommandLineColors.ANSI_BLUE);
        String height = scanner.nextLine();
        builder.setHeight(Float.parseFloat(height));

        System.out.print("What is your wight? : "+CommandLineColors.ANSI_BLUE);
        String weight = scanner.nextLine();
        builder.setAge(Integer.parseInt(weight));

        System.out.print("What is your punch line? : "+CommandLineColors.ANSI_BLUE);
        String punchLine = scanner.nextLine();
        builder.setPunchLine(punchLine);

        System.out.print("What are your super power name? : "+CommandLineColors.ANSI_BLUE);
        String spName = scanner.nextLine();

        System.out.print("What are your super power level? [L/M/H]: "+CommandLineColors.ANSI_BLUE);
        String spLevel = scanner.nextLine();

        SuperPower sp = new SuperPower(spName, PowerCategory.HIGH);
        builder.setSuperPowers(new SuperPower[]{sp});

        return builder;
    }

    public static int askToTrainSuperHero() {

        String questionPrompt = "Are you ready for training (You must be, Good for battle) ?";
        String[] options = new String[] {"1. I am born ready. Let's do the training ","2. Main menu "};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Answer : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);

    }

    public static void showEssentialTrainingList(List<AbstractTraining> essentialTrainings) {

        System.out.println("Nice choice my friend..."+CommandLineColors.ANSI_GREEN);
        System.out.println("Great Heroes comes with great responsibilities :P\nSo you have to train hard."+CommandLineColors.ANSI_GREEN);

        //show all required trainings
        for(AbstractTraining t: essentialTrainings){
            System.out.println(t.trainingId);
        }
    }


    public static int askForBattle() {

        String questionPrompt = "Good job. You have completed the training, Now you are ready for battle";
        String[] options = new String[] {"1. Start Battle","2. Main menu"};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Your Choice : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);
    }

    public static void showSuperVillainDetails(SuperVillain superVillain) {
        System.out.println("\n");
        System.out.println(superVillain.getName().toUpperCase());
        System.out.println("Strength - " + superVillain.getStrength());
        System.out.println("Level - " + superVillain.getLevel());
        System.out.println("\n");
    }


    public static void showFightInstructions() {
        System.out.println("So, let me tell you some rules first."+CommandLineColors.ANSI_CYAN);
        System.out.println(" - Both (Hero/Ememy) will get 100 initial health."+CommandLineColors.ANSI_CYAN);
        System.out.println(" - The one whose health will reduced to zero first will loose."+CommandLineColors.ANSI_CYAN);
        System.out.println(" - This is one to one combat, i.e each player will attack one by one like"+CommandLineColors.ANSI_CYAN);
        System.out.println(" - You are only have the moves that you mastered in your training(That's why trainings are good)"+CommandLineColors.ANSI_CYAN);
        System.out.println(" - Damage will depends on your choice of move and your strength( choose your move wisely)"+CommandLineColors.ANSI_CYAN);
        System.out.println(" - Damage will depends on your choice of move and your strength( choose your move wisely)"+CommandLineColors.ANSI_CYAN);
        System.out.println(" - Block move is defence move. It will save you from damage."+CommandLineColors.ANSI_CYAN);
        System.out.println(" - Only Continuous 2 Blocks are effective, after that you will get harmed"+CommandLineColors.ANSI_CYAN);
        System.out.println(" - You have window of 3 seconds to attack, after that your life will start to reduce."+CommandLineColors.ANSI_CYAN);
        System.out.println(" - Most important, Read all rules carefully :P\n"+CommandLineColors.ANSI_CYAN);
    }


    public static boolean performToss() {
        String tossChoice;

        while(true){
            System.out.println("Heads[H] or Tails[T]:");
            tossChoice = scanner.nextLine();

            if(GameUtils.isValidInput(new String[]{"h", "t"}, tossChoice.toLowerCase())){
                break;
            }else {
                System.out.println("How could you do that, come again.");
            }
        }

        return random.nextBoolean();
    }





}
