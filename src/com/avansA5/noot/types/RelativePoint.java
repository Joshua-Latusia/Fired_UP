package com.avansA5.noot.types;

import com.avansA5.noot.managers.WindowManager;

import java.awt.*;

public class RelativePoint extends Point
{

    public RelativePoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX()
    {
        double width = WindowManager.frame.getWidth();
        return (width / 100) * x;
    }

    @Override
    public double getY()
    {
        double height = WindowManager.panel.getHeight();
        return (height / 100) * y;
    }
}
