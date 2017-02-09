package com.avansA5.noot.objects;

import com.avansA5.noot.interfaces.Colliding;
import com.avansA5.noot.interfaces.GiveDamage;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.objects.characters.Player;
import com.avansA5.noot.scenes.GameScene;
import com.avansA5.noot.types.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by TripleCTMJ on 02/06/2016.
 * Refactored by Joshua 17/06/2016.
 *
 * Obstacles while playing , non destructable
 */
public class Rock extends GameObject implements Colliding, GiveDamage{
    private final PlayerPanel panel;
    private final double direction = 0.5 * Math.PI;
    private final int SCALED_PANEL_SIZE;
    private final double SCREEN_WIDTH;
    private BufferedImage rock;

    public Rock(){
        try {rock = ImageIO.read(new File("res//Rock.png"));}
        catch(Exception e) {e.printStackTrace();}

        panel = GameScene.getPanels()[1];
        SCALED_PANEL_SIZE = (int)(panel.getPanelWidth()*panel.getScale());
        SCREEN_WIDTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        int x = (int) ((Math.random()*SCREEN_WIDTH-(2*SCALED_PANEL_SIZE)) + (SCALED_PANEL_SIZE) - rock.getWidth());

        Point spawnPos = new Point(x,-64);
        SceneManager.getCurrentScene().addSprite(this);
        vector = new Vector2D(spawnPos.getX() , spawnPos.getY(), rock.getWidth()*0.5, rock.getHeight()*0.5, direction, 5);
    }

    @Override
    public void draw(Graphics2D g2) {
        AffineTransform at = new AffineTransform();
        at.translate((int)vector.getX(), (int)vector.getY());
        at.scale(0.5, 0.5);
        g2.drawImage(rock, at, null);
    }

    @Override
    public void update() {
        if(vector.getX() < SCALED_PANEL_SIZE-50 ||
           vector.getX() > SCREEN_WIDTH- SCALED_PANEL_SIZE+50)
            dead = true;

        if(vector.getY() < - 75 || vector.getY() > SCREEN_WIDTH + 50)
            dead = true;

        double moveX = Math.cos(vector.getDirection());
        double moveY = Math.sin(vector.getDirection());
        vector.setX(vector.getX() + moveX * vector.getSpeed());
        vector.setY(vector.getY() + moveY * vector.getSpeed());
    }

    @Override
    public void onCollision(Colliding other)
    {
        if(other instanceof Player)
        {
            dead = true;
        }
    }

    @Override
    public int getDamage() {return 1;}
}
