package com.deeps.rpg.character.superhero;

import com.deeps.rpg.constants.GameConstants;
import com.deeps.rpg.powers.SuperPower;
import com.deeps.rpg.training.AbstractTraining;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*Class to create or build a new SuperHero
-Contains all the characteristics similar to that
of Super Hero Class,

--Part of MVP(User Story 1)*/
public class SuperHeroBuilder implements Serializable {


    private String name;
    private String realName;
    private String Gender;
    private String punchLine;
    private Float height;
    private int Weight;
    private int age;
    private SuperPower[] superPowers;
    private List<AbstractTraining> trainings = new ArrayList<>();


    //constructors
    public SuperHeroBuilder() {
    }

    public SuperHeroBuilder(String name) {
        this.name = name;
    }

    //to build the new charcacter as a Super HEro
    public SuperHero build(){
        return new SuperHero(this);
    }


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
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPunchLine() {
        return punchLine;
    }

    public void setPunchLine(String punchLine) {

        if(punchLine.isEmpty()){
            punchLine = GameConstants.DEFAULT_DIALOGUE_FOR_HERO;
        }
        this.punchLine = punchLine;

    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SuperPower[] getSuperPowers() {
        return superPowers;
    }

    public void setSuperPowers(SuperPower[] superPowers) {
        this.superPowers = superPowers;
    }

    public void setTrainings(List<AbstractTraining> trainings) {
        if(trainings != null && trainings.size() > 0)
            this.trainings.addAll(trainings);

    }

    public List<AbstractTraining> getTrainings() {
        return trainings;
    }


    @Override
    public String toString() {
        return "SuperHeroBuilder{" +
                ", name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", punchLine='" + punchLine + '\'' +
                ", height=" + height +
                ", Weight=" + Weight +
                ", age=" + age +
                ", superPowers=" + Arrays.toString(superPowers) +
                '}';
    }
}
