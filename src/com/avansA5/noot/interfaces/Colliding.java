package com.avansA5.noot.interfaces;

import com.avansA5.noot.types.Vector2D;

/**
 * Created by RMSjoshua on 6/2/2016.
 */
public interface Colliding {
    void onCollision(Colliding other);
    Vector2D getVector();

}
