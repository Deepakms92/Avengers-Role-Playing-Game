package com.deeps.rpg.character.supervillain;


import com.deeps.rpg.character.supervillain.SuperVillain;
import com.deeps.rpg.constants.GameConstants;

/*Class to create or build a new SuperVillain
-Contains all the characteristics similar to that
of Super Villain Class,

--Part of MVP(User Story 3)*/
public class SuperVillainBuilder {

    private String name;
    private String realName;
    private String Gender;
    private String punchLine;
    private Float height;
    private int Weight;
    private int age;
    private int strength;

//Constructors
    public SuperVillainBuilder(String name) {
        this.name = name;
    }

    public SuperVillainBuilder() {
    }

    //Method to build the SuperVillain from a new SuperVillain Created.
    public SuperVillain build(){
        return new SuperVillain(this);
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

    //adding defalt ounchLine if no punchLine.
    public void setPunchLine(String punchLine) {
        if(punchLine.isEmpty()){
            punchLine = GameConstants.DEFAULT_DIALOGUE_FOR_VILLAIN;
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
