package com.avansA5.noot.objects;

import com.avansA5.noot.scenes.GameScene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by TripleCTMJ on 02/06/2016.
 * Refactored by Joshua on 17/06/2016.
 *
 * The scrolling background in the middle of GameScene
 */
public class ScrollingBackground extends GameObject
{
    private final ArrayList<BufferedImage> backgrounds;
    BufferedImage background1,background2,background3;
    private final int BACKGROUND_HEIGHT = 1440;
    // amount of pickels per step ( this * frames) is amount of scrollign per seconds.
    private final int SCROLLING_SPEED = 4;

    // counter for the amount of steps taken
    private int step = 0;
    private int panelWidth;
    private double panelScale;

    public ScrollingBackground()
    {
        backgrounds = new ArrayList<>();
        try
        {
            for(int i = 1; i <= 6; i++)
            {
                backgrounds.add(ImageIO.read(new File("res" + File.separator + "ScrollingBackground" + i + ".png")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        background1 = backgrounds.get((int)(Math.random() * 5));
        background2 = backgrounds.get((int)(Math.random() * 5));
        background3 = backgrounds.get((int)(Math.random() * 5));

        panelWidth = GameScene.getPanel(0).getPanelWidth();
        panelScale = GameScene.getPanel(0).getScale();
    }

    public void draw(Graphics2D g)
    {
        if(step > (BACKGROUND_HEIGHT / SCROLLING_SPEED)) {
            step = 0;

            background1 = background2;
            background2 = background3;
            background3 = backgrounds.get((int)(Math.random() * 5));
        }

        g.drawImage(background1, (int)(panelWidth*panelScale), step * SCROLLING_SPEED, null);
        g.drawImage(background2, (int)(panelWidth*panelScale), (step * SCROLLING_SPEED) - BACKGROUND_HEIGHT, null);
        g.drawImage(background3, (int)(panelWidth*panelScale), (step * SCROLLING_SPEED) - (2 * BACKGROUND_HEIGHT), null);
    }

    @Override
    public void update()
    {
        step++;
    }
}
