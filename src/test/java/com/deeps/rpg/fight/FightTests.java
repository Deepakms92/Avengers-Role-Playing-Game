package com.deeps.rpg.fight;

import com.deeps.rpg.character.superhero.SuperHero;
import com.deeps.rpg.character.superhero.SuperHeroBuilder;
import com.deeps.rpg.character.supervillain.SuperVillain;
import com.deeps.rpg.character.supervillain.SuperVillainBuilder;
import com.deeps.rpg.character.supervillain.repo.SuperVillainRepository;
import com.deeps.rpg.fight.impl.FightImpl;
import com.deeps.rpg.helper.GameViewHelper;
import com.deeps.rpg.player.Player;
import com.deeps.rpg.powermoves.PowerMovesRepository;
import com.deeps.rpg.training.AbstractTraining;
import com.deeps.rpg.training.repo.TrainingRepository;
import com.deeps.rpg.training.types.BasicTraining;
import com.deeps.rpg.training.types.FlyTraining;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.ByteArrayInputStream;
import java.util.List;


@RunWith(PowerMockRunner.class)
@PrepareForTest({GameViewHelper.class})
public class FightTests {



//   @Mock(answer =Answers.RETURNS_MOCKS)
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private FightImpl fight;

    @Mock
    PowerMovesRepository powerMovesRepository;

    @Mock
    SuperVillainRepository superVillainRepository;

    @Mock(answer = Answers.RETURNS_MOCKS)
    SuperHero superHero;


    @Mock(answer = Answers.RETURNS_MOCKS)
    SuperVillain superVillain;

    SuperVillainBuilder superVillainBuilder;

    SuperHeroBuilder superHeroBuilder;

    @Mock(answer = Answers.RETURNS_MOCKS)
    Player player;


    @Mock
    AbstractTraining training;

    @Mock
    TrainingRepository repository;




    @Before
    public void setUp(){
       // fight = new Fight(player);

        superVillainBuilder= new SuperVillainBuilder();
        superVillainBuilder.setName("Hulk");
        superVillain= new SuperVillain(superVillainBuilder);
        superHeroBuilder= new SuperHeroBuilder();
        superHero=new SuperHero(superHeroBuilder);

    }




    @Test
    public void testSuperVillainPerformce() throws Exception {
        System.setIn(new ByteArrayInputStream("K".getBytes()));
        Whitebox.invokeMethod(fight,"superVillainPerformHit");
        PowerMockito.verifyPrivate(fight,Mockito.times(1)).invoke("getTotalDamage",Mockito.anyInt(),Mockito.anyInt());




    }
    @Test
    public void testChooseEnemy() throws Exception {
        fight.chooseEnemy(superHero);
        PowerMockito.mockStatic(GameViewHelper.class);
        PowerMockito.verifyStatic(GameViewHelper.class,Mockito.times(0));
        GameViewHelper.showSuperVillainDetails(superVillain);

    }

    @Test
    public void testDamageLevel() throws Exception {
        int output= Whitebox.invokeMethod(fight,"getTotalDamage",10,5);
        Assert.assertEquals(15,output);
    }

}
