package com.avansA5.noot.ui;

import java.awt.*;

public class UIString implements UIElement
{
    private final String text;
    private final Point location;
    private final Font font;
    private Color color = Color.WHITE;
    private Rectangle rect;

    public UIString(String text, Point location)
    {
        this(text, location, "");
    }

    private UIString(String text, Point location, String fontName)
    {
        this(text, location, fontName, 10);
    }

    public UIString(String text, Point location, String fontName, int size)
    {
        this(text, location, fontName, size, Font.PLAIN);
    }

    private UIString(String text, Point location, String fontName, int size, int attributes)
    {
        this.text = text;
        this.location = location;

        this.font = new Font(fontName, attributes, size);
    }

    public UIString(String text, Point location, String fontName, int size, Color color)
    {
        this(text, location, fontName, size, Font.PLAIN);
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        draw(g2, this.location);
    }

    public void draw(Graphics2D g2, Point location)
    {
        Color oldcolor = g2.getColor();
        g2.setColor(color);

        g2.setFont(font);
        g2.drawString(text, (float) location.getX() - (g2.getFontMetrics().stringWidth(text) / 2), (float) location.getY() - (g2.getFontMetrics().getHeight() / 2));
        if (rect == null)
        {
            rect = new Rectangle((int) location.getX(), (int) location.getY(), g2.getFontMetrics().stringWidth(text), g2.getFontMetrics().getHeight());
        }
        g2.setColor(oldcolor);
    }

    public void update() {}

    public Rectangle getRect()
    {
        if (rect == null)
            throw new RuntimeException("Kankerzooi");
        else
            return rect;
    }

    public void onClick(Point p){}

    public void setColor(Color color)
    {
        this.color = color;
    }

    public void setVisible(boolean visible) {}
}
