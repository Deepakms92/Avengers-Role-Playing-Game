package com.deeps.rpg.character.superhero.repo;

import com.deeps.rpg.character.superhero.SuperHero;
import com.deeps.rpg.character.superhero.SuperHeroBuilder;
import com.deeps.rpg.powers.PowerCategory;
import com.deeps.rpg.powers.SuperPower;
import com.deeps.rpg.training.repo.TrainingRepository;
import com.deeps.rpg.utils.AsciiImages;

import java.util.ArrayList;
import java.util.List;

/* Repository for heroes
 - Contains predefined superHeroes(Acts as a SuperHero DB) and some of the utility method to get the SuperHeros
 - Singleton class
 -Part of MVP(User Story 1)

* */
public class SuperHeroRepository {
    private static SuperHeroRepository INSTANCE;

    private List<SuperHero> activeSuperHeroes = new ArrayList<>();
    private List<String> activeSuperHeroesNames = new ArrayList<>();

//private constructors for singleton
    private SuperHeroRepository() {
        this.activeSuperHeroesNames.add("Hulk");
        this.activeSuperHeroesNames.add("Iron Man");
        this.activeSuperHeroesNames.add("Captain America");
        this.activeSuperHeroesNames.add("Thor");
        this.activeSuperHeroesNames.add("Black Widow");

    }

    //factory method to get instance of Repository making this repository a singleon class as well
    public static SuperHeroRepository getInstance(){
        if(null == INSTANCE){
            INSTANCE = new SuperHeroRepository();
        }

        return INSTANCE;
    }


    //method to add when a new super hero is created
    public void addSuperHero(SuperHero superHero){
        if(null != superHero){
            activeSuperHeroesNames.add(superHero.getName());
            this.activeSuperHeroes.add(superHero);
        }
    }

    //method to get a list of all active super HEros
    public List<SuperHero> getActiveHeroes() {
        return activeSuperHeroes;
    }

    //method to get a list of all active super HEros names
    public List<String> getActiveHeroesNames() {
        return activeSuperHeroesNames;
    }


    //method to get  superHero by name
    public SuperHero getSuperHeroByName(String superHeroName){
        switch (superHeroName){
            case "Hulk":
                return getHulk();

            case "Iron Man":
                return getIronMan();

            case "Captain America":
                return getCaptainAmerica();

            case "Thor":
                return getThor();

            case "Black Widow":
                return getBlackWidow();

        }
        return null;
    }


    // Method That retrieves Thor
    private SuperHero getThor(){
        SuperHeroBuilder thorBuilder = new SuperHeroBuilder("Thor");
        thorBuilder.setAge(150);
        thorBuilder.setGender("M");
        thorBuilder.setHeight(5.9F);
        thorBuilder.setWeight(120);
        thorBuilder.setRealName("Thor");
        thorBuilder.setPunchLine("I am God of Thunder");
        thorBuilder.setSuperPowers(new SuperPower[]{new SuperPower("HAMMER", PowerCategory.HIGH), new SuperPower("THUNDER", PowerCategory.HIGH) });
        System.out.println(AsciiImages.THOR_IMAGE);
        return thorBuilder.build();
    }

    // Method That retrieves Hulk
    private SuperHero getHulk(){
        SuperHeroBuilder hulkBuilder = new SuperHeroBuilder("Hulk");
        hulkBuilder.setAge(45);
        hulkBuilder.setGender("M");
        hulkBuilder.setHeight(16.2F);
        hulkBuilder.setWeight(500);
        hulkBuilder.setRealName("Bruce Banner");
        hulkBuilder.setPunchLine("HULK SMAAASSSHH");
        hulkBuilder.setSuperPowers(new SuperPower[]{new SuperPower("HULK_SMASH", PowerCategory.HIGH), new SuperPower("SUPER_STRENGTH",PowerCategory.MEDIUM) });
        System.out.println(AsciiImages.HULK);
        return hulkBuilder.build();
    }

    // Method That retrieves Iron Man
    private SuperHero getIronMan(){
        SuperHeroBuilder ironManBuilder = new SuperHeroBuilder("Iron Man");
        ironManBuilder.setAge(45);
        ironManBuilder.setGender("M");
        ironManBuilder.setHeight(5.9F);
        ironManBuilder.setWeight(68);
        ironManBuilder.setRealName("Tony Stark");
        ironManBuilder.setPunchLine("I love you 3000");
        ironManBuilder.setSuperPowers(new SuperPower[]{new SuperPower("IRON_SUIT", PowerCategory.HIGH), new SuperPower("WEALTH", PowerCategory.HIGH) });
        System.out.println(AsciiImages.IRON_MAN);
        ironManBuilder.setTrainings(TrainingRepository.getInstance().getIronManTrainings());
        return ironManBuilder.build();
    }


    // Method That retrieves CAptainAmerica
    private SuperHero getCaptainAmerica(){
        SuperHeroBuilder captainAmericaBuilder = new SuperHeroBuilder("Captain America");
        captainAmericaBuilder.setAge(100);
        captainAmericaBuilder.setGender("M");
        captainAmericaBuilder.setHeight(6.2F);
        captainAmericaBuilder.setWeight(88);
        captainAmericaBuilder.setRealName("Steve Rogers");
        captainAmericaBuilder.setPunchLine("Avengers Assemble!!");
        captainAmericaBuilder.setSuperPowers(new SuperPower[]{new SuperPower("MARTIAL_ART", PowerCategory.MEDIUM), new SuperPower("SHIELD", PowerCategory.HIGH) } );
        System.out.println(AsciiImages.CAPTAIN_AMERICA);
        return captainAmericaBuilder.build();
    }

    // Method That retrieves Black Widow
    private SuperHero getBlackWidow(){
        SuperHeroBuilder blackWidowBuilder = new SuperHeroBuilder("Black Widow");
        blackWidowBuilder.setAge(35);
        blackWidowBuilder.setGender("F");
        blackWidowBuilder.setHeight(5.4F);
        blackWidowBuilder.setWeight(60);
        blackWidowBuilder.setRealName("Natasha Romanoff");
        blackWidowBuilder.setPunchLine("I will kill you.");
        blackWidowBuilder.setSuperPowers(new SuperPower[]{new SuperPower("HAND_COMBAT", PowerCategory.LOW)});
        System.out.println(AsciiImages.BLACK_WIDOW);
        return blackWidowBuilder.build();
    }

}
