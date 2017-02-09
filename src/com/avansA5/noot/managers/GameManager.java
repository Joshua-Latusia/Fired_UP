package com.avansA5.noot.managers;


import com.avansA5.noot.util.Log;

import javax.swing.*;

public class GameManager
{
    static Timer updateTimer;

    public static void start()
    {
        Log.log("Starting GameManager");
        updateTimer = new Timer(1000 / 60, e -> update());
        updateTimer.start();
        Log.log("GameManager started");
    }

    public static void stop()
    {
        updateTimer.stop();
    }

    static void update()
    {
        SceneManager.update();
    }


}
