package com.avansA5.noot.objects;

import javax.swing.*;
import java.io.Serializable;

/**
 * Created by TripleCTMJ on 07/06/2016.
 * Refactored by Joshua on 17/06/2016.
 *
 * Score for a player
 */
public class Score implements Serializable{
    private static int score;
    private Timer timer;
    private int decreasePerSec = 2;

    public Score(){
        timer = new Timer(1000/decreasePerSec, e -> downScore());
        timer.start();
    }

    public void setScore(int score){
        this.score = score;
    }

    public void downScore(){
        score--;
    }

    public static int getScore() {
        return score;
    }

    public void stop(){
        timer.stop();
    }

}
