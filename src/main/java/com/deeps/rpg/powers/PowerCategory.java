package com.deeps.rpg.powers;

import java.io.Serializable;

/*Enum for categorizing the power based on the damageLevel
* Part of MVP (User Story 2 and 3)
* */
public enum PowerCategory implements Serializable {

    LOW(1), MEDIUM(2), HIGH(3);

    private final int damageLevel;

    PowerCategory(int damageLevel){
        this.damageLevel=damageLevel;
    }
}
