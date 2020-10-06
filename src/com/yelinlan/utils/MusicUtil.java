package com.yelinlan.utils;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

/**
 * @项目名称: TankPro
 * @类名称: MusicUtil
 * @类描述:
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/6 9:31
 **/
public class MusicUtil {
    private static AudioClip start;
    private static AudioClip bomb;
    private static AudioClip shot;
    private static AudioClip hard;
    //装在音乐资源
    static {
        try {
            //引号里面的是音乐文件所在的绝对路径
            start = Applet.newAudioClip(new File("music/start.wav").toURL());
            bomb = Applet.newAudioClip(new File("music/bomb.wav").toURL());
            shot = Applet.newAudioClip(new File("music/shot.wav").toURL());
            hard = Applet.newAudioClip(new File("music/hard.wav").toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playStart() {
        start.play();
    }

    public static void playBomb() {
        bomb.play();
    }

    public static void playShot() {
        shot.play();
    }
    public static void playHard() {
        hard.play();
    }
}