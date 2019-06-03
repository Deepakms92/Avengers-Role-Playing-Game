package com.deeps.rpg.fight;

import com.deeps.rpg.character.superhero.SuperHero;
import com.deeps.rpg.character.supervillain.SuperVillain;
import com.deeps.rpg.character.supervillain.repo.SuperVillainRepository;
import com.deeps.rpg.constants.GameConstants;
import com.deeps.rpg.exception.GameException;
import com.deeps.rpg.helper.GameViewHelper;
import com.deeps.rpg.player.Player;
import com.deeps.rpg.powermoves.PowerMoves;
import com.deeps.rpg.powermoves.PowerMovesRepository;
import com.deeps.rpg.utils.CommandLineColors;

import java.util.*;
import java.util.stream.Collectors;


/*Class represent the battle or fight between the super villain and the super Hero

Done as  a part of MVP(User ztory 3)*/

public class Fight {

    Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();


    private SuperHero superHero;
    private SuperVillain superVillain;
    private int superHeroLife;
    private int superVillainLife;
    private int superVillainLastMoveEffect;
    private int superHeroLastMoveEffect;
    private int superHeroContinuousBlock;
    private int superVillainContinuousBlock;
    private Object activeCharacter;
    private List<PowerMoves> superVillainMovesList;
    private List<PowerMoves> superHeroMovesList;
    private String userInput = "";

    //constructor for instantiating the Fight
    public Fight(Player userPlayer) {
        superHero = userPlayer.getSuperHero();
        superHeroLife = 100;
        superVillainLife = 100;
        superVillainLastMoveEffect = 0;
        superHeroLastMoveEffect = 0;
        superHeroContinuousBlock = 0;
        superVillainContinuousBlock = 0;
        superVillainMovesList = PowerMovesRepository.getInstance().getAvailablePowerMovesList();
        superHeroMovesList = superHero.getEssentialTrainings().stream()
                .flatMap(t -> t.moves.stream())
                .collect(Collectors.toList());
        chooseEnemy(superHero);
    }

    /* Method choose superVillains according to superhero level
     *  parameters : superHero - superHero selected by user
     *
     * */
    private void chooseEnemy(SuperHero superHero) {
        int level = superHero.getLevel();
        List<SuperVillain> availableEnemies = SuperVillainRepository.getInstance().getSuperVillainsByLevel(level);

        //choose random enemy
        if(availableEnemies!= null && availableEnemies.size() > 0){
            superVillain = availableEnemies.get(random.nextInt(availableEnemies.size()));
        }

        //show superVillains details to user
        GameViewHelper.showSuperVillainDetails(superVillain);
    }

    /* Method that Start fight.
     *  - show fight instructions.
     *  - perform toss.
     *  - manage current attacker(at a time only one Hero/Enemy can attack).
     *  - update Hero strength according to fight result.
     *
     * */
    public boolean startFight() {
        //show fight rules
        GameViewHelper.showFightInstructions();

        //toss to choose who will attack first
        if(GameViewHelper.performToss()){
            System.out.println();
            System.out.println("Yes, You won the toss and get chance to hit first\n\n"+ CommandLineColors.ANSI_GREEN);
            activeCharacter = superHero;
        }else {
            System.out.println("You loose the toss and enemy get chance to hit first\n\n"+CommandLineColors.ANSI_RED);
            activeCharacter = superVillain;
        }

        //start fight
        if(activeCharacter instanceof  SuperVillain){

            while (superHeroLife > 0 && superVillainLife > 0){
                superVillainPerformHit();
                try {
                    heroPerformHit();
                } catch (GameException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println("Hero life : " + CommandLineColors.ANSI_GREEN +(superHeroLife > 0 ? superHeroLife : 0) + " Enemy life : " + CommandLineColors.ANSI_RED +(superVillainLife > 0 ? superVillainLife : 0));
                System.out.println();
            }
        }else {
            while (superHeroLife > 0 && superVillainLife > 0){
                try {
                    heroPerformHit();
                } catch (GameException e) {
                    System.err.println(e.getMessage());
                }
                superVillainPerformHit();
                System.out.println("Hero life : " +CommandLineColors.ANSI_GREEN + (superHeroLife > 0 ? superHeroLife : 0) + " Enemy life : " +  CommandLineColors.ANSI_RED +(superVillainLife > 0 ? superVillainLife : 0));
                System.out.println();
            }
        }


        // decide winner
        Object winner = null;
        if(superHeroLife > superVillainLife){
            winner = superHero;
            //increase superHero strength
            superHero.addStrength(5); //5 is basic strength increment value
            System.out.println("\nWINNER, yes, You won :)"+CommandLineColors.ANSI_BLUE);
            return  true;
        }else {
            winner = superVillain;
            //decrease hero strength
            superHero.subtractStrength(5); //5 is basic strength increment value
            System.out.println("\nNever mind, you loose. Try hard again"+CommandLineColors.ANSI_CYAN);
            return  false;
        }
    }



   /* method to perform superVillain attack and calculate supervillain life depends on damage by superHero.*/
    private void superVillainPerformHit() {
        PowerMoves move;
        if (activeCharacter instanceof SuperVillain) {

            //choose random moves from all available moves
            int i = random.nextInt(superVillainMovesList.size());
            move = superVillainMovesList.get(i);
            System.out.println(move.getMoveSymbol());

            //if block then no damage
            if (Objects.equals(move.getMoveId(), GameConstants.BLOCK_MOVE_ID) && superVillainContinuousBlock < GameConstants.MAX_BLOCK_LIMIT) {
                superHeroLastMoveEffect = 0;
                superVillainLastMoveEffect = 0;
                superVillainLife -= superHeroLastMoveEffect;
                superVillainContinuousBlock++;

            } else {
                //update superHero's life
                superVillainLastMoveEffect = getTotalDamage(move.getMoveDamageLevel(), superVillain.getStrength());
                superVillainLife -= superHeroLastMoveEffect;
                superVillainContinuousBlock = 0;
            }

            //change activeCharacter
            activeCharacter = superHero;
        }
    }


    /*Method to perform SuperHero and calculate SuperHero life depends on damage by SuperVillain.
    ask SuperHero for his attacking move.
     only moves that are mastered in training are valid.
    delay in attack(> 6 sec.) cause damage to Hero.*/
    private void heroPerformHit() throws GameException {
        PowerMoves move = null;

        Timer timer = new Timer();
        TimerTask inputTImeWarningTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("You are taking too much time to hit. it will reduce your health."+CommandLineColors.ANSI_RED);
                superHeroLife -= 5;
            }
        };
        timer.schedule(inputTImeWarningTask, GameConstants.USER_INPUT_TIME_WINDOW, GameConstants.USER_INPUT_TIME_WINDOW);

        String choice = "";
        while (true){
            //prompt user for move
            System.out.println("Your move: "+CommandLineColors.ANSI_BLUE);
            userInput = scanner.nextLine();

            //check if move is valid.
            move = superHeroMovesList.stream()
                    .filter(m -> m.getMoveSymbol().equalsIgnoreCase(userInput))
                    .findFirst()
                    .orElse(null);

            if(move == null){
                timer.cancel();
                throw new GameException("No such move exist. try another one hurry!!"+CommandLineColors.ANSI_RED);
            }else {
                //valid move
                timer.cancel();
                break;
            }
        }

        //Block can save superHero from attack(No damage) but more than 2 block consecutive block not effective.
        if(Objects.equals(move.getMoveId(), GameConstants.BLOCK_MOVE_ID) && superHeroContinuousBlock < GameConstants.MAX_BLOCK_LIMIT){
            superVillainLastMoveEffect = 0;
            superHeroLastMoveEffect = 0;
            superHeroLife -= superVillainLastMoveEffect;
            superHeroContinuousBlock++;
            if(superHeroContinuousBlock == GameConstants.MAX_BLOCK_LIMIT){
                System.out.println("Next continuous block will cause you damage"+CommandLineColors.ANSI_RESET);
            }
        }else {
            //update superHero's life
            superHeroLastMoveEffect = getTotalDamage(move.getMoveDamageLevel(), superHero.getStrength());
            superHeroLife -= superVillainLastMoveEffect;
            superHeroContinuousBlock = 0;
        }
        activeCharacter = superVillain;
    }

    //calculate damage by attack depends on move and attacker strength.
    private int getTotalDamage(int moveDamageLevel, int strength) {
        return moveDamageLevel + strength * moveDamageLevel / 10;
    }






}
