package com.avansA5.noot.objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by TripleCTMJ on 6-6-2016.
 * Refactored by Joshua on 17/06/2016.
 */
public class Background extends GameObject
{
    BufferedImage background;
     private double scale;

    public Background(String url)
    {
       calculateScale();

        try {
            background = ImageIO.read(new File(url));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    @Override
    public void draw(Graphics2D g2)
    {
        g2.scale(scale,scale);
        g2.drawImage(background,0,0,null);
        g2.scale(1920/Toolkit.getDefaultToolkit().getScreenSize().getWidth(),1920/Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    }

    public void calculateScale()
    {
       scale  = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1920;
    }

    public double getScale()
    {
        return scale;
    }

    public void update(){}
}
