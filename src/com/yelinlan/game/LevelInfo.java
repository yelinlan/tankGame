package com.yelinlan.game;

import com.yelinlan.utils.MyUtil;

/**
 * @项目名称: TankPro
 * @类名称: LevelInfo
 * @类描述: 用来管理当前关卡的信息的∶单例类
 * 单例设计模式∶如果一个类只需要该类且有唯一的实例，那么可以使用
 * 单例设计模式来设计该类。
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/6 8:51
 **/
public class LevelInfo {
    public LevelInfo() {
    }

    public static final LevelInfo levelInfo = new LevelInfo();

    public static LevelInfo getInstance() {
        return levelInfo;
    }

    //关卡编号
    private int level;
    //关卡的敌人的数量
    private int enemyCount;
    //通关的要求的时长，-1意味着不限时
    private int crossTime = -1;
    //敌人类型信息
    private int[] enemyType;

    //游戏难度
    private int levelType;


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getCrossTime() {
        return crossTime;
    }

    public void setCrossTime(int crossTime) {
        this.crossTime = crossTime;
    }

    public int[] getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(int[] enemyType) {
        this.enemyType = enemyType;
    }

    //获得敌人类型数组中的随机的一个元素。
    //获得一个随机的敌人的类型
    public int getRandomEnemyType() {
        int index = MyUtil.getRandomNumber(0, enemyType.length);
        return enemyType[index];
    }

    public int getLevelType() {
        return levelType < 1 ? 1 : levelType;
    }

    public void setLevelType(int levelType) {
        this.levelType = levelType;
    }
}