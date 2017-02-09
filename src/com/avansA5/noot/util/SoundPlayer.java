package com.avansA5.noot.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * Created by TripleCTMJ on 7-6-2016.
 */
public class SoundPlayer
{
    private Clip clip;

    public SoundPlayer(String URL)
    {
        try
        {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(URL));
            clip = AudioSystem.getClip();
            clip.open(sound);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void start(float decibels)
    {
        decreaseVolume(decibels);
        clip.start();
    }

    public void start()
    {
        clip.start();
    }

    public void stop()
    {
        clip.stop();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void loop(float decibels)
    {
        decreaseVolume(decibels);
        clip.loop(Clip.LOOP_CONTINUOUSLY);}

    public void decreaseVolume(float decibels){
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(-decibels);
    }
}
