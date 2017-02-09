package com.avansA5.noot.objects;

import com.avansA5.noot.interfaces.Colliding;
import com.avansA5.noot.interfaces.GiveDamage;
import com.avansA5.noot.managers.AnimationManager;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.objects.characters.Boss;
import com.avansA5.noot.objects.characters.Enemy;
import com.avansA5.noot.objects.characters.Player;
import com.avansA5.noot.scenes.GameScene;
import com.avansA5.noot.types.BulletType;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by TripleCTMJ on 03/06/2016.
 * Refactored by Joshua on 17/06/2016.
 */

public class Bullet extends GameObject implements GiveDamage , Colliding
{
    private final BulletType type;
    private final State state;
    private final Animation animation;
    private final PlayerPanel panel;
    private final double animationTimer;
    private int size=32;
    private long oldTime = 0;
    private int damage = 1;
    boolean isLargeBullet;

    public Bullet(State state, Vector2D spawnPos, Vector2D targetPos, BulletType type, int damage, boolean isLargeBullet)
    {
        this.isLargeBullet = isLargeBullet;
        if(isLargeBullet){
            size = size*2;
        }
        this.damage = damage;
        this.type = type;
        animationTimer = 20;
        this.state = state;
        if (state == State.BLUE)
            animation = new Animation("res//blueFireballTileset.png", 1, 6, 64, 64, 30);
        else
            animation = new Animation("res//redFireballTileset.png", 1, 6, 64, 64,30);

        double diffX = targetPos.getX() + 16 - spawnPos.getX();
        double diffY = targetPos.getY() + 16 - spawnPos.getY();
        double direction = Math.atan2(diffY, diffX);

        vector = new Vector2D(spawnPos.getX() , spawnPos.getY(), animation.getCurrentImage().getWidth(), animation.getCurrentImage().getHeight(), direction, 8);

        AnimationManager.addAnimation(animation);

        panel = GameScene.getPanels()[0];
        // michael said this was okay;

        SceneManager.getCurrentScene().addSprite(this);
    }

    @Override
    public Vector2D getCenter()
    {
        Vector2D newVector;
        if(isLargeBullet){
            newVector = new Vector2D(vector.getX() - (vector.getWidth() / 2), vector.getY() - (vector.getHeight() / 2), (vector.getWidth()*2), (vector.getHeight()*2));
    }
        else {
            newVector = new Vector2D(vector.getX() - vector.getWidth() / 2, vector.getY() - vector.getHeight() / 2, vector.getWidth(), vector.getHeight());
        }
        return newVector;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        AffineTransform at = new AffineTransform();
        at.translate((int)vector.getX(), (int)vector.getY());
        at.rotate(vector.getDirection());
        at.translate(-((int)vector.getX() + size), -((int)vector.getY() + size));
        at.translate((int) vector.getX(), (int) vector.getY());
        if(isLargeBullet){
            at.scale(1.5,1.5);
        }
        g2.drawImage(animation.getCurrentImage(),at, null);

    }

    @Override
    public void update()
    {
        if(panel != null) {
            if (vector.getX() < panel.getPanelWidth() * panel.getScale() - 50 || vector.getX() > Toolkit.getDefaultToolkit().getScreenSize().getWidth() - panel.getPanelWidth() * panel.getScale() + 50)
                dead = true;

            if (vector.getY() < -75 || vector.getY() > Toolkit.getDefaultToolkit().getScreenSize().getHeight() + 50)
                dead = true;
        }
        animation.update();

        long newTime = System.currentTimeMillis();
        if(newTime - oldTime > animationTimer)
        {
            oldTime = newTime;
            double moveX = Math.cos(vector.getDirection());
            double moveY = Math.sin(vector.getDirection());
            vector.setX(vector.getX() + moveX * vector.getSpeed());
            vector.setY(vector.getY() + moveY * vector.getSpeed());
        }

    }

    public BulletType getBulletType()
    {
        return type;
    }

    public int getDamage()
    {
        return damage;
    }

    public void onCollision(Colliding other)
    {
        if(other instanceof Player)
        {
            Player player = (Player) other;
            if(getBulletType() == BulletType.ENEMY)
            {
                if(getState() != player.getState()) {
                    if (!player.getInvinsible()) {
                        dead = true;
                    }
                }
            }
        }

        if(other instanceof Enemy)
        {
            Enemy enemy = (Enemy) other;
            if(getBulletType() == BulletType.PLAYER)
            {
                if(getState() !=enemy.getState())
                    dead = true;
            }
        }

        if(other instanceof Boss)
        {
            if(getBulletType() == BulletType.PLAYER)
            {
                dead = true;
            }
        }
    }

    public State getState()
    {
        return state;
    }
}
