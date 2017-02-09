package com.avansA5.noot.types;

public enum State
{
    RED,
    BLUE;

    private static final State[] vals = values();

    public static State random()
    {
        int randomNr = (int) (Math.random() * 2);
        return vals[randomNr];
    }

    public State next()
    {
        return vals[(this.ordinal() + 1) % vals.length];
    }
}