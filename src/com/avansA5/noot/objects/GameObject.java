package com.avansA5.noot.objects;


import com.avansA5.noot.interfaces.Drawable;
import com.avansA5.noot.interfaces.Updatable;
import com.avansA5.noot.types.Vector2D;

/**
 * Created by TripleCTMJ on 03/06/2016.
 * Refactored by Joshua on 17/06/2016.
 */

public abstract class GameObject implements Drawable, Updatable
{
    public boolean dead = false;

    public Vector2D getVector() {
        return vector;
    }
    public Vector2D getCenter() { return vector; }

    protected Vector2D vector = new Vector2D();
}
