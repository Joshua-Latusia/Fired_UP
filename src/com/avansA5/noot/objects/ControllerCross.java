package com.avansA5.noot.objects;

import com.avansA5.noot.types.RelativePoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by TripleCTMJ on 03/06/2016.
 * Refactored by Joshua on 17/06/2016.
 *
 *  The option menu for GameOverScene
 */

public class ControllerCross extends GameObject {


    private final double x;
    private final double y;
    private int imageNumber = 0, update = 0;

    private BufferedImage up, down, left, usedImage;

    public ControllerCross(){
        try {
            up   = ImageIO.read(new File("res/CrossUp.png"));
            down = ImageIO.read(new File("res/CrossDown.png"));
            left = ImageIO.read(new File("res/CrossLeft.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        RelativePoint relativePoint = new RelativePoint(50, 50);
        x = relativePoint.getX();
        y = relativePoint.getY() - up.getHeight()/2;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(usedImage, (int)x, (int)y, null);
    }

    @Override
    public void update() {
        if(update%50 == 0) {
            imageNumber++;
            imageNumber = imageNumber % 3;

            switch (imageNumber) {
                case 0:
                    usedImage = down;
                    break;
                case 1:
                    usedImage = left;
                    break;
                case 2:
                    usedImage = up;
                    break;
                default:
                    break;
            }

        }
        update++;
}
}
