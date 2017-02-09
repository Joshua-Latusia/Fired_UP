package com.avansA5.noot.types.patterns;

import com.avansA5.noot.types.HermitCurve;
import com.avansA5.noot.types.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by TripleCTMJ on 02/06/2016.
 */
public class MovePattern
{
    private final ArrayList<Point> points = new ArrayList<>();
    private final int amplitude;
    private int currentPoint;
    private double speed;
    private boolean endReached = false;

    public MovePattern(double speed)
    {
        currentPoint = 0;
        this.speed = speed;
        amplitude = 500;
    }

    public void addTargetPoint(Point p)
    {
        points.add(p);
    }

    public void addCurveToPoint(Point p)
    {
        if (!points.isEmpty())
        {
            Point startPoint = points.get(points.size() - 1);
            double dx = p.getX() - startPoint.getX();
            double dy = p.getY() - startPoint.getY();
            double angle = Math.atan2(dy, dx) + Math.PI / 2;
            double angle2 = angle + Math.PI;
            double anker1X = startPoint.getX() + Math.cos(angle) * amplitude;
            double anker1Y = startPoint.getY() + Math.sin(angle) * amplitude;
            double anker2X = p.getX() + Math.cos(angle2) * amplitude;
            double anker2Y = p.getY() + Math.sin(angle2) * amplitude;

            Point2D anker1 = new Point2D.Double(anker1X, anker1Y);
            Point2D anker2 = new Point2D.Double(anker2X, anker2Y);
            Point2D startPoint2D = new Point2D.Double(startPoint.getX(), startPoint.getY());
            Point2D p2D = new Point2D.Double(p.getX(), p.getY());

            HermitCurve curve = new HermitCurve(startPoint2D, anker1, p2D, anker2);

            int CURVE_POINTS = 50;
            for(int i = 0; i < CURVE_POINTS; i++)
            {
                points.add(curve.getPoint((1.0 / CURVE_POINTS) * i));
            }
            points.add(p);
        }
    }

    public Point getNextPoint()
    {
        return points.get(currentPoint);
    }

    public boolean checkCurrentPosition(Vector2D vector)
    {
        double dx = vector.getX() - points.get(currentPoint).getX();
        double dy = vector.getY() - points.get(currentPoint).getY();

        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        if (distance < 5)
        {
            if (currentPoint < points.size() - 1)
            {
                currentPoint++;
                return true;
            } else
            {
                endReached = true;
            }
        }
        return false;
    }

    public boolean endReached()
    {
        return endReached;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
}
