package com.avansA5.noot.types.patterns;

import com.avansA5.noot.objects.Bullet;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.Vector2D;
import com.avansA5.noot.types.patterns.patternTemplates.Pattern;
import com.avansA5.noot.util.Log;

import java.util.ArrayList;

/**
 * Created by TripleCTMJ on 02/06/2016.
 */
public class ShootPattern
{
    private final ArrayList<Pattern> patterns = new ArrayList<>();

    private State state;
    private int currentShootPattern;
    private static boolean isAlive;

    private long delay;

    private long oldTime = System.currentTimeMillis();

    public ShootPattern(int delay)
    {
        this.currentShootPattern = 0;
        this.state = State.RED;
        this.delay = delay;
        isAlive = true;
    }

    public void addPattern(Pattern pattern)
    {
        patterns.add(pattern);
    }

    public ArrayList<Bullet> getBullets(Vector2D spawnPos, State state, int spriteWidth, int spriteHeight, boolean isBoss)
    {
        long newTime = System.currentTimeMillis();
        if(newTime - oldTime > 0)
        {
            oldTime = newTime;
            if(!isBoss)
            {
                this.state = state;
            }

            if(patterns.get(currentShootPattern).hasEnded())
            {
                if(isBoss)
                    this.state = State.random();

                currentShootPattern++;
                Log.log("New shoot pattern");
                if(currentShootPattern > patterns.size() - 1)
                {
                    currentShootPattern = 0;
                }
                oldTime = newTime + delay;
                patterns.get(currentShootPattern).restart();
            }
            return patterns.get(currentShootPattern).getBullets(spawnPos, state, spriteWidth,spriteHeight);
        }

        return new ArrayList<Bullet>();
    }
}
