package com.avansA5.noot.types;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Vector2D
{
    private double x = 0;
    private double y = 0;
    private double width = 0;
    private double height = 0;

    private double speed = 0;

    private double direction = 0;

    public Vector2D() {}

    public Vector2D(double x, double y)
    {
        this(x,y,0,0);
    }

    public Vector2D(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Vector2D(double x, double y, double width, double height, double direction, double speed)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.speed = speed;
    }

    //region Getters and Setters
    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getWidth()
    {
        return width;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public double getDirection()
    {
        return direction;
    }

    public void setDirection(double direction)
    {
        this.direction = direction;
    }
    //endregion

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getX2()
    {
        return x + width;
    }

    @Override
    public String toString()
    {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", direction=" + direction +
                '}';
    }

    public double getY2()
    {
        return y + height;
    }

    public Rectangle toRectangle()
    {
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }

    public Ellipse2D toEllipse2D(){return new Ellipse2D.Double(x,y,width,height);}
}
