package com.deeps.rpg.powermoves;

import java.io.Serializable;

/*
class represent move which are used in fight
 each move has its damage level.

 Part of MVP( User Story 2 and 3)
*/

public class PowerMoves implements Serializable {

    private String moveId;
    private String moveDescription = "No description Available";
    private String moveSymbol;
    private int moveDamageLevel;
    private boolean moveMastered = false;

    /*public Constructors*/
    public PowerMoves(String moveId, String moveSymbol, int moveDamageLevel) {
        this.moveId = moveId;
        this.moveSymbol = moveSymbol;
        this.moveDamageLevel = moveDamageLevel;
    }

    /*deafult constructors*/
    PowerMoves(String moveId, String moveDescription, String moveSymbol, int moveDamageLevel) {
        this.moveId = moveId;
        this.moveDescription = moveDescription;
        this.moveSymbol = moveSymbol;
        this.moveDamageLevel = moveDamageLevel;
    }


    //Getters and Setters
    public String getMoveId() {
        return moveId;
    }

    public void setMoveId(String moveId) {
        this.moveId = moveId;
    }

    public String getMoveDescription() {
        return moveDescription;
    }

    public void setMoveDescription(String moveDescription) {
        this.moveDescription = moveDescription;
    }

    public String getMoveSymbol() {
        return moveSymbol;
    }

    public void setMoveSymbol(String moveSymbol) {
        this.moveSymbol = moveSymbol;
    }

    public int getMoveDamageLevel() {
        return moveDamageLevel;
    }

    public void setMoveDamageLevel(int moveDamageLevel) {
        this.moveDamageLevel = moveDamageLevel;
    }

    public boolean isMoveMastered() {
        return moveMastered;
    }

    public void setMoveMastered(boolean moveMastered) {
        this.moveMastered = moveMastered;
    }
}
