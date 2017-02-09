package com.avansA5.noot.scenes;

import com.avansA5.noot.Program;
import com.avansA5.noot.managers.ControlManager;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.objects.ControllerCross;
import com.avansA5.noot.objects.PlayerPanel;
import com.avansA5.noot.objects.ScrollingBackground;
import com.avansA5.noot.ui.UIString;
import com.avansA5.noot.util.RelativePoint;
import com.avansA5.noot.util.SoundPlayer;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.*;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Casje flum flum on 03/06/2016.
 * Scene when all players lives are depleted
 */
public class GameOverScene extends Scene implements WiimoteListener{
   PlayerPanel[] panels = GameScene.getPanels();
    double scale = super.calculateScale();
    SoundPlayer soundPlayer;

    @Override
    public void draw(Graphics2D g2)
    {
        super.draw(g2);

        for(PlayerPanel p : panels)
            p.draw(g2);
    }

    @Override
    public void load() {
        soundPlayer = new SoundPlayer("res//GameOverMusic.wav");
        soundPlayer.loop();
        ControlManager.clearWiiMoteListener(0);
        ControlManager.clearWiiMoteListener(1);
        ControlManager.addWiimoteListener(this,0);
        uiElements.add(new UIString("GAME OVER", new RelativePoint(50,25), "Matura MT Script Capitals", (int)(125*scale), Color.BLACK));
        uiElements.add(new UIString("Restart", new RelativePoint(55,41), "Matura MT Script Capitals",(int)(100*scale), Color.black));
        uiElements.add(new UIString("Menu", new RelativePoint(40,58), "Matura MT Script Capitals",(int)(100*scale), Color.BLACK));
        uiElements.add(new UIString("Quit", new RelativePoint(55,76), "Matura MT Script Capitals",(int)(100*scale), Color.black));

        sprites = new CopyOnWriteArrayList<>();
        sprites.add(new ScrollingBackground());
        sprites.add(new ControllerCross());


    }

    @Override
    public void unload()
    {
        sprites.removeAll(sprites);
        uiElements.removeAll(uiElements);
        soundPlayer.stop();
    }

    @Override
    public void onButtonsEvent(WiimoteButtonsEvent wiimoteButtonsEvent)
    {
        if(wiimoteButtonsEvent.isButtonUpJustPressed())
        {
            SceneManager.setScene("GameScene");
        }

        if(wiimoteButtonsEvent.isButtonDownJustPressed())
        {
            Program.stop();
        }

        if(wiimoteButtonsEvent.isButtonLeftJustPressed())
        {
            SceneManager.setScene("StartScene");
        }

    }

    public void onIrEvent(IREvent irEvent) {}

    public void onMotionSensingEvent(MotionSensingEvent motionSensingEvent) {}

    public void onExpansionEvent(ExpansionEvent expansionEvent) {}

    public void onStatusEvent(StatusEvent statusEvent) {}

    public void onDisconnectionEvent(DisconnectionEvent disconnectionEvent) {}

    public void onNunchukInsertedEvent(NunchukInsertedEvent nunchukInsertedEvent) {}

    public void onNunchukRemovedEvent(NunchukRemovedEvent nunchukRemovedEvent) {}

    public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent guitarHeroInsertedEvent) {}

    public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent guitarHeroRemovedEvent) {}

    public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent classicControllerInsertedEvent) {}

    public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent classicControllerRemovedEvent) {}
}
