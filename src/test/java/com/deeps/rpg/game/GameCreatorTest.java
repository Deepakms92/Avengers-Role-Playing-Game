package com.deeps.rpg.game;

import com.deeps.rpg.game.impl.GameImpl;
import com.deeps.rpg.helper.GameViewHelper;
import com.deeps.rpg.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.ByteArrayInputStream;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GameViewHelper.class, GameImpl.class})
public class GameCreatorTest {

//    @Mock(serializable = true)
    @Mock(answer = Answers.RETURNS_MOCKS)
    GameImpl gameCreator;

    @Mock(serializable = true)
    Player player;

    @Before
    public void setUp() throws Exception {
        player= new Player("Deepak");
        player.setPlayerName("Deepak");
    }

    @Test
    public void testStatrt() throws Exception {

        System.setIn(new ByteArrayInputStream(("1" + System.lineSeparator() + "Deepak"+System.lineSeparator() + "1"
                +System.lineSeparator() +"1"+System.lineSeparator()+"1"+System.lineSeparator() + "1" + System.lineSeparator() + "P" +System.lineSeparator() +"K"
                        +System.lineSeparator() +"B").getBytes())
                );

       // System.setIn(new ByteArrayInputStream("Deepak".getBytes()));
        gameCreator.start();

        PowerMockito.verifyPrivate(Mockito.times(1)).invoke("showChoosePlayerMenu");
//        System.setIn(System.in);
        PowerMockito.verifyPrivate(Mockito.times(1)).invoke("showMainMenu");

    }

    @Test
    public void testFinishGame() throws Exception {


        Whitebox.invokeMethod(gameCreator,"finishGame");
        PowerMockito.verifyPrivate(gameCreator,Mockito.times(1)).invoke("saveGame",Mockito.any());
        PowerMockito.mockStatic(GameViewHelper.class);
        PowerMockito.verifyStatic(Mockito.times(1));
        GameViewHelper.sayGoodBye();

    }


    @Test
    public void testcheckIfPlayerAlreadyExist() throws Exception {
        Whitebox.invokeMethod(gameCreator,"checkIfPlayerAlreadyExist",Mockito.anyString());

    }
}