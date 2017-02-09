package com.avansA5.noot.objects.characters;

import com.avansA5.noot.interfaces.Colliding;
import com.avansA5.noot.managers.AnimationManager;
import com.avansA5.noot.managers.SceneManager;
import com.avansA5.noot.managers.WindowManager;
import com.avansA5.noot.objects.Animation;
import com.avansA5.noot.objects.Bullet;
import com.avansA5.noot.objects.GameObject;
import com.avansA5.noot.scenes.GameScene;
import com.avansA5.noot.scenes.StartScene;
import com.avansA5.noot.types.BulletType;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.Vector2D;
import com.avansA5.noot.types.patterns.MovePattern;
import com.avansA5.noot.types.patterns.ShootPattern;
import com.avansA5.noot.types.patterns.patternTemplates.BossSprayPattern;
import com.avansA5.noot.types.patterns.patternTemplates.BossStraightPattern;
import com.avansA5.noot.types.patterns.patternTemplates.SprayPattern;
import com.avansA5.noot.util.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cas on 20/05/2016.
 */
public class Boss extends GameObject implements Colliding
{
    private final Animation animation;
    private final double bossScale = 3;
    private static SoundPlayer player;
    private Timer decreaseLivesTimer;
    private boolean invinsible;
    private final int SPRITE_HEIGHT = 90;
    private final int SPRITE_WIDTH = 100;
    private boolean movingRight;
    private double currentLives = 50;
    private double startingLives = 50;

    private Stage stage = Stage.STAGE1;
    // Healtbar stuff and cas please no touchy this
    private double playerPanelWidth = GameScene.getPanel(0).getPanelWidth();
    private double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private double screenWidth =  Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int healtY = 50;
    public static boolean isAlive = false;

    private ShootPattern shootPattern1;
    private ShootPattern shootPattern2;
    private ShootPattern shootPattern3;
    private ShootPattern currentShootPattern;



    public Boss() {
        createShootPatterns();
        movingRight = true;
        stage = Stage.STAGE1;
        animation = new Animation("res" + File.separator + "Boss.png",
                1, 4, SPRITE_WIDTH, SPRITE_HEIGHT, 10);

        AnimationManager.addAnimation(animation);
        isAlive = true;
        vector = new Vector2D(800, -200, SPRITE_WIDTH * bossScale, SPRITE_HEIGHT * bossScale);

        SceneManager.getCurrentScene().getSprites().add(this);

        player = new SoundPlayer("res//Dragon_Roar_2.wav");
        player.loop(10);

        if(StartScene.getStatus())
            currentLives *= 2;
    }

    private void AI()
    {
        switch(stage)
        {
            case STAGE1:
                invinsible = true;
                vector.setY(vector.getY() + 1);
                if (vector.getY() > 0)
                {
                    invinsible = false;
                    stage = Stage.STAGE2;
                }
                break;
            case STAGE2:
                if(currentLives < 34)
                    stage = Stage.STAGE3;
                if (movingRight)
                {
                    vector.setX(vector.getX() + 1);
                } else
                {
                    vector.setX(vector.getX() - 1);
                }

                if (vector.getX() < 390)
                {
                    movingRight = true;
                }

                if (vector.getX() > (WindowManager.panel.getWidth() - 390 * GameScene.getPanels()[0].getScale()) - vector.getWidth())
                {
                    movingRight = false;
                }

                currentShootPattern = shootPattern1;
                shoot();
                break;
            case STAGE3:
                double halfpanel = (WindowManager.panel.getWidth() - 390 * GameScene.getPanels()[0].getScale())/2;
                double rightside = WindowManager.panel.getWidth() - 390 * GameScene.getPanels()[0].getScale() + vector.getWidth();

                if(vector.getX() >  halfpanel && vector.getX() < rightside )
                {
                    movingRight = false;
                }

                if(vector.getX() > 0 && vector.getX() < halfpanel)
                {
                    movingRight = true;
                }

                if(vector.getX() > halfpanel-5 && vector.getX() < halfpanel + 5)
                {
                    currentShootPattern = shootPattern2;
                    shoot();
                }
                else
                {
                    if (movingRight)
                    {
                        vector.setX(vector.getX() + 5);
                    }

                    if (!movingRight)
                    {
                        vector.setX(vector.getX() - 5);
                    }
                }

                if(currentLives <16)

                    stage = Stage.STAGE4;
                break;
            case STAGE4:
                if (movingRight)
                {
                    vector.setX(vector.getX() + 1);
                } else
                {
                    vector.setX(vector.getX() - 1);
                }

                if (vector.getX() < 390)
                {
                    movingRight = true;
                }

                if (vector.getX() > (WindowManager.panel.getWidth() - 390 * GameScene.getPanels()[0].getScale()) - vector.getWidth())
                {
                    movingRight = false;
                }

                currentShootPattern = shootPattern3;
                shoot();
                break;
        }
    }

    private void shoot()
    {
            State state = State.random();
            ArrayList<Bullet> bullets = currentShootPattern.getBullets(vector, state,(int) vector.getWidth(), (int) vector.getHeight(), true);

            for (Bullet b : bullets)
            {
                SceneManager.getCurrentScene().addSprite(b);
            }
        }

    @Override
    public void draw(Graphics2D g2) {
        // not sure if nesseserrie
        if(!this.dead)
            drawHealth(g2);

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(vector.getX(), vector.getY());
        affineTransform.scale(bossScale, bossScale);
        g2.drawImage(animation.getCurrentImage(), affineTransform, null);
//        g2.draw(new Ellipse2D.Double((int) getVector().getX(), (int) getVector().getY(), (int) getVector().getWidth(), (int) getVector().getHeight()));
        //g2.drawOval((int)vector.getX(),(int)vector.getY(),(int)(vector.getWidth()*bossScale),(int)(vector.getHeight()*bossScale));
    }

    @Override
    public void update()
    {
        AI();
    }

    @Override
    public void onCollision(Colliding other)
    {
        if (other instanceof Bullet)
        {
            Bullet bullet = (Bullet) other;
            if (bullet.getBulletType() == BulletType.PLAYER)
            {
                if (!invinsible)
                {
                    decreaseLivesTimer = new Timer(300, e -> notInvinsible());
                    new SoundPlayer("res//BulletSound1.wav").start();
                    decreaseLivesTimer.start();
                    invinsible = true;
                    currentLives -= bullet.getDamage();

                }
            }
        }

    if(currentLives <=0)

    {
        dead = true;
        AnimationManager.removeAnimation(animation);
        GameScene.saveScore();
        SceneManager.setScene("WinnerScene");
        player.stop();

    }

    }

    private void drawHealth(Graphics2D g2)
    {
        // black background at bottom of the screen with 4% of the screen
        g2.fillRect((int)playerPanelWidth, ((int)(screenHeight-(screenHeight/(healtY*3)))),
                (int)(screenWidth-(playerPanelWidth*2)), (int)(screenHeight/(healtY*3)));
        g2.setColor(Color.RED);

        RoundRectangle2D roundrec = new RoundRectangle2D.Double(playerPanelWidth, (screenHeight-(screenHeight/(healtY*3))),
                                    ((screenWidth-(playerPanelWidth+playerPanelWidth))*(currentLives/startingLives))
                                    , screenHeight/(healtY*3),35,35);
        g2.fill(roundrec);
        g2.setColor(Color.BLACK);

        if(healtY >= 9)
            healtY--;

    }

    private void notInvinsible()
    {
        invinsible = false;
        decreaseLivesTimer.stop();
    }

    @Override
    public Vector2D getCenter()
    {
        return new Vector2D(vector.getX() + (vector.getWidth() / 2), vector.getY() + (vector.getHeight() / 2), vector.getWidth(), vector.getHeight());
    }

    public boolean getInvinsible()
    {
        return invinsible;
    }

    private void createShootPatterns()
    {
        shootPattern1 = new ShootPattern(1000);
        shootPattern2 = new ShootPattern(500);
        shootPattern3 = new ShootPattern(1000);

        shootPattern1.addPattern(new SprayPattern(19));

        shootPattern2.addPattern(new BossSprayPattern(19, 20));

        shootPattern3.addPattern(new BossStraightPattern(20));

    }

    enum Stage
    {
        STAGE1,STAGE2,STAGE3,STAGE4
    }

    public static void stopSound()
    {
        player.stop();
    }
}
