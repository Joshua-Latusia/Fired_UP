package com.avansA5.noot.scenes;

import com.avansA5.noot.managers.ControlManager;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.objects.Bullet;
import com.avansA5.noot.objects.Image;
import com.avansA5.noot.types.BulletType;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.Vector2D;
import com.avansA5.noot.ui.UIString;
import com.avansA5.noot.util.RelativePoint;
import wiiusej.wiiusejevents.physicalevents.*;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.*;

/**
 * Created by TripleCTMJ on 03/06/2016.
 */
public class TutorialScene extends Scene implements WiimoteListener{

    double scale;
    State state;
    private Image blueSubImage;
    private Image blueSubImage2;
    private Image blueImage3;
    private Image blueImage4;
    private boolean blue;

    @Override
    public void load() {
        StartScene.soundPlayer.start();
        ControlManager.clearWiiMoteListener(0);
        ControlManager.clearWiiMoteListener(1);
        ControlManager.addWiimoteListener(this,0);

        scale = super.calculateScale();
        sprites.add(new Image("res/JoyStick.png", new RelativePoint(24, 35)));
        sprites.add(new Image("res/ButtonB.png", new RelativePoint(24,55)));
        sprites.add(new Image("res/ButtonZ.png", new RelativePoint(24, 75)));
        sprites.add(new Image("res//wiimote.png", new RelativePoint(24,15)));

        Image redImage = new Image("res//PlayerRed.png", new RelativePoint(40,35));
        Image redSubImage = new Image(redImage.getSubImage(0,0,130,120), new RelativePoint(76,35));
        redSubImage.setVisible(true);
        sprites.add(redSubImage);

        Image redImage2 = new Image("res//redFireBallTileset.png", new RelativePoint(40,55));
        Image redSubImage2 = new Image(redImage2.getSubImage(0,0,64,64), new RelativePoint(76,55));
        redSubImage2.setVisible(true);
        sprites.add(redSubImage2);

        Image image3 = new Image("res//RedSelected.png", new RelativePoint(76,75));
        sprites.add(image3);

        Image image4 = new Image("res//RedCrossHair.png", new RelativePoint(76, 15));
        sprites.add(image4);


        blue = true;
        state = State.RED;

        uiElements.add(new UIString("richt met wiimote", new RelativePoint(50,23),"Matura MT Script Capitals",(int)(100*scale)));
        uiElements.add(new UIString("beweeg je draak", new RelativePoint(50,43), "Matura MT Script Capitals",(int)(100*scale)));
        uiElements.add(new UIString("schiet vuurballen", new RelativePoint(50,63), "Matura MT Script Capitals",(int)(100*scale)));
        uiElements.add(new UIString("verander van kleur", new RelativePoint(50,83), "Matura MT Script Capitals",(int)(100*scale)));
        uiElements.add(new UIString("druk op a+b om verder te gaan", new RelativePoint(50,99), "Matura MT Script Capitals", (int)(50*scale)));
    }

    @Override
    public void unload()
    {
        uiElements.clear();
        sprites.removeAll(sprites);
        uiElements.removeAll(uiElements);
        StartScene.soundPlayer.stop();
    }

    @Override
    public void onButtonsEvent(WiimoteButtonsEvent wiimoteButtonsEvent) {
        if(wiimoteButtonsEvent.isButtonAPressed())
        {
            if(wiimoteButtonsEvent.isButtonBPressed())
            {
                SceneManager.setScene("PowerUpScene");
            }
        }

        if(wiimoteButtonsEvent.isButtonBJustPressed())
        {
            sprites.add(new Bullet(state, new Vector2D(1458,270), new Vector2D(1453, -200), BulletType.PLAYER, 1, false));
        }
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

        if(nunchukButtonsEvent.getButtonsEvent().isButtonZJustPressed())
        {
            if(blue) {
                Image blueImage = new Image("res//PlayerBlue.png", new RelativePoint(40, 35));
                blueSubImage = new Image(blueImage.getSubImage(0, 0, 130, 120), new RelativePoint(76, 35));
                blueSubImage.setVisible(true);
                sprites.add(blueSubImage);

                Image redImage2 = new Image("res//blueFireBallTileset.png", new RelativePoint(40, 55));
                blueSubImage2 = new Image(redImage2.getSubImage(0, 0, 64, 64), new RelativePoint(76, 55));
                blueSubImage2.setVisible(true);
                sprites.add(blueSubImage2);

                blueImage3 = new Image("res//BlueSelected.png", new RelativePoint(76, 75));
                sprites.add(blueImage3);

                blueImage4 = new Image("res//BlueCrossHair.png", new RelativePoint(76, 15));
                sprites.add(blueImage4);
                state = State.BLUE;
                blue = false;
            }
            else
            {
                if(blueSubImage != null && blueSubImage2 != null && blueImage3 != null) {
                    sprites.remove(sprites.indexOf(blueSubImage));
                    sprites.remove(sprites.indexOf(blueSubImage2));
                    sprites.remove(sprites.indexOf(blueImage3));
                    sprites.remove(sprites.indexOf(blueImage4));
                }
                state = State.RED;
                blue = true;
            }
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
