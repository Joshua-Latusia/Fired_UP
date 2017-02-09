package com.avansA5.noot.objects.pickups;

import com.avansA5.noot.interfaces.Colliding;

import com.avansA5.noot.objects.GameObject;
import com.avansA5.noot.objects.PlayerPanel;
import com.avansA5.noot.scenes.GameScene;

/**
 * Created by TripleCTMJ on 09/06/2016.
 * Refactored by Joshua on 17/06/2016.
 */
abstract class Pickup extends GameObject implements Colliding
{
    final PlayerPanel panel= GameScene.getPanels()[1];
    final int SCALED_PANEL_SIZE = (int)(panel.getPanelWidth()*panel.getScale());
    final double SCREEN_WIDTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
}
