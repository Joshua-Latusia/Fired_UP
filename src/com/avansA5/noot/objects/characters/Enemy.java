package com.avansA5.noot.objects.characters;

import com.avansA5.noot.interfaces.Colliding;
import com.avansA5.noot.managers.AnimationManager;
import com.avansA5.noot.managers.EnemyManager;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.objects.Animation;
import com.avansA5.noot.objects.Bullet;
import com.avansA5.noot.objects.GameObject;
import com.avansA5.noot.types.BulletType;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.Vector2D;
import com.avansA5.noot.types.patterns.MovePattern;
import com.avansA5.noot.types.patterns.ShootPattern;
import com.avansA5.noot.util.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Cas on 20/05/2016.
 */
public class Enemy extends GameObject implements Colliding
{
    private final Animation animation;
    private final State state;
    private final ShootPattern shootPattern;
    private int lives = 3;
    private MovePattern movePattern;
    private double moveDirection = 0;
    private Timer decreaseLivesTimer;
    private final int SPRITE_HEIGHT = 75;
    private final int SPRITE_WIDTH = 100;
    private boolean invinsible;

    public Enemy(MovePattern pattern, ShootPattern shootPattern, State state)
    {
        this.state = state;
        this.movePattern = pattern;
        this.shootPattern = shootPattern;
        this.vector = new Vector2D(pattern.getNextPoint().getX(), pattern.getNextPoint().getY(), SPRITE_WIDTH, SPRITE_HEIGHT);

        Animation redAnimation = new Animation("res/EnemyRed.png", 1, 4, 100, 75, 15);
        AnimationManager.addAnimation(redAnimation);
        Animation blueAnimation = new Animation("res/EnemyBlue.png", 1, 4, 100, 75, 15);
        AnimationManager.addAnimation(blueAnimation);

        if(state == State.RED)
        {
            animation = redAnimation;
        }
        else
        {
            animation = blueAnimation;
        }
        SceneManager.getCurrentScene().getSprites().add(this);
        movePattern = pattern;
    }

    @Override
    public Vector2D getCenter()
    {
        return new Vector2D(vector.getX() + (vector.getWidth() / 2), vector.getY() + (vector.getHeight() / 2), vector.getWidth(), vector.getHeight());
    }

    private void move()
    {
        if(movePattern.checkCurrentPosition(vector))
        {
            Point p = movePattern.getNextPoint();

            double dx = p.getX() - vector.getX();
            double dy = p.getY() - vector.getY();
            moveDirection = Math.atan2(dy,dx);
        }

        double moveX = Math.cos(moveDirection) * movePattern.getSpeed();
        double moveY = Math.sin(moveDirection) * movePattern.getSpeed();
        vector.setX(vector.getX() + moveX);
        vector.setY(vector.getY() + moveY);
    }

    private void shoot()
    {

            ArrayList<Bullet> bullets = shootPattern.getBullets(vector, state, SPRITE_WIDTH, SPRITE_HEIGHT, false);

            for(Bullet b : bullets)
            {
                SceneManager.getCurrentScene().addSprite(b);
            }

    }

    @Override
    public void draw(Graphics2D g2)
    {
        g2.drawImage(animation.getCurrentImage(), (int)vector.getX(), (int)vector.getY(), null);
//        g2.draw(new Ellipse2D.Double((int) getVector().getX(), (int) getVector().getY(), (int) getVector().getWidth(), (int) getVector().getHeight()));
    }

    @Override
    public void update()
    {
        if(lives <= 0)
        {
            EnemyManager.enemies.remove(this);
            AnimationManager.removeAnimation(animation);
            dead = true;
        }

        if(!movePattern.endReached())
        {
            move();
        }
        shoot();
    }

    @Override
    public void onCollision(Colliding other)
    {
       if(other instanceof Bullet)
       {
           Bullet bullet = (Bullet) other;
           if(bullet.getBulletType() == BulletType.PLAYER)
           {
               if(bullet.getState() != state)
               {
                   if(!invinsible)
                   {
                       decreaseLivesTimer = new Timer(300, e -> notInvinsible());
                       new SoundPlayer("res//BulletSound1.wav").start();
                       decreaseLivesTimer.start();
                       invinsible = true;
                       lives -= bullet.getDamage();
                   }
               }
           }
       }
    }

    private void notInvinsible()
    {
        invinsible = false;
        decreaseLivesTimer.stop();
    }

    public State getState()
    {
        return state;
    }
}
