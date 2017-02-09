package com.avansA5.noot.managers;

import com.avansA5.noot.Program;
import com.avansA5.noot.util.Log;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ControlManager implements WiimoteListener
{
    public static ArrayList<Wiimote> wiimotes;

    public static void start()
    {
        Log.log("Starting ControlManager");

        //TODO: single/multi
        Wiimote[] _wiimotes = WiiUseApiManager.getWiimotes(2, true);

        if (_wiimotes == null || _wiimotes.length == 0)
        {
            Log.error("No WiiMotes Connected");
            Program.stop();
        }

        wiimotes = new ArrayList<>(Arrays.asList(_wiimotes));

        for (int i = 0; i < wiimotes.size(); i++)
        {
            Log.log("Initializing wiimote " + i);
            Wiimote wiimote = wiimotes.get(i);
            if (i == 0)
                wiimote.setLeds(true, false, false, false);
            if (i == 1)
                wiimote.setLeds(false, true, false, false);

            wiimote.activateIRTRacking();
            wiimote.setSensorBarBelowScreen();
            wiimote.setIrSensitivity(0);
            wiimote.setIrSensitivity(3);

            addWiimoteListener(new ControlManager(), 0);

            Log.log("ControlManager started");
        }
    }

    public static void addWiimoteListener(WiimoteListener wiimoteListener, int i)
    {
        Log.log("Adding wiimoteListener for wiimote " + i);

        if(wiimotes.size() > i) {
            Wiimote wiimote = wiimotes.get(i);

            wiimote.addWiiMoteEventListeners(wiimoteListener);
        }
    }

    public static void clearWiiMoteListener(int i)
    {
        if(wiimotes.size() > i) {
            Wiimote wiimote = wiimotes.get(i);
            if (wiimote.getWiiMoteEventListeners().length != 0) {
                for (WiimoteListener wiimoteListener1 : wiimote.getWiiMoteEventListeners()) {
                    wiimote.removeWiiMoteEventListeners(wiimoteListener1);
                }
            }
        }
    }

    public static void removeWiimoteListener(WiimoteListener wiimoteListener, int i)
    {
        Log.log("Removing wiimoteListener for wiimote" + i);
        Wiimote wiimote = wiimotes.get(i);

        wiimote.removeWiiMoteEventListeners(wiimoteListener);
    }


    //region WiimoteShit

    @Override
    public void onButtonsEvent(WiimoteButtonsEvent wiimoteButtonsEvent)
    {
        if(wiimoteButtonsEvent.isButtonHomeJustReleased())
        {
            Program.stop();
        }
    }

    @Override
    public void onIrEvent(IREvent irEvent)
    {

    }

    @Override
    public void onMotionSensingEvent(MotionSensingEvent motionSensingEvent)
    {

    }

    @Override
    public void onExpansionEvent(ExpansionEvent expansionEvent)
    {

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

    //endregion
}