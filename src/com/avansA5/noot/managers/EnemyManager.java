package com.avansA5.noot.managers;

import com.avansA5.noot.objects.GameObject;
import com.avansA5.noot.objects.characters.Boss;
import com.avansA5.noot.objects.characters.Enemy;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.patterns.MovePattern;
import com.avansA5.noot.types.patterns.ShootPattern;
import com.avansA5.noot.types.patterns.patternTemplates.SprayPattern;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by TripleCTMJ on 3-6-2016.
 */
public class EnemyManager
{
    public static Timer timer;
    public static ArrayList<GameObject> enemies;
    public static int enemyNr;
    private static ArrayList<MovePattern> movePatterns;
    private static ArrayList<ShootPattern> shootPatterns;
    private static boolean left;

    public static void start()
    {
        enemyNr = 1;
        enemies = new ArrayList<>();
        timer = new Timer(1000, e -> newWave());
        timer.start();
        movePatterns = new ArrayList<MovePattern>();
        shootPatterns = new ArrayList<ShootPattern>();

    }

    public static void newWave()
    {
        if (enemies.isEmpty())
        {
            if (enemyNr % 10 == 0)
            {
                enemies.add(new Boss());
            } else
            {
                int x;
                int y;

                if (Math.random() > 0.5)
                {
                    x = (int) (Math.random() * 201) + 100;
                    y = (int) (Math.random() * 999);
                    left = true;

                } else
                {
                    x = (int) (Math.random() * 120) + 1630;
                    y = 1 + (int) (Math.random() * 999);
                    left = false;
                }

                for (int i = 0; i < enemyNr; i++)
                {
                    ShootPattern shootPattern = new ShootPattern(1000);
                    shootPattern.addPattern(new SprayPattern((int) (Math.random()) + 2));
                    shootPatterns.add(shootPattern);

                    MovePattern movePattern = new MovePattern((int) (Math.random()) + 2);

                    if(left)
                        movePattern.addTargetPoint(new Point(x - i * 100, y));
                    else
                        movePattern.addTargetPoint(new Point(x + i * 100,y));

                    movePatterns.add(movePattern);
                }

                for (int i = 0; i < 10; i++)
                {
                    int x2 = (int) (450 + (Math.random() * 750));
                    int y2 = (int) (Math.random() * 400);
                    for (MovePattern mp : movePatterns)
                    {
                        mp.addCurveToPoint(new Point(x2, y2));
                    }
                }

                for (int i = 0; i < movePatterns.size(); i++)
                {
                    enemies.add(new Enemy(movePatterns.get(i), shootPatterns.get(i), State.random()));
                }

                movePatterns.removeAll(movePatterns);
                shootPatterns.removeAll(shootPatterns);
                enemyNr++;
            }

        }
    }

    public static void stop()
    {
        timer.stop();
        enemyNr = 1;
    }
}
