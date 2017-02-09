package com.avansA5.noot.objects.pickups;

import com.avansA5.noot.interfaces.Colliding;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.objects.characters.Player;
import com.avansA5.noot.types.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by TripleCTMJ on 09/06/2016.
 * Refactored by Joshua on 17/06/2016.
 */
public class FasterMovement extends Pickup {
    BufferedImage image;

    public FasterMovement(){
        try
        {
            image = ImageIO.read(new File("res//PowerupFasterMovement.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        int x = (int) (Math.random()*SCREEN_WIDTH-(2*SCALED_PANEL_SIZE)) + SCALED_PANEL_SIZE - image.getWidth();
        int y = -50;
        vector = new Vector2D(x,y, 44,35);
        SceneManager.getCurrentScene().addSprite(this);
    }
    @Override
    public void onCollision(Colliding other) {
        if(other instanceof Player)
        {
            dead = true;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image,(int)vector.getX(),(int) vector.getY(),null);
    }



    @Override
    public void update() {
        vector.setY(vector.getY() + 5);
    }
}
