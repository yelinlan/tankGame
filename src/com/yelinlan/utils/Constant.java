package com.yelinlan.utils;

import java.awt.*;

/**
 * @项目名称: TankPro
 * @类名称: Constant
 * @类描述: 游戏中的常量都在该类中维护﹐方便后期的管理
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/1 18:48
 **/
public class Constant {
    /******************游戏窗口相关****************/
    public static final String GAME_TITLE = "坦克大战";

    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    public static final int SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final int FRAME_X = SCREEN_W - FRAME_WIDTH >> 1;
    public static final int FRAME_Y = SCREEN_H - FRAME_HEIGHT >> 1;

    /*****************游戏菜单相关****************/
    public static final int STATE_MENU = 0;
    public static final int STATE_HELP = 1;
    public static final int STATE_ABOUT = 2;
    public static final int STATE_RUN = 3;
    public static final int STATE_LOST = 4;
    public static final int STATE_WIN = 5;
    public static final int STATE_CROSS = 6;

    public static final String[] MENUS = {
            "开始游戏",
            "继续游戏",
            "游戏帮助",
            "游戏关于",
            "退出游戏"
    };

    public static final Font GAME_FONT = new Font("宋体", Font.BOLD, 24);
    public static final Font SMALL_FONT = new Font("宋体", Font.BOLD, 12);
    public static final int REPAINT_INTERVAL = 100;

    //最大敌人数量
    public static final int EMENY_MAX_COUNT = 10;
    public static final int EMENY_BORN_INTERVAL = 5000;

    public static final int EMENY_AI_INTERVAL = 3000;
    public static final double EMENY_FIRE_PERCENT = 0.1;
    //resize 图片
    public static final int RESIZE_W = 40;
    public static final int RESIZE_H = 40;

    public static final String OVER_STR0 = "ESC键退出游戏";
    public static final String OVER_STR1 = "ENTER键回主莱单";
}