package com.deeps.rpg.character.supervillain;


import com.deeps.rpg.character.CharacterLevel;

/*Class represent SuperVillain and its characteristics
 strength - Increase and decrease on each win or loose in fight.
-Decide damage level of attacks.
- SuperHero level depends on it.
--Part of MVP(User Story 3)
*/
public class SuperVillain {

    private String name;
    private String realName;
    private String gender;
    private String favDialogue;
    private Float height;
    private int weight;
    private int age;
    private int strength;
    private int level;


    //Constructors to build the SuperVillains.
    public SuperVillain(SuperVillainBuilder superVillainBuilder) {
        this.name = superVillainBuilder.getName();
        this.realName = superVillainBuilder.getRealName();
        this.gender = superVillainBuilder.getGender();
        this.favDialogue = superVillainBuilder.getPunchLine();
        this.height = superVillainBuilder.getHeight();
        this.weight = superVillainBuilder.getWeight();
        this.age = superVillainBuilder.getAge();
        this.strength = superVillainBuilder.getStrength();
        updateLevel();

    }

    //Getter and Setters
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

    public String getFavDialogue() {
        return favDialogue;
    }

    public void setFavDialogue(String favDialogue) {
        this.favDialogue = favDialogue;
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

    @Override
    public String toString() {
        return "SuperVillain{" +
                "name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", gender='" + gender + '\'' +
                ", favDialogue='" + favDialogue + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", strength=" + strength +
                ", level=" + level +
                '}';
    }

    private void updateLevel(){
        if(strength > 0 && strength <= 20){
            level = CharacterLevel.LEVEL_BASIC;
        }else if(strength > 20 && strength <= 50){
            level = CharacterLevel.LEVEL_MASTER;
        }else {
            level = CharacterLevel.LEVEL_PRO;
        }
    }
}
