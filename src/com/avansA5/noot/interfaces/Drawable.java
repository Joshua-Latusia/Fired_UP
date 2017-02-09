package com.avansA5.noot.interfaces;

import java.awt.*;

/**
 * Interface for objects that can be drawn
 */
public interface Drawable
{
    /**
     * Draws this GameObject
     * @param g2 the Graphics2D engine stuff
     */
    void draw(Graphics2D g2);
}
