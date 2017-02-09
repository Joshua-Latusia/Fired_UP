package com.avansA5.noot.types;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by TripleCTMJ on 02/06/2016.
 */
public class HermitCurve
{
    private final Point2D p0;
    private final Point2D m0;
    private final Point2D p1;
    private final Point2D m1;

    public HermitCurve(Vector2D p0, Vector2D m0, Vector2D p1, Vector2D m1)
    {
        this.p0 = new Point2D.Double(p0.getX(), p0.getY());
        this.m0 = new Point2D.Double(m0.getX(), m0.getY());
        this.p1 = new Point2D.Double(p1.getX(), p1.getY());
        this.m1 = new Point2D.Double(m1.getX(), m1.getY());
    }

    public HermitCurve(Point2D p0, Point2D m0, Point2D p1, Point2D m1)
    {
        this.p0 = p0;
        this.m0 = m0;
        this.p1 = p1;
        this.m1 = m1;
    }

    public Point getPoint(double t)
    {
        double xCalc = (2 * t*t*t - 3 * t*t + 1)*p0.getX() + (t*t*t - 2 * t*t + t)*m0.getX() +
                        (-2 * t*t*t + 3 * t*t)*p1.getX() + (t*t*t - t*t)*m1.getX();
        double yCalc = (2 * t*t*t - 3 * t*t + 1)*p0.getY() + (t*t*t - 2 * t*t + t)*m0.getY() +
                        (-2 * t*t*t + 3 * t*t)*p1.getY() + (t*t*t - t*t)*m1.getY();
        return new Point((int)xCalc, (int)yCalc);
    }

}
