package com.avansA5.noot.objects.characters;

import com.avansA5.noot.interfaces.Colliding;
import com.avansA5.noot.managers.*;
import com.avansA5.noot.objects.*;
import com.avansA5.noot.objects.pickups.*;
import com.avansA5.noot.scenes.GameScene;
import com.avansA5.noot.types.State;
import com.avansA5.noot.types.Vector2D;
import com.avansA5.noot.types.WiimoteButtons;
import com.avansA5.noot.util.Log;
import wiiusej.wiiusejevents.physicalevents.*;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Optional;

import static com.avansA5.noot.types.BulletType.ENEMY;
import static com.avansA5.noot.types.BulletType.PLAYER;

/**
 * Created by TripleCTMJ on 19/05/2016.
 */
public class Player extends GameObject implements WiimoteListener, Colliding
{
    CrossHair crossHair;
    State state;
    PlayerPanel panel;
    private CrossHair ch;

    private Animation animation;
    private Animation redAnimation;
    private Animation blueAnimation;

    private int bulletDamage = 1;
    private int playerId;
    private int lives = 6;

    private boolean larger = false;
    public boolean isAlive = true;
    private boolean invinsible;
    private boolean increaseable;
    private boolean sceneIsSet;

    private double playerScale = 0.75;
    private double lookAngle;
    private double moveAngle;
    private double magnitude;
    private double deviation;
    private double speed = 6;

    private Timer decreaseLivesTimer;
    private Timer increaseLivesTimer;


    private final int SPRITE_WIDTH = 130;
    private final int SPRITE_HEIGHT = 120;

    public Player(int player, CrossHair crossHair)
    {
        Log.log("Constructing player "+player);
        this.ch = crossHair;
        ControlManager.addWiimoteListener(this, player);
        playerId = player;
        invinsible = false;
        increaseable = true;
        sceneIsSet = false;
        redAnimation = new Animation("res/PlayerRed.png", 1, 7, 130, 120,25);
        AnimationManager.addAnimation(redAnimation);
        blueAnimation = new Animation("res/PlayerBlue.png", 1, 7, 130, 120,25);
        AnimationManager.addAnimation(blueAnimation);

        if(player==0)
        {
            animation = redAnimation;
            state = State.RED;
        }
        else
        {
            animation = blueAnimation;
            state = State.BLUE;
        }

        panel = GameScene.getPanels()[playerId];
        panel.setHearts(lives);
        panel.setPlayer(playerId);

        vector = new Vector2D(500, 500, animation.getCurrentImage().getWidth()*playerScale, animation.getCurrentImage().getHeight()*playerScale);

        Log.log("Player "+player+" constructed");
        SceneManager.getCurrentScene().getSprites().add(this);
    }

    public Vector2D getCenter()
    {
        return vector;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        if(!isAlive)
            return;

        AffineTransform tr = new AffineTransform();
        tr.translate(vector.getX() + SPRITE_WIDTH/2*playerScale, vector.getY() + SPRITE_HEIGHT/2*playerScale);
        tr.rotate(lookAngle - (Math.PI/2));
        tr.translate(-vector.getX() - SPRITE_WIDTH/2*playerScale, -vector.getY() - SPRITE_HEIGHT/2*playerScale);

        tr.translate(vector.getX(), vector.getY());
        tr.scale(playerScale,playerScale);

        g2.drawImage(animation.getCurrentImage(), tr, null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("ARIAL", Font.BOLD, 25));
        g2.drawString(""+(playerId+1), (int)vector.getX()+SPRITE_WIDTH/3,(int)(vector.getY()+ SPRITE_HEIGHT/2));
        g2.setColor(Color.black);
//        g2.drawOval((int)vector.getX(), (int)vector.getY(), (int)vector.getWidth(), (int)vector.getHeight());
//        g2.draw(vector.toEllipse2D());
    }

    @Override
    public void update()
    {
        if(!isAlive)
            return;
        panel.setScore(GameScene.getScore());

        panel.setHearts(lives);
        double difX = (vector.getX() + 64) - (ch.getLocation().getX() + 32);
        double difY = (vector.getY() + 64) - (ch.getLocation().getY() + 32);

        lookAngle = Math.atan2(difY, difX);


        if (Double.isNaN(moveAngle))
        {
            moveAngle = 0.0;
        }
        if (Double.isNaN(magnitude))
        {
            magnitude = 0.0;
        }

        double cosX = Math.cos(Math.toRadians(moveAngle - 90));
        double cosY = Math.sin(Math.toRadians(moveAngle - 90));

        if (magnitude > deviation)
        {
            // used in case the move coordinates are outside of the boundaries
            double oldX = vector.getX();
            double oldY = vector.getY();

            double moveX = cosX * magnitude * speed;
            double moveY = cosY * magnitude * speed;
            vector.setX(vector.getX() + moveX);
            vector.setY(vector.getY() + moveY);

            //TODO get playerpanel size
            if (vector.getX() < 0 + panel.getPanelWidth()*panel.getScale() ||
                    vector.getX() > Toolkit.getDefaultToolkit().getScreenSize().getWidth()-(panel.getPanelWidth()*panel.getScale())-SPRITE_WIDTH)
                vector.setX(oldX);

            if(vector.getY() < 350 || vector.getY() > Toolkit.getDefaultToolkit().getScreenSize().getHeight()-SPRITE_HEIGHT)
                vector.setY(oldY);
        }
    }

    @Override
    public void onButtonsEvent(WiimoteButtonsEvent wiimoteButtonsEvent)
    {
        switch(wiimoteButtonsEvent.getButtonsJustReleased()){
            case WiimoteButtons.B:
                new Bullet(state, new Vector2D(
                        (vector.getX()+SPRITE_WIDTH/2+ (SPRITE_WIDTH*playerScale) * Math.cos(lookAngle + Math.PI)),
                        (vector.getY()+SPRITE_HEIGHT/2+ (SPRITE_WIDTH*playerScale)  * Math.sin(lookAngle + Math.PI))
                ) , new Vector2D(ch.getLocation().getX(), ch.getLocation().getY()),PLAYER, bulletDamage, larger);
                break;
            default:
                break;
        }

        if(wiimoteButtonsEvent.isButtonHomeJustPressed())
        {
            SceneManager.setScene("StartScene");
        }
    }

    @Override
    public void onIrEvent(IREvent irEvent)
    {
        if(crossHair==null)
            return;
        crossHair.onIrEvent(irEvent);
    }

    @Override
    public void onMotionSensingEvent(MotionSensingEvent motionSensingEvent)
    {

    }

    @Override
    public void onExpansionEvent(ExpansionEvent exp)
    {
        NunchukEvent nun = (NunchukEvent) exp;
        ButtonsEvent but = nun.getButtonsEvent();
        JoystickEvent joy = nun.getNunchukJoystickEvent();
        moveAngle = (double) joy.getAngle();
        magnitude = (double) joy.getMagnitude();


        if (nun.getButtonsEvent().isButtonZJustReleased())
        {
            toggleState();
        }
    }

    void toggleState()
    {
        state = state.next();
        panel.setState(this.state);


        if(state==State.BLUE)
            animation = blueAnimation;
        else
            animation = redAnimation;

        Log.log("Changed state of player "+playerId+" to "+state.name());
    }

    @Override
    public void onCollision(Colliding other)
    {
        if (other instanceof Bullet)
        {
            Bullet bullet = (Bullet) other;
            if (bullet.getBulletType() == ENEMY)
                if (bullet.getState() != state)
                    if (lives > 0)
                        if (!invinsible)
                        {
                            GameScene.setPowerUpsFalse(playerId);
                            larger = false;
                            speed = 6;
                            bulletDamage = 1;
                            double normalWidth = vector.getWidth()/playerScale;
                            double normalHeight = vector.getHeight()/playerScale;
                            playerScale = 0.75;

                            vector.setWidth(normalWidth*playerScale);
                            vector.setHeight(normalHeight*playerScale);
                            lives--;
                            decreaseLivesTimer = new Timer(2000, e -> notInvinsible());
                            decreaseLivesTimer.start();
                            invinsible = true;
                        }
        }

        if(other instanceof Rock)
        {
            if(lives > 0)
            {
                if(!invinsible)
                {
                    GameScene.setPowerUpsFalse(playerId);
                    larger = false;
                    speed = 6;
                    bulletDamage = 1;
                    double normalWidth = vector.getWidth()/playerScale;
                    double normalHeight = vector.getHeight()/playerScale;
                    playerScale = 0.75;

                    vector.setWidth(normalWidth*playerScale);
                    vector.setHeight(normalHeight*playerScale);
                    decreaseLivesTimer = new Timer(2000, e -> notInvinsible());
                    decreaseLivesTimer.start();
                    lives--;
                    invinsible = true;
                }
            }
        }

        if(other instanceof Heart)
        {
            if(increaseable)
            {
                if(lives < 6) {
                    lives+= 2;
                    if(lives > 6)
                    {
                        lives = 6;
                    }

                    panel.setHearts(lives);
                    increaseLivesTimer = new Timer(3000, e -> notIncreasable());
                    increaseLivesTimer.start();
                    increaseable = false;
                }
            }

        }

        if(other instanceof BiggerBullet)
        {
            GameScene.powerUpOne(playerId);
            larger = true;
        }
        if(other instanceof FasterMovement)
        {
            GameScene.powerUpThree(playerId);
            speed = 9;
        }
        if(other instanceof StrongerBullets)
        {
            GameScene.powerUpTwo(playerId);
            bulletDamage = 2;
        }
        if(other instanceof ShrinkPlayer)
        {
            GameScene.powerUpFour(playerId);
            double normalWidth = vector.getWidth()/playerScale;
            double normalHeight = vector.getHeight()/playerScale;
            playerScale = 0.6;

            vector.setWidth(normalWidth*playerScale);
            vector.setHeight(normalHeight*playerScale);

        }

        if(lives == 0)
        {
            if(Boss.isAlive) {
                Boss.stopSound();
            }
            isAlive = false;
            //Get our other player
            Optional<GameObject> gameObject = SceneManager.getCurrentScene().getSprites().parallelStream().filter(e -> e instanceof Player && e != this).findFirst();
            if(gameObject.isPresent())
            {
                Player p = (Player) gameObject.get();
                if(!p.isAlive)
                {
                    if(!sceneIsSet) {
                        panel.setHearts(lives);
                        SceneManager.setScene("GameOverScene");
                        sceneIsSet = true;
                    }



                }
                else
                {
                    ControlManager.clearWiiMoteListener(playerId);
                    GameScene.getPanel(playerId).setHearts(0);
                    dead = true;
                }
            }
            else
            {
                if(!sceneIsSet) {
                    panel.setHearts(lives);
                    SceneManager.setScene("GameOverScene");
                    sceneIsSet = true;
                }
            }

        }
    }

    private void notInvinsible()
    {
        invinsible = false;
        decreaseLivesTimer.stop();
    }

    private void notIncreasable()
    {
        increaseable = true;
        increaseLivesTimer.stop();
    }

    public State getState()
    {
        return state;
    }

    public boolean getInvinsible()
    {
        return invinsible;
    }

    @Override
    public void onStatusEvent(StatusEvent statusEvent)
    {

    }

    @Override
    public void onDisconnectionEvent(DisconnectionEvent disconnectionEvent)
    {

    }

    @Override
    public void onNunchukInsertedEvent(NunchukInsertedEvent nunchukInsertedEvent)
    {

    }

    @Override
    public void onNunchukRemovedEvent(NunchukRemovedEvent nunchukRemovedEvent)
    {

    }

    @Override
    public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent guitarHeroInsertedEvent)
    {

    }

    @Override
    public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent guitarHeroRemovedEvent)
    {

    }

    @Override
    public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent classicControllerInsertedEvent)
    {

    }

    @Override
    public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent classicControllerRemovedEvent)
    {

    }


}