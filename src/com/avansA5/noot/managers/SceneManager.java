package com.avansA5.noot.managers;


import com.avansA5.noot.scenes.*;
import com.avansA5.noot.util.Log;

import java.awt.*;
import java.util.HashMap;

public class SceneManager
{
    private static final HashMap<String, Scene> scenes = new HashMap<>();
    private static Scene currentScene;

    public static void start()
    {
        Log.log("Starting SceneManager");
        scenes.put("StartScene", new StartScene());
        scenes.put("TutorialScene", new TutorialScene());
        scenes.put("GameScene", new GameScene());
        scenes.put("GameOverScene", new GameOverScene());
        scenes.put("WinnerScene", new WinnerScene());
        scenes.put("PowerUpScene", new PowerUpScene());
        setScene("StartScene");
        Log.log("SceneManager started");
    }

    public static void setScene(String name)
    {
        if (currentScene != null)
            currentScene.unload();
        currentScene = scenes.get(name);
        currentScene.load();
        Log.log("Loaded scene: " + name);
    }

    static void draw(Graphics2D g2)
    {
        if (currentScene != null)
            currentScene.draw(g2);
    }

    static void update()
    {
        if (currentScene != null)
            currentScene.update();
    }

    public static Scene getCurrentScene()
    {
        return currentScene;
    }

}
