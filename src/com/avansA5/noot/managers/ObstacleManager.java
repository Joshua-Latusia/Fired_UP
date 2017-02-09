package com.avansA5.noot.managers;

import com.avansA5.noot.objects.Rock;

import javax.swing.*;

/**
 * Created by TripleCTMJ on 2-6-2016.
 */
public class ObstacleManager
{
    static Timer timer;
    public static void start()
    {
        timer = new Timer(3000, e -> rock());
        timer.start();
    }
   public static void rock()
   {
       new Rock();
   }

    public static void stop()
    {
        timer.stop();
    }
}
