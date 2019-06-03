package com.deeps.rpg.powers;


import java.io.Serializable;

/*class to represt the Super Power of the super heros
* Part of MVP(USer STory 2 and 3)
* */
public class SuperPower implements Serializable {
    private  String name;
    private PowerCategory category;


    //Constructor
    public SuperPower(String name, PowerCategory category) {
        this.name = name;
        this.category = category;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PowerCategory getCategory() {
        return category;
    }

    public void setCategory(PowerCategory category) {
        this.category = category;
    }
}
