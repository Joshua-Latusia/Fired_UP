package com.avansA5.noot.types.patterns.patternTemplates;

import com.avansA5.noot.objects.Bullet;
import com.avansA5.noot.types.BulletType;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.Vector2D;

import java.util.ArrayList;

/**
 * Created by TripleCTMJ on 02/06/2016.
 */
public class StraightPattern implements Pattern
{
    private boolean hasEnded;
    private final int bulletCount;
    public StraightPattern(int bulletCount)
    {
        this.bulletCount = bulletCount;
    }

    private ArrayList<Bullet> bullets = new ArrayList<>();

    @Override
    public ArrayList<Bullet> getBullets(Vector2D spawnPos, State state, int spriteWidth, int spriteHeight)     {
        hasEnded = true;
        if(bulletCount % 2 == 0)
        {
            //Even
            return evenBullets(spawnPos, state,spriteWidth,spriteHeight);
        }
        else
        {
            //Uneven
            return unEvenBullets(spawnPos, state, spriteWidth, spriteHeight);
        }
    }

    @Override
    public boolean hasEnded()
    {
        return hasEnded;
    }

    @Override
    public void restart()
    {
        hasEnded = false;
    }

    private ArrayList<Bullet> evenBullets(Vector2D spawnPos, State state, int spriteWidth, int spriteHeight)
    {
        bullets.clear();
        int half = bulletCount / 2;

        for(int i = -half; i <= half; i++)
        {
            double SHOOT_CHANCE = 20.0 / 100.0;
//            if(Math.random() >= SHOOT_CHANCE)
//            {
                int SPREAD = 30;
                double spawnX = spawnPos.getX() + spriteWidth/2 + i * SPREAD;
                double spawnY = spawnPos.getY() + spriteHeight;
                double targetX = spawnPos.getX() + spriteWidth/2 + i * SPREAD;
                double targetY = spawnPos.getY() + 500;
                Bullet bullet = new Bullet(state, new Vector2D(spawnX, spawnY), new Vector2D(targetX, targetY), BulletType.ENEMY, 1, false);
                bullets.add(bullet);
            }
//        }
        return bullets;
    }

    private ArrayList<Bullet> unEvenBullets(Vector2D spawnPos, State state, int spriteWidth, int spriteHeight)
    {
        bullets.clear();
        int half = (bulletCount - 1) / 2;

        for(int i = -half; i <= half; i++)
        {
//            if(Math.random() >= 0.2)
//            {
                int SPREAD = 30;
                double spawnX = spawnPos.getX() + spriteWidth/2 + i * SPREAD;
                double spawnY = spawnPos.getY() + spriteHeight;
                double targetX = spawnPos.getX() + spriteWidth/2 + i * SPREAD;
                double targetY = spawnPos.getY() + 500;
                Bullet bullet = new Bullet(state, new Vector2D(spawnX, spawnY), new Vector2D(targetX, targetY), BulletType.ENEMY, 1, false);
                bullets.add(bullet);
            }
//        }
        return bullets;
    }
}
