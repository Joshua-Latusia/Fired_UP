package com.avansA5.noot;

import com.avansA5.noot.managers.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Program
{
    public static final String TITLE  = "Fired up 1.33.7 pre beta release of the bleeding edge alpha";

    public static void start()
    {
        ScoreManager.start();
        ControlManager.start();
        WindowManager.start();
        GameManager.start();
        AnimationManager.start();


    }

    public static void stop()
    {
        ScoreManager.stop();
        System.exit(0);
    }

    public static void main(String[] args)
    {
        //System.setProperty("sun.java2d.opengl", "True");
        System.out.print(new String(Base64.getDecoder().decode("PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09DQp8ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHwNCnwgICAgICAgICAgICAgICAgICAgICAgICAgICAgIEZpcmVkIFVwICAgICAgICAgICAgICAgICAgICAgfA0KfCAgICAgICAgICAgICAgICAgICAgICAgIEhvbHkgc2hpdCB3ZWxjb21lISAgICAgICAgICAgICAgICB8DQp8ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHwNCnwgICAgICAgICAgICAgICAgICAgICAgICBNaWNoYWVsIHZhbiBkZXIgTmV0ICAgICAgICAgICAgICAgfA0KfCAgICAgICAgICAgICAgICAgICAgICAgIENhcyBkZSBHcm9vdCAgICAgICAgICAgICAgICAgICAgICB8DQp8ICAgICAgICAgICAgICAgICAgICAgICAgQ2FzIEtvb3BtYW5zICAgICAgICAgICAgICAgICAgICAgIHwNCnwgICAgICAgICAgICAgICAgICAgICAgICBDZXphbiBWb24gTWVpamVuZmVsZHQgICAgICAgICAgICAgfA0KfCAgICAgICAgICAgICAgICAgICAgICAgIEpvc2h1YSBMYXR1c2lhICAgICAgICAgICAgICAgICAgICB8DQp8ICAgICAgICAgICAgICAgICAgICAgICAgVGltb24gQm9yZyAgICAgICAgICAgICAgICAgICAgICAgIHwNCj09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PQ0K"), StandardCharsets.UTF_8));

        Program.start();
    }
}
