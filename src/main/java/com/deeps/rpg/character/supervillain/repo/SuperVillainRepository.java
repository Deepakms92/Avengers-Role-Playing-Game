package com.deeps.rpg.character.supervillain.repo;

import com.deeps.rpg.character.supervillain.SuperVillain;
import com.deeps.rpg.character.supervillain.SuperVillainBuilder;

import java.util.ArrayList;
import java.util.List;

/* Repository for heroes
 - Contains predefined superHeroes(Acts as a SuperHero DB) and some of the utility method to get the SuperHeros
 - Singleton class
 -Part of MVP(User Story 3)

* */

public class SuperVillainRepository {

    private static SuperVillainRepository INSTANCE;
    private List<SuperVillain> activeSuperVillains = new ArrayList<>();
    private List<String> activeSuperVillainsNames = new ArrayList<>();

    //private constructor
    private SuperVillainRepository() {
        this.activeSuperVillainsNames.add("Hydra");
        this.activeSuperVillainsNames.add("Winter Soldier");
        this.activeSuperVillainsNames.add("Thanos");
        this.activeSuperVillainsNames.add("Ultron");
        this.activeSuperVillainsNames.add("Loki");
        this.activeSuperVillainsNames.add("Kill Monger");
    }


    //factory method to get instance of Repository making this repository a singleon class as well
    public static SuperVillainRepository getInstance(){
        if(null == INSTANCE){
            INSTANCE = new SuperVillainRepository();
        }
        return INSTANCE;
    }



    //method to get a list of all active super Villains
    public List<SuperVillain> getActiveSuperVillains() {
        return activeSuperVillains;
    }

    //method to get a list of all active super Villains names
    public List<String> getActiveSuperVillainsNames() {
        return activeSuperVillainsNames;
    }


    //Method to get Kill Monger
    public SuperVillain getKillMonger() {
        SuperVillainBuilder killMongerBuilder= new SuperVillainBuilder("Kill Monger");
        killMongerBuilder.setAge(35);
        killMongerBuilder.setGender("M");
        killMongerBuilder.setHeight(5.8F);
        killMongerBuilder.setWeight(69);
        killMongerBuilder.setRealName("Erik");
        killMongerBuilder.setPunchLine("Wakanda i Mine YO!");
        killMongerBuilder.setStrength(15);
        return killMongerBuilder.build();
    }

    //Method to get Thanos
    public SuperVillain getThanos() {
        SuperVillainBuilder thanosBuilder= new SuperVillainBuilder("Thanos");
        thanosBuilder.setAge(500);
        thanosBuilder.setGender("M");
        thanosBuilder.setHeight(10.8F);
        thanosBuilder.setWeight(963);
        thanosBuilder.setRealName("Dione");
        thanosBuilder.setPunchLine("Snap!and make the world a better place");
        thanosBuilder.setStrength(70);
        return thanosBuilder.build();
    }

    //Method to get Ultron
    public SuperVillain getUltron() {
        SuperVillainBuilder ultronBuilder= new SuperVillainBuilder("Ultron");
        ultronBuilder.setAge(40);
        ultronBuilder.setGender("M");
        ultronBuilder.setHeight(5.8F);
        ultronBuilder.setWeight(90);
        ultronBuilder.setRealName("Ultron");
        ultronBuilder.setPunchLine("Kill Avengers");
        ultronBuilder.setStrength(20);
        return ultronBuilder.build();
    }

    //Method to get Winter SOldier

    public SuperVillain getWinterSoldier() {
        SuperVillainBuilder killMongerBuilder= new SuperVillainBuilder("Winter Soldier");
        killMongerBuilder.setAge(34);
        killMongerBuilder.setGender("M");
        killMongerBuilder.setHeight(6.2F);
        killMongerBuilder.setWeight(80);
        killMongerBuilder.setRealName("Bucky Barnes");
        killMongerBuilder.setPunchLine("I don't talk \"Serious Face\"");
        killMongerBuilder.setStrength(15);
        return killMongerBuilder.build();
    }

    //Method to get Loki

    public SuperVillain getLoki() {
        SuperVillainBuilder lokiBuilder= new SuperVillainBuilder("Loki");
        lokiBuilder.setAge(200);
        lokiBuilder.setGender("M");
        lokiBuilder.setHeight(5.8F);
        lokiBuilder.setWeight(100);
        lokiBuilder.setRealName("Loji");
        lokiBuilder.setPunchLine("Asgard is Mine");
        lokiBuilder.setStrength(40);
        return lokiBuilder.build();
    }

    //Method to get Hydra
    public SuperVillain getHydra() {
        SuperVillainBuilder hydraBuilder= new SuperVillainBuilder("Hydra");
        hydraBuilder.setAge(200);
        hydraBuilder.setGender("M");
        hydraBuilder.setHeight(6.8F);
        hydraBuilder.setWeight(0);
        hydraBuilder.setRealName("Hydra");
        hydraBuilder.setPunchLine("Hail Hydra");
        hydraBuilder.setStrength(25);
        return hydraBuilder.build();
    }


    //Get the Supervillains based on the Level of them.
    public List<SuperVillain>  getSuperVillainsByLevel(int level){
        List<SuperVillain> supervillains = new ArrayList<>();

        switch (level){
            case 1:
                supervillains.add(getWinterSoldier());
                supervillains.add(getUltron());
                supervillains.add(getKillMonger());

            case 2:
                supervillains.add(getHydra());
                supervillains.add(getLoki());

            case 3:
                supervillains.add(getThanos());

        }
        return supervillains;
    }

}
