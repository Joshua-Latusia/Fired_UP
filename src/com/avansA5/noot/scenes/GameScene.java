package com.avansA5.noot.scenes;

import com.avansA5.noot.interfaces.Colliding;
import com.avansA5.noot.managers.*;
import com.avansA5.noot.objects.*;
import com.avansA5.noot.objects.characters.*;
import com.avansA5.noot.util.SoundPlayer;

import java.awt.*;
import java.util.*;

/**
 * Created by TripleCTMJ on 19/05/2016.
 */
public class GameScene extends Scene
{
    private static PlayerPanel[] panels = new PlayerPanel[2];

    public static PlayerPanel[] getPanels()
    {
        return panels;
    }

    public static PlayerPanel getPanel(int i)
    {
        return panels[i];
    }

    private static Score score;
    private static SoundPlayer soundPlayer;

    @Override
    public void update()
    {
        super.update();
        for(PlayerPanel p : panels)
            if(p != null)
                p.update();

        checkCollision();
    }

    @Override
    public void draw(Graphics2D g2)
    {

        super.draw(g2);

        for(PlayerPanel p : panels)
            if(p != null)
                p.draw(g2);


    }

    @Override
    public void load() {
        score = new Score();
        score.setScore(999);
        soundPlayer = new SoundPlayer("res//PlayingSound.wav");
        soundPlayer.loop();
        panels[0] = new PlayerPanel(0);
        panels[1] = new PlayerPanel(1);
        sprites.add(new ScrollingBackground());


        EnemyManager.start();
        PickUpManager.start();
        ObstacleManager.start();


        if(StartScene.getStatus()) {
            if (ControlManager.wiimotes.size() == 2) {
                CrossHair crossHair1 = new CrossHair(0);
                new Player(0, crossHair1);

                CrossHair crossHair2 = new CrossHair(1);
                new Player(1, crossHair2);
            }
            else
            {
                EnemyManager.stop();
                PickUpManager.stop();
                ObstacleManager.stop();
                SceneManager.setScene("StartScene");
            }
        }
        else
        {
            CrossHair crossHair1 = new CrossHair(0);
            new Player(0, crossHair1);
        }


    }

    @Override
    public void unload()
    {
        AnimationManager.clearAnimations();
        score.stop();
        ObstacleManager.stop();
        PickUpManager.stop();
        EnemyManager.stop();
        soundPlayer.stop();
        sprites.removeAll(sprites);
        uiElements.removeAll(uiElements);

    }

    private void checkCollision()
    {
        Object[] objects = SceneManager.getCurrentScene().getSprites().parallelStream().filter(gameObject -> gameObject instanceof Colliding).toArray();

        Colliding[] coll = new Colliding[objects.length];

        for(int i = 0; i < objects.length; i++)
            coll[i] = (Colliding) objects[i];

        java.util.List<Colliding> colliders = Arrays.asList(coll);

        colliders.forEach(gameObject ->
                colliders.forEach(e -> {
                    GameObject kek = (GameObject) e;
                    double diffX = (kek.getCenter().getX()) - (((GameObject) gameObject).getCenter().getX());
                    double diffY = (kek.getCenter().getY()) - (((GameObject) gameObject).getCenter().getY());
                    double distance = Math.sqrt((diffX * diffX) + (diffY * diffY));

                    double collisionDistance = (kek.getVector().getWidth() / 2) + (gameObject.getVector().getWidth() / 2);
                    if(distance < collisionDistance)
                    {
                        if(e!=gameObject)
                        {
                            e.onCollision(gameObject);
                            gameObject.onCollision(e);
                        }
                    }
                }));
    }

//    public static void stopMusic()
//    {
//        soundPlayer.stop();
//    }
//
//    public static void startMusic()
//    {
//        soundPlayer.loop();
//    }

    public static void saveScore(){
        stopScore();
        ScoreManager.addScore(score);
    }

    public static void stopScore(){
        score.stop();
    }

    public static int getScore(){
        return score.getScore();
    }

    public static void setPowerUpsFalse(int player){
        panels[player].setPowerUpsFalse();
    }

    public static void powerUpOne(int player){
        panels[player].powerUpOne();
    }
    public static void powerUpTwo(int player){
        panels[player].powerUpTwo();
    }
    public static void powerUpThree(int player){
        panels[player].powerUpThree();
    }
    public static void powerUpFour(int player){
        panels[player].powerUpFour();
    }
}
