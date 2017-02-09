package com.avansA5.noot.scenes;

import com.avansA5.noot.managers.ControlManager;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.objects.Image;
import com.avansA5.noot.ui.UIString;
import com.avansA5.noot.util.RelativePoint;
import wiiusej.wiiusejevents.physicalevents.*;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.*;

/**
 * Created by TripleCTMJ on 10/06/2016.
 */
public class PowerUpScene extends Scene implements WiimoteListener{

    private double scale;

    @Override
    public void load() {
        StartScene.soundPlayer.start();
        ControlManager.clearWiiMoteListener(0);
        ControlManager.clearWiiMoteListener(1);
        ControlManager.addWiimoteListener(this,0);

        scale = super.calculateScale();

        sprites.add(new Image("res//PowerupbiggerBullet.png", new RelativePoint(23, 20)));
        sprites.add(new Image("res//PowerupStrongerBullet.png", new RelativePoint(23, 30)));
        sprites.add(new Image("res//PowerupFasterMovement.png", new RelativePoint(23, 44)));
        sprites.add(new Image("res//PowerupShrinkPlayer.png", new RelativePoint(23, 56)));

        uiElements.add(new UIString("Krijg grotere vuurballen", new RelativePoint(50,27),"Matura MT Script Capitals",(int)(80*scale)));
        uiElements.add(new UIString("Krijg sterkere vuurballen", new RelativePoint(50,39), "Matura MT Script Capitals",(int)(80*scale)));
        uiElements.add(new UIString("Beweeg je draak sneller", new RelativePoint(50,51), "Matura MT Script Capitals",(int)(80*scale)));
        uiElements.add(new UIString("Maak je draak kleiner", new RelativePoint(50,63), "Matura MT Script Capitals",(int)(80*scale)));
        uiElements.add(new UIString("druk op z om te beginnen", new RelativePoint(50,99), "Matura MT Script Capitals", (int)(50*scale)));
    }

    @Override
    public void unload() {
        uiElements.clear();
        sprites.clear();
        StartScene.soundPlayer.stop();
    }

    @Override
    public void onButtonsEvent(WiimoteButtonsEvent wiimoteButtonsEvent) {

    }

    @Override
    public void onIrEvent(IREvent irEvent) {

    }

    @Override
    public void onMotionSensingEvent(MotionSensingEvent motionSensingEvent) {

    }

    @Override
    public void onExpansionEvent(ExpansionEvent expansionEvent) {
        NunchukEvent nunchukButtonsEvent = (NunchukEvent) expansionEvent;

        if(nunchukButtonsEvent.getButtonsEvent().isButtonZJustPressed()) {
            SceneManager.setScene("GameScene");
        }

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
