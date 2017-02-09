package com.avansA5.noot.managers;

import com.avansA5.noot.Program;
import com.avansA5.noot.util.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WindowManager extends JPanel
{
    private static final Dimension dimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 1);
    public static WindowManager panel;
    public static JFrame frame;

    private WindowManager()
    {

        Timer repaintTimer = new Timer(1000 / 60, e -> repaint());
        repaintTimer.start();
        setBackground(Color.black);

    }

    public static Dimension getDimension()
    {
        return dimension;
    }

    public static void start()
    {
        Log.log("Starting WindowManager");

        SceneManager.start();

        frame = new JFrame(Program.TITLE);
        frame.setUndecorated(true);

        panel = new WindowManager();
        frame.setContentPane(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(dimension);

        frame.setVisible(true);




        frame.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent keyEvent)
            {
                if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE)
                    Program.stop();
            }
        });


        WindowManager.frame = frame;



        Log.log("WindowManager started");
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D) graphics;


        // Dikke sevn "anti" alias-ing

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        SceneManager.draw(g2);
    }
}
