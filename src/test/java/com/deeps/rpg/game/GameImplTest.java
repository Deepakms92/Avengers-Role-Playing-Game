package com.deeps.rpg.game;

import com.deeps.rpg.constants.GameConstants;
import com.deeps.rpg.exception.PlayerException;
import com.deeps.rpg.game.impl.GameImpl;
import com.deeps.rpg.helper.GameViewHelper;
import com.deeps.rpg.player.Player;
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
import java.io.File;
import java.util.NoSuchElementException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GameViewHelper.class, GameImpl.class})
public class GameImplTest {

//    @Mock(serializable = true)

    @InjectMocks
    GameImpl gameCreator;

    @Mock
    GameImpl gameImple;

    @Mock(serializable = true)
    Player player;



    @Before
    public void setUp() throws Exception {
        player= new Player("DeepakKumar");
        player.setPlayerName("DeepakKumar");
    }



    @Test
    public void testcheckIfPlayerAlreadyExist() throws Exception {
        Whitebox.invokeMethod(gameCreator,"checkIfPlayerAlreadyExist","Deepak");
        String input= Whitebox.invokeMethod(gameCreator,"checkIfPlayerAlreadyExist",Mockito.anyString());
        Assert.assertEquals(null,input);

    }


    @Test
    public void testRetriveSavedPlayerWhichisPresent(){
        File file = new File(GameConstants.SAVED_PLAYER_DIR_NAME+"/Deepak.dat");
        gameCreator.retrieveSavedPlayer(file);

    }
    @Test
    public void testRetriveSavedPlayerWhichisNotPresent(){
        File file = new File(GameConstants.SAVED_PLAYER_DIR_NAME+"/some.dat");
        gameCreator.retrieveSavedPlayer(file);

    }

    @Test
    public void testCreateNewPlayer() throws Exception {
        System.setIn(new ByteArrayInputStream("Deepak2".getBytes()));
        Whitebox.invokeMethod(gameCreator,"createNewPlayer");

    }

    @Test(expected = PlayerException.class)
    public void tesCreateNewplayer_Exception() throws Exception {
        System.setIn(new ByteArrayInputStream("Deepak".getBytes()));
        Whitebox.invokeMethod(gameCreator,"createNewPlayer");
    }

    @Test
    public void testChooseExistingPlayer() throws Exception {
        System.setIn(new ByteArrayInputStream("Deepak".getBytes()));
        Whitebox.invokeMethod(gameCreator,"chooseExistingPlayer");
        PowerMockito.verifyPrivate(gameCreator,Mockito.times(1)).invoke("checkIfPlayerAlreadyExist","Deepak");
    }
    @Test(expected = PlayerException.class)
    public void testChooseExistingPlayer_Exception() throws Exception {
        System.setIn(new ByteArrayInputStream("Deepak23".getBytes()));
        Whitebox.invokeMethod(gameCreator,"chooseExistingPlayer");
        PowerMockito.verifyPrivate(gameCreator,Mockito.times(1)).invoke("checkIfPlayerAlreadyExist","Deepak23");
    }

    @Test
    public void testShowChoosePlayerMenu() throws Exception {
        System.setIn(new ByteArrayInputStream(("1"+System.lineSeparator() + "Deepak46").getBytes()));
        Whitebox.invokeMethod(gameCreator,"showChoosePlayerMenu");

    }
    @Test
    public void testCreateOrChoosePlayer_FirstChoice() throws Exception {

        System.setIn(new ByteArrayInputStream(("Deepak56"+System.lineSeparator() + "Deepak56").getBytes()));
        gameCreator.createOrChoosePlayer(1);
        PowerMockito.verifyPrivate(gameCreator,Mockito.times(1)).invoke("createNewPlayer");

    }

    @Test
    public void testCreateOrChoosePlayer_FirstChoice_Exception() throws Exception {

        System.setIn(new ByteArrayInputStream(("Deepak"+System.lineSeparator() + "1"+System.lineSeparator()+"Deepak56"+System.lineSeparator()+"Deepak56").getBytes()));
        gameCreator.createOrChoosePlayer(1);
        PowerMockito.verifyPrivate(gameCreator,Mockito.times(1)).invoke("createNewPlayer");

    }

    @Test
    public void testCreateOrChoosePlayer_SecondChoice() throws Exception {

        System.setIn(new ByteArrayInputStream(("Deepak"+System.lineSeparator() + "Deepak").getBytes()));
        gameCreator.createOrChoosePlayer(2);
        PowerMockito.verifyPrivate(gameCreator,Mockito.times(1)).invoke("chooseExistingPlayer");

    }

    @Test
    public void testCreateNewHero() throws Exception {
        System.setIn(new ByteArrayInputStream(("Deepak"+System.lineSeparator() + "Deepak"+System.lineSeparator() + "M"+System.lineSeparator()+"5.9F"
        +System.lineSeparator()+"100"+System.lineSeparator()+"Hi"+System.lineSeparator()+"BOPM"+System.lineSeparator()+"M").getBytes()));
        Whitebox.invokeMethod(gameCreator,"createNewHero");
    }

    @Test(expected = NumberFormatException.class)
    public void testCreateNewHero_HeightInInString() throws Exception {
        System.setIn(new ByteArrayInputStream(("Deepak"+System.lineSeparator() + "Deepak"+System.lineSeparator() + "M"+System.lineSeparator()+"five"
                +System.lineSeparator()+"100"+System.lineSeparator()+"Hi"+System.lineSeparator()+"BOPM"+System.lineSeparator()+"M").getBytes()));
        Whitebox.invokeMethod(gameCreator,"createNewHero");
    }


    @Test
    public void testSaveGame(){
        gameCreator.saveGame(player);
    }
}