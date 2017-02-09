package com.avansA5.noot.types.patterns.patternTemplates;

import com.avansA5.noot.objects.Bullet;
import com.avansA5.noot.types.BulletType;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.Vector2D;

import java.util.ArrayList;

/**
 * Created by Timon on 09/06/2016.
 */
public class BossSprayPattern implements Pattern
{
    private int maxBullets;
    private boolean hasEnded = false;
    private boolean increasing = true;

    private int bulletCounter = 0;
    private State state;
    private double shootTimer = 0;
    private long oldTime = System.currentTimeMillis();

    ArrayList<Bullet> bullets = new ArrayList<>();

    public BossSprayPattern(int maxBullets, int shootingSpeed)
    {
        this.maxBullets = maxBullets;
        this.shootTimer = 1000 / shootingSpeed;
        state = State.random();
    }
    @Override
    public ArrayList<Bullet> getBullets(Vector2D spawnPos, State state, int spriteWidth, int spriteHeight)
    {
        bullets.clear();
        long newTime = System.currentTimeMillis();
        if(newTime - oldTime > shootTimer)
        {
            oldTime = newTime;
            int half = maxBullets / 2;

            double spawnX = spawnPos.getX() + spriteWidth/2;
            double spawnY = spawnPos.getY() + spriteHeight;
            double targetX = spawnPos.getX() + spriteWidth / 2 + 50 + (-half + bulletCounter) * 30;
            double targetY = spawnPos.getY() + 500;
            Bullet bullet = new Bullet(state, new Vector2D(spawnX, spawnY), new Vector2D(targetX, targetY), BulletType.ENEMY, 1, false);
            bullets.add(bullet);

            if(increasing)
            {
                if (++bulletCounter >= maxBullets)
                {
                    hasEnded = true;
                }
            }

            if(!increasing)
            {
                if(--bulletCounter <= 0)
                {
                    hasEnded = true;
                }
            }

        }

        return  bullets;
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
        increasing = !increasing;
    }
}
