package com.avansA5.noot.objects;

import com.avansA5.noot.managers.ControlManager;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.scenes.GameScene;
import com.avansA5.noot.util.Log;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cas on 20/05/2016.
 * Refactored by Joshua on 17/06/2016.
 */
public class CrossHair extends GameObject implements WiimoteListener {

    private final PlayerPanel panel;
    private Point location;
    private BufferedImage image;
    private String imageString;
    private final int SPRITE_SIZE = 100;

    public CrossHair(int player)
    {
        Log.log("Constructing crosshair for player "+player);
        SceneManager.getCurrentScene().getSprites().add(this);
        if(player == 0){imageString = "res/BlueCrossHair.png";}
        else {imageString = "res/RedCrossHair.png";}

        try {
            image = ImageIO.read(new File(imageString)).getSubimage(0,0,100,100);
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel = GameScene.getPanels()[player];

        location = new Point(500,500);

        ControlManager.clearWiiMoteListener(player);
        ControlManager.addWiimoteListener(this, player);

        Log.log("Crosshair for player "+player+" constructed");
    }

    @Override
    public void draw(Graphics2D g2) {
        AffineTransform transform = new AffineTransform();
        transform.translate(location.x, location.y);
        transform.scale(0.7, 0.7);
        g2.drawImage(image, transform, null);
    }

    @Override
    public void update()
    {

    }

    @Override
    public void onButtonsEvent(WiimoteButtonsEvent wiimoteButtonsEvent) {

    }

    @Override
    public void onIrEvent(IREvent irEvent) {

        Point oldLocation = new Point(location.x,location.y);




        location.x = irEvent.getAx() * 2;
        if(location.x < panel.getPanelWidth()*panel.getScale() ||
            location.x >  Toolkit.getDefaultToolkit().getScreenSize().getWidth() - panel.getPanelWidth()*panel.getScale()- SPRITE_SIZE)

            location.x = oldLocation.x;

        location.y = irEvent.getAy() * 2;

        if(location.y >  Toolkit.getDefaultToolkit().getScreenSize().getHeight() - SPRITE_SIZE)
            location.y = oldLocation.y;






    }

    public Point getLocation() {
        return location;
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
