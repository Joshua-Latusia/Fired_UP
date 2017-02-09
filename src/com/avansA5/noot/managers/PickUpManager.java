package com.avansA5.noot.managers;

import com.avansA5.noot.objects.pickups.*;

import javax.swing.*;

/**
 * Created by TripleCTMJ on 3-6-2016.
 */
public class PickUpManager
{
    static Timer timer, powerUpTimer;
    public static void start()
    {
        timer = new Timer(8500, e -> spawnHeart());
        timer.start();

        powerUpTimer = new Timer(5000, e -> spawnPowerUp());
        powerUpTimer.start();
    }

    public static void spawnHeart()
    {
        new Heart();
    }

    public static void spawnPowerUp()
    {
        double i = (Math.random()*400);
        if(i<100){
            new BiggerBullet();
        }
        else if(i<200){
            new FasterMovement();
        }
        else if(i<300){
            new StrongerBullets();
        }
        else{
            new ShrinkPlayer();
        }
    }

    public static void stop()
    {
        timer.stop();
        powerUpTimer.stop();
    }
}
