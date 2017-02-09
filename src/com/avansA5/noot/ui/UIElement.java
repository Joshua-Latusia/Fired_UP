package com.avansA5.noot.ui;

import java.awt.*;

public interface UIElement
{
    void draw(Graphics2D g2);

    void update();

    Rectangle getRect();

    void onClick(Point p);

    void draw(Graphics2D g2, Point location);

    void setColor(Color color);

    void setVisible(boolean visible);
}
