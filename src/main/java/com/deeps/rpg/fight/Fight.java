package com.deeps.rpg.fight;

import com.deeps.rpg.character.superhero.SuperHero;

/*Interface represent the battle or fight between the super villain and the super Hero
Done as  a part of MVP(User ztory 3)*/
public interface Fight {
    public void chooseEnemy(SuperHero superHero);
    public boolean startFight();


}
