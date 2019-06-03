package com.deeps.rpg.character.superhero;

import com.deeps.rpg.character.CharacterLevel;
import com.deeps.rpg.powers.SuperPower;
import com.deeps.rpg.training.AbstractTraining;
import com.deeps.rpg.training.repo.TrainingRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*Class represent SuperHero and its characteristics
 strength - Increase and decrease on each win or loose in fight.
-Decide damage level of attacks.
- SuperHero level depends on it.
--Part of MVP(User Story 1)
*/
public class SuperHero implements Serializable {

    private static final int BASIC_TRAINING_COMPLETE_STRENGTH = 5;


    private String name;
    private String realName;
    private String gender;
    private String favDailogue;
    private Float height;
    private int weight;
    private int age;
    private int strength;
    private int level;
    private boolean trainingCompleted;
    private SuperPower[] superPowers;
    private List<AbstractTraining> essentialTrainings = new ArrayList<>();



    //constructor to build the SuperHero
    public SuperHero(SuperHeroBuilder superHeroBuilder) {
        this.name = superHeroBuilder.getName();
        this.realName = superHeroBuilder.getRealName();
        this.gender = superHeroBuilder.getGender();
        this.favDailogue = superHeroBuilder.getPunchLine();
        this.height = superHeroBuilder.getHeight();
        this.weight = superHeroBuilder.getWeight();
        this.age = superHeroBuilder.getAge();
        this.superPowers= superHeroBuilder.getSuperPowers();
        //to add basic training to SuperHero(Part of User Story 2)
        this.essentialTrainings.add(TrainingRepository.getInstance().getBasicTraining());
        this.essentialTrainings.addAll(superHeroBuilder.getTrainings());
        trainingCompleted = false;
        updateLevel();

    }


    /*Getter and Setters*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFavDailogue() {
        return favDailogue;
    }

    public void setFavDailogue(String favDailogue) {
        this.favDailogue = favDailogue;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
        updateLevel();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void subtractStrength(int strength) {
        this.strength = this.strength - strength;
        updateLevel();
    }

    public void addStrength(int strength) {
        this.strength =  this.strength + strength;
        updateLevel();
    }

    public List<AbstractTraining> getEssentialTrainings() {
        return essentialTrainings;
    }

    public void setEssentialTrainings(List<AbstractTraining> essentialTrainings) {
        this.essentialTrainings.addAll(essentialTrainings);
    }

    public boolean isTrainingCompleted() {
        return trainingCompleted;
    }

    public void setTrainingCompleted(boolean trainingCompleted) {
        this.trainingCompleted = trainingCompleted;
        if(strength < BASIC_TRAINING_COMPLETE_STRENGTH){
            setStrength(BASIC_TRAINING_COMPLETE_STRENGTH);
        }
    }

    public SuperPower[] getSuperPowers() {
        return superPowers;
    }

    public void setSuperPowers(SuperPower[] superPowers) {
        this.superPowers = superPowers;
    }


    //method to update user level depending on SuperHeros strength(Part of User Story 3)
    private void updateLevel(){
        if(strength >= 0 && strength <= 10){
            level = CharacterLevel.LEVEL_BASIC;
        }else if(strength > 10 && strength <= 30){
            level =  CharacterLevel.LEVEL_MASTER;
        }else {
            level =  CharacterLevel.LEVEL_PRO;
        }
    }


    @Override
    public String toString() {
        return "SuperHero{" +
                "name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", gender='" + gender + '\'' +
                ", favDailogue='" + favDailogue + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", strength=" + strength +
                ", level=" + level +
                ", trainingCompleted=" + trainingCompleted +
                ", superPowers=" + Arrays.toString(superPowers) +
                '}';
    }
}
