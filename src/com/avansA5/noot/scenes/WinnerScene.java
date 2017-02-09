
package com.avansA5.noot.scenes;

import com.avansA5.noot.Program;
import com.avansA5.noot.managers.ControlManager;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.managers.ScoreManager;
import com.avansA5.noot.objects.Background;
import com.avansA5.noot.objects.Score;
import com.avansA5.noot.ui.UIButton;
import com.avansA5.noot.ui.UIString;
import com.avansA5.noot.util.RelativePoint;
import com.avansA5.noot.util.SoundPlayer;
import wiiusej.wiiusejevents.physicalevents.*;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.*;

import java.awt.*;

public class WinnerScene extends Scene implements WiimoteListener
{
    private int selectedOption = 1;
    private static boolean multiplayer = false;
    public static SoundPlayer soundPlayer;
    public Background background;
    private double scale;
    @Override
    public void load()
    {
        ControlManager.clearWiiMoteListener(0);
        ControlManager.clearWiiMoteListener(1);
        ControlManager.addWiimoteListener(this,0);
        soundPlayer = new SoundPlayer("res//WinningMusic.wav");
        soundPlayer.loop();
        background = new Background("res//VictoryBG.png");
        sprites.add(background);
        scale = background.getScale();



        uiElements.add(new UIString("Begin Scherm", new RelativePoint(50,65), "Matura MT Script Capitals",(int)(80*scale), Color.WHITE));
        uiElements.add(new UIButton(new Rectangle(100,100,20,20) , Color.WHITE,new RelativePoint(34,57), new UIString("", new Point(0,0)), true));
        uiElements.add(new UIString("  Herstart", new RelativePoint(50,80), "Matura MT Script Capitals",(int)(80*scale), Color.BLACK));
        uiElements.add(new UIButton(new Rectangle(100,100,20,20) , Color.WHITE, new RelativePoint(40,72),new UIString("", new Point(0,0)), false));
        uiElements.add(new UIString("  Stoppen", new RelativePoint(50,95), "Matura MT Script Capitals",(int)(80*scale), Color.BLACK));
        uiElements.add(new UIButton(new Rectangle(100,100,20,20) , Color.WHITE, new RelativePoint(40,87),new UIString("", new Point(0,0)), false));


        uiElements.add(new UIString("Highscore: " + ScoreManager.getHighScore(), new RelativePoint(50,40), "Matura MT Script Capitals",(int)(80*scale), Color.yellow.brighter()));
        uiElements.add(new UIString("Score: " + Score.getScore(), new RelativePoint(52,48), "Matura MT Script Capitals",(int)(80*scale), Color.orange.darker().darker()));

    }

    @Override
    public void unload()
    {
        soundPlayer.stop();
        sprites.removeAll(sprites);
        uiElements.removeAll(uiElements);
    }

    @Override
    public void onButtonsEvent(WiimoteButtonsEvent wiimoteButtonsEvent)
    {

        if(wiimoteButtonsEvent.isButtonUpJustReleased() && selectedOption == 2)
        {
            selectedOption = 1;
            uiElements.get(0).setColor(Color.WHITE);
            uiElements.get(1).setVisible(true);
            uiElements.get(2).setColor(Color.BLACK);
            uiElements.get(3).setVisible(false);
            uiElements.get(4).setColor(Color.BLACK);
            uiElements.get(5).setVisible(false);
        }
        if(wiimoteButtonsEvent.isButtonUpJustReleased() && selectedOption == 3)
        {
            selectedOption = 2;
            uiElements.get(0).setColor(Color.BLACK);
            uiElements.get(1).setVisible(false);
            uiElements.get(2).setColor(Color.WHITE);
            uiElements.get(3).setVisible(true);
            uiElements.get(4).setColor(Color.BLACK);
            uiElements.get(5).setVisible(false);
        }

        if(wiimoteButtonsEvent.isButtonDownJustReleased() && selectedOption == 1)
        {
            selectedOption = 2;
            uiElements.get(0).setColor(Color.BLACK); // tekst
            uiElements.get(1).setVisible(false);
            uiElements.get(2).setColor(Color.WHITE);
            uiElements.get(3).setVisible(true);
            uiElements.get(4).setColor(Color.BLACK);
            uiElements.get(5).setVisible(false);

        }
        if(wiimoteButtonsEvent.isButtonDownJustPressed() && selectedOption == 2)
        {
            selectedOption = 3;
            uiElements.get(0).setColor(Color.BLACK);
            uiElements.get(1).setVisible(false);
            uiElements.get(2).setColor(Color.BLACK);
            uiElements.get(3).setVisible(false);
            uiElements.get(4).setColor(Color.WHITE);
            uiElements.get(5).setVisible(true);
        }

        if(wiimoteButtonsEvent.isButtonAJustPressed())
        {
            switch(selectedOption)
            {
                case 1:
                    SceneManager.setScene("StartScene");
                    break;
                case 2:
                    SceneManager.setScene("TutorialScene");
                    break;
                case 3:
                    Program.stop();

            }
        }
    }


    @Override
    public void onIrEvent(IREvent irEvent)
    {
    }

    @Override
    public void onMotionSensingEvent(MotionSensingEvent motionSensingEvent) {

    }

    @Override
    public void onExpansionEvent(ExpansionEvent expansionEvent) {

    }

    @Override
    public void onStatusEvent(StatusEvent statusEvent) {

    }

    @Override
    public void onDisconnectionEvent(DisconnectionEvent disconnectionEvent) {

    }

    @Override
    public void onNunchukInsertedEvent(NunchukInsertedEvent nunchukInsertedEvent) {

    }

    @Override
    public void onNunchukRemovedEvent(NunchukRemovedEvent nunchukRemovedEvent) {

    }

    @Override
    public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent guitarHeroInsertedEvent) {

    }

    @Override
    public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent guitarHeroRemovedEvent) {

    }

    @Override
    public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent classicControllerInsertedEvent) {

    }

    @Override
    public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent classicControllerRemovedEvent) {

    }
}
