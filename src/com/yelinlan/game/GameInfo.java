package com.yelinlan.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @项目名称: TankPro
 * @类名称: GameInfo
 * @类描述: 游戏相关的信息的类
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/6 10:54
 **/
public class GameInfo {
    //从配置文件中读取
    //关卡数量
    public static int levelCount;

    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("level/gameinfo"));
            levelCount = Integer.parseInt(prop.getProperty("levelCount"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getLevelCount() {
        return levelCount;
    }

    public static void setLevelCount(int levelCount) {
        GameInfo.levelCount = levelCount;
    }
}