package com.avansA5.noot.objects;

import com.avansA5.noot.util.RelativePoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by TripleCTMJ on 09/06/2016.
 * Refactored by Joshua on 17/06/2016.
 */
public class Image extends GameObject{
    private final int y;
    private final int x;

    private BufferedImage image;
    private boolean visible;

    public Image(String imageLocation, RelativePoint relativePoint){
        try {
            image = ImageIO.read(new File(imageLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

        x = (int)relativePoint.getX() - image.getWidth()/2;
        y = (int)relativePoint.getY() - image.getHeight()/2;
        visible = true;
    }

    public Image(BufferedImage image, RelativePoint relativePoint)
    {
        this.image = image;
        x = (int)relativePoint.getX() - image.getWidth()/2;
        y = (int)relativePoint.getY() - image.getHeight()/2;
    }
    @Override
    public void draw(Graphics2D g2) {
        if(visible){
            g2.drawImage(image, x, y, null);
        }
    }

    public BufferedImage getSubImage(int row, int col, int width, int height)
    {
        return image.getSubimage(row,col,width,height);
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }


    @Override
    public void update() {}
}
