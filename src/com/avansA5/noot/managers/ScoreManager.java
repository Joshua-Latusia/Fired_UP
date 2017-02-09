package com.avansA5.noot.managers;

import com.avansA5.noot.objects.Score;
import com.avansA5.noot.util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by TripleCTMJ on 07/06/2016.
 */
public class ScoreManager implements Serializable {

    private static List<Integer> scores = new ArrayList<>();

    public static void addScore(Score score){
        Log.log("Adding score");
        scores.add(score.getScore());
        sort();
        while(scores.size() > 1)
            scores.remove(scores.size()-1);
}

    public static void stop() {
        Log.log("Stopping ScoreManager");
        try {
            ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream("Scores.dat"));
            Log.log("Saving files");
            s.writeObject(scores);
            s.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Log.log("ERROR 404 - file not found");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.log("ERROR");
            e.printStackTrace();
        }
    }


    public static void start() {
        Log.log("Starting ScoreManager");
        try {
            ObjectInputStream s = new ObjectInputStream(new FileInputStream("Scores.dat"));
            Log.log("Loading files");
            scores = (ArrayList<Integer>) s.readObject();

        } catch (IOException | ClassNotFoundException e)
        {
            scores = new ArrayList<>();
            scores.add(0);
            e.printStackTrace();
        }
    }


    public static void sort(){
        Log.log("Sorting scores");
        Collections.sort(scores, new Comparator<Integer>(){
                    @Override public int compare(Integer s1, Integer s2){
                        return s2 - s1;
                    }
                }
        );
    }

    public static int getHighScore(){
        try
        {
            return scores.get(0);
        } catch (Exception e)
        {
            return 0;
        }
    }
}
