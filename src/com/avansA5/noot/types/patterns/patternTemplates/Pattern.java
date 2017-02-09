package com.avansA5.noot.types.patterns.patternTemplates;

import com.avansA5.noot.objects.Bullet;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.Vector2D;

import java.util.ArrayList;

/**
 * Created by TripleCTMJ on 02/06/2016.
 */
public interface Pattern
{
    ArrayList<Bullet> getBullets(Vector2D spawnPos, State state, int spriteWidth, int spriteHeight);

    boolean hasEnded();
    void restart();
}
