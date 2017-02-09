//package com.avansA5.noot.scenes;
//
//import com.avansA5.noot.Program;
//import com.avansA5.noot.managers.ControlManager;
//import com.avansA5.noot.managers.SceneManager;
//import com.avansA5.noot.ui.UIString;
//import com.avansA5.noot.util.RelativePoint;
//import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
//import wiiusej.wiiusejevents.physicalevents.IREvent;
//import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
//import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
//import wiiusej.wiiusejevents.utils.WiimoteListener;
//import wiiusej.wiiusejevents.wiiuseapievents.*;
//
//public class StartScene extends Scene implements WiimoteListener
//{
//    @Override
//    public void load()
//    {
//        uiElements.add(new UIString("Fired Up", new RelativePoint(50,30), "Matura MT Script Capitals",50));
//        ControlManager.clearWiiMoteListener(0);
//        ControlManager.addWiimoteListener(this, 0);
//    }
//
//    @Override
//    public void unload()
//    {
//
//    }
//
//    @Override
//    public void onButtonsEvent(WiimoteButtonsEvent wiimoteButtonsEvent) {
//        if(wiimoteButtonsEvent.isButtonUpJustPressed())
//        {
//            SceneManager.setScene("TutorialScene");
//        }
//
//        if(wiimoteButtonsEvent.isButtonDownJustPressed())
//        {
//            Program.stop();
//        }
//    }
//
//    @Override
//    public void onIrEvent(IREvent irEvent) {
//
//    }
//
//    @Override
//    public void onMotionSensingEvent(MotionSensingEvent motionSensingEvent) {
//
//    }
//
//    @Override
//    public void onExpansionEvent(ExpansionEvent expansionEvent) {
//
//    }
//
//    @Override
//    public void onStatusEvent(StatusEvent statusEvent) {
//
//    }
//
//    @Override
//    public void onDisconnectionEvent(DisconnectionEvent disconnectionEvent) {
//
//    }
//
//    @Override
//    public void onNunchukInsertedEvent(NunchukInsertedEvent nunchukInsertedEvent) {
//
//    }
//
//    @Override
//    public void onNunchukRemovedEvent(NunchukRemovedEvent nunchukRemovedEvent) {
//
//    }
//
//    @Override
//    public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent guitarHeroInsertedEvent) {
//
//    }
//
//    @Override
//    public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent guitarHeroRemovedEvent) {
//
//    }
//
//    @Override
//    public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent classicControllerInsertedEvent) {
//
//    }
//
//    @Override
//    public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent classicControllerRemovedEvent) {
//
//    }
//}

package com.avansA5.noot.scenes;

import com.avansA5.noot.Program;
import com.avansA5.noot.managers.ControlManager;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.objects.Background;
import com.avansA5.noot.ui.UIButton;
import com.avansA5.noot.ui.UIString;
import com.avansA5.noot.util.Log;
import com.avansA5.noot.util.RelativePoint;
import com.avansA5.noot.util.SoundPlayer;
import wiiusej.wiiusejevents.physicalevents.*;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.*;

import java.awt.*;

public class StartScene extends Scene implements WiimoteListener
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
        soundPlayer = new SoundPlayer("res//IntroFadeInAndOut.wav");
        soundPlayer.loop();
        background = new Background("res//StartUpBG.png");
        sprites.add(background);
        scale = background.getScale();

        uiElements.add(new UIString("1 speler", new RelativePoint(19,81), "Matura MT Script Capitals",(int)(80*scale), Color.RED));
        uiElements.add(new UIButton(new Rectangle(100,100,20,20) , Color.RED,new RelativePoint(10,74), new UIString("", new Point(0,0)), true));
        uiElements.add(new UIString(" 2 spelers", new RelativePoint(19,90), "Matura MT Script Capitals",(int)(80*scale), Color.BLACK));
        uiElements.add(new UIButton(new Rectangle(100,100,20,20) , Color.RED, new RelativePoint(10,82),new UIString("", new Point(0,0)), false));
    }

    @Override
    public void unload()
    {
        soundPlayer.stop();
        sprites.removeAll(sprites);
        uiElements.removeAll(uiElements);
        //ControlManager.removeWiimoteListener(this, 0);
    }

    @Override
    public void onButtonsEvent(WiimoteButtonsEvent wiimoteButtonsEvent)
    {
        if(wiimoteButtonsEvent.isButtonUpJustPressed())
        {
            selectedOption = 1;
            uiElements.get(0).setColor(Color.red);
            uiElements.get(1).setVisible(true);
            uiElements.get(2).setColor(Color.BLACK);
            uiElements.get(3).setVisible(false);
        }

        if(wiimoteButtonsEvent.isButtonDownJustPressed())
        {
            selectedOption = 2;
            uiElements.get(0).setColor(Color.BLACK);
            uiElements.get(1).setVisible(false);
            uiElements.get(2).setColor(Color.RED);
            uiElements.get(3).setVisible(true);
        }

        if(wiimoteButtonsEvent.isButtonAJustPressed())
        {
            switch(selectedOption)
            {
                case 1:
                    SceneManager.setScene("TutorialScene");
                    break;
                case 2:
                    multiplayer = true;
                    SceneManager.setScene("TutorialScene");
                    break;
            }
        }

        if(wiimoteButtonsEvent.isButtonHomeJustPressed())
        {
            Program.stop();
        }
    }

    public static boolean getStatus()
    {
        return multiplayer;
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
