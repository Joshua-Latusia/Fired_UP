package com.avansA5.noot.managers;

import com.avansA5.noot.objects.Animation;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class AnimationManager
{
    private static final CopyOnWriteArrayList<Animation> animations = new CopyOnWriteArrayList<>();

    private static boolean alive = true;

    public static void start()
    {
        Thread updateThread = new Thread(AnimationManager::updater);
        updateThread.start();
    }

    private static void updater()
    {

        while (alive)
        {
            Iterator<Animation> iterator = animations.iterator();

            while (iterator.hasNext())
            {
                Animation a = iterator.next();
                a.update();
            }
        }
    }


    public static void addAnimation(Animation animation)
    {
        animations.add(animation);
    }

    public static void removeAnimation(Animation animation)
    {
        animations.remove(animation);
    }

    public static void clearAnimations(){animations.clear();}

    public static void stop()
    {
        alive = false;
    }
}
