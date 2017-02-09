package com.avansA5.noot.objects;

import com.avansA5.noot.managers.ScoreManager;
import com.avansA5.noot.managers.WindowManager;
import com.avansA5.noot.scenes.GameScene;
import com.avansA5.noot.types.State;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by TripleCTMJ on 5/24/2016.
 */
public class PlayerPanel extends GameObject
{

    private static final int PANEL_WIDTH = 390;
    private static final int PANEL_HEIGHT = 1080;
    // Positions for drawing on the stats
    private  Point highscorePos, scorePos, playerPos, power1Pos, power2Pos, power3Pos, power4Pos;
    private boolean power1On = false, power2On = false, power3On = false, power4On = false;
    private Point difPos = new Point(20,20);

    private ArrayList<Point> heartpos = new ArrayList<>();
    private final int playerId;

    // Image of the side panels
    private BufferedImage playerPanelImg, power1, power2, power3, power4;
    private BufferedImage selectedRed;
    private BufferedImage selectedBlue;
    private BufferedImage leftBlackHeart;
    private BufferedImage rightBlackHeart;
    private int playerScore;
    private String playerNr = "";
    private String difficulty = "";
    private int highscore = ScoreManager.getHighScore();
    private int heartCount = 0;
    private State state = State.RED;
    private double scale;


    public PlayerPanel(int playerId)
    {
        this.playerId = playerId;
        if(playerId == 0) {
            heartpos.add(new Point(256, 780));
            heartpos.add(new Point(256, 780));
            heartpos.add(new Point(147, 780));
            heartpos.add(new Point(147, 780));
            heartpos.add(new Point(39, 780));
            heartpos.add(new Point(39, 780));
            highscorePos = new Point(170, 38);
            scorePos = new Point(110, 98);
            playerPos = new Point(110, 160);
            difPos  = new Point(110,500);
            playerNr = "1";

            power1Pos = new Point(80, 270);
            power2Pos = new Point(220, 270);
            power3Pos = new Point(80, 410);
            power4Pos = new Point(220, 410);
        }

        if(playerId == 1)
        {
            heartpos.add(new Point(1565, 778));
            heartpos.add(new Point(1565, 778));
            heartpos.add(new Point(1674, 778));
            heartpos.add(new Point(1674, 778));
            heartpos.add(new Point(1783, 778));
            heartpos.add(new Point(1783, 778));
            highscorePos = new Point(1680, 38);
            scorePos = new Point(1620, 98);
            playerPos = new Point(1625, 160);
            difPos = new Point(110, 500);
            playerNr = "2";

            power1Pos = new Point(1610, 270);
            power2Pos = new Point(1750, 270);
            power3Pos = new Point(1610, 410);
            power4Pos = new Point(1750, 410);
        }

        try
        {
            playerPanelImg = ImageIO.read(new File("res" + File.separator + "PlayerPanel.png"));
            selectedBlue = ImageIO.read(new File("res" + File.separator + "BlueSelected.png"));
            selectedRed = ImageIO.read(new File("res" + File.separator + "RedSelected.png"));
            leftBlackHeart = ImageIO.read(new File("res" + File.separator + "LeftBlackHeart.png"));
            rightBlackHeart = ImageIO.read(new File("res" + File.separator + "RightBlackHeart.png"));

            power1 = ImageIO.read(new File("res" + File.separator + "PowerupbiggerBullet.png"));
            power2 = ImageIO.read(new File("res" + File.separator + "PowerupStrongerBullet.png"));
            power3 = ImageIO.read(new File("res" + File.separator + "PowerupFasterMovement.png"));
            power4 = ImageIO.read(new File("res" + File.separator + "PowerupShrinkPlayer.png"));

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        calculateScale();


    }

    public void setScore(int newScore)
    {
        playerScore = newScore;
    }

    public void setHearts(int heartCount)
    {
        switch(heartCount)
        {
            case 0:
                this.heartCount = 6;
                break;
            case 1:
                this.heartCount = 5;
                break;
            case 2:
                this.heartCount = 4;
                break;
            case 3:
                this.heartCount = 3;
                break;
            case 4:
                this.heartCount = 2;
                break;
            case 5:
                this.heartCount = 1;
                break;
            case 6:
                this.heartCount = 0;
                break;
        }
    }

    public void setPlayer(int playerNr)
    {
        this.playerNr = ""+playerNr;
    }


    public void setState(State state)
    {
        this.state = state;
    }

    public  int getPanelWidth() {
        return PANEL_WIDTH;
    }

    // draws a string a point in a readable way
    private void drawString(Graphics2D g2, Point p, String text)
    {
        float xPos = (float) p.getX()*(float)scale;
        float yPos = (float) p.getY()*(float)scale;

        g2.setColor(Color.black);
        for(int x = -1; x <= 1; x++)
            for(int y = -1; y <= 1; y++)
                g2.drawString(text, xPos + x, yPos+y);
        g2.setColor(Color.white);
        g2.drawString(text,xPos, yPos);

    }

    private void drawSelectedColor(Graphics2D g2)
    {

        AffineTransform t = new AffineTransform();
        switch(this.state)
        {
            case BLUE:
            if(playerId==1)
            t.translate(WindowManager.getDimension().getWidth()- ((getPanelWidth()*scale) - (210*scale)), 565*scale);

            else
            t.translate(210*scale, 565*scale);
            t.scale(scale, scale);
            g2.drawImage(selectedBlue,t,null);
            break;

            case RED:
            if(playerId==1)
            t.translate(WindowManager.getDimension().getWidth()- ((getPanelWidth()*scale)-(45*scale)), 565*scale);
            else
                t.translate(45*scale, 565*scale);

            t.scale(scale, scale);
            g2.drawImage(selectedRed,t,null);
            break;
        }

    }

    public void draw(Graphics2D g2)
    {
        AffineTransform transform = new AffineTransform();

        if(playerId==1)
            transform.translate(WindowManager.getDimension().getWidth()-390*scale, 0);

        transform.scale(scale,scale);


        g2.drawImage(playerPanelImg,transform,null);


        g2.setFont(g2.getFont().deriveFont(28.0f));


        drawString(g2,highscorePos,""+highscore);
        drawString(g2,scorePos, ""+playerScore);
        drawString(g2,playerPos, playerNr );
        drawString(g2,difPos, difficulty );
        drawSelectedColor(g2);

        if(power1On){
            g2.drawImage(power1, (int)power1Pos.getX(), (int)power1Pos.getY(), null);
        }
        if(power2On){
            g2.drawImage(power2, (int)power2Pos.getX(), (int)power2Pos.getY(), null);
        }
        if(power3On){
        g2.drawImage(power3, (int)power3Pos.getX(), (int)power3Pos.getY(), null);
        }
        if(power4On){
            g2.drawImage(power4, (int)power4Pos.getX(), (int)power4Pos.getY(), null);
        }

        for(int i = 0; i < heartCount; i++)
        {
            if(i % 2 == 0) {
                AffineTransform tr = new AffineTransform();
                tr.translate((int) heartpos.get(i).getX(), (int) heartpos.get(i).getY());
                tr.scale(scale, scale);
                if(playerId == 0)
                g2.drawImage(leftBlackHeart, tr, null);
                else
                    g2.drawImage(rightBlackHeart,tr,null);
            }
            else
            {
                    AffineTransform tr = new AffineTransform();
                    tr.translate((int) heartpos.get(i).getX(), (int) heartpos.get(i).getY());
                    tr.scale(scale, scale);
                if(playerId == 0)
                    g2.drawImage(rightBlackHeart, tr, null);
                else
                    g2.drawImage(leftBlackHeart,tr,null);

            }
        }
    }

    public void update()
    {
        highscore = ScoreManager.getHighScore();
        playerScore = GameScene.getScore();
    }

    public double getScale() {
        return scale;}

    private void calculateScale()
    {
        scale = WindowManager.getDimension().getHeight() / playerPanelImg.getHeight();
    }

    public void setPowerUpsFalse(){
        power1On = false;
        power2On = false;
        power3On = false;
        power4On = false;
    }

    public void powerUpOne(){
        power1On = true;
    }
    public void powerUpTwo(){
        power2On = true;
    }
    public void powerUpThree(){
        power3On = true;
    }
    public void powerUpFour(){
        power4On = true;
    }
}
