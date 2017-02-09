package com.avansA5.noot.objects;

import com.avansA5.noot.interfaces.Updatable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by TripleCtmj on 24/05/2016.
 * Refactored by Joshua on 17/06/2016
 */
public class Animation implements Updatable
{
    private final ArrayList<BufferedImage> spriteList = new ArrayList<>();
    private final double animationTimer;
    private int maxSprites = 0;
    private int currentSprite = 0;
    private long oldTime = 0;

    public Animation(String tilesheetPath, int rows, int columns, int width, int height, int updatesPerSecond)
    {
        maxSprites = rows * columns;
        animationTimer = 1000/updatesPerSecond;
        createSpriteList(tilesheetPath, rows, columns, width, height);
    }

    private void createSpriteList(String tileSheetPath, int rows, int columns, int width, int height)
    {
        try
        {
            BufferedImage tilesheetImage = ImageIO.read(new File(tileSheetPath));

            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < columns; c++)
                {
                    BufferedImage tempImage = tilesheetImage.getSubimage(c * width, r * height, width, height);
                    spriteList.add(tempImage);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public BufferedImage getCurrentImage()
    {
        return spriteList.get(currentSprite);
    }

    @Override
    public void update()
    {
        long newTime = System.currentTimeMillis();
        if(newTime - oldTime > animationTimer)
        {
            oldTime = newTime;
            currentSprite++;
            if (currentSprite >= maxSprites)
                currentSprite = 0;
        }
    }
}
