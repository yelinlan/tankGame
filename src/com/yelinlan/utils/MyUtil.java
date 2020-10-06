package com.yelinlan.utils;

import java.awt.*;

/**
 * @项目名称: TankPro
 * @类名称: MyUtil
 * @类描述: 工具类
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/3 10:29
 **/
public class MyUtil {

    /**
     * @param min
     * @param max
     * @return : int
     * @方法名 : getRandomNumber
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 10:30
     * @功能描述 :得到指定的区间的随机数
     */
    public static final int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static final Color getRandomColor() {
        int red = getRandomNumber(0, 256);
        int green = getRandomNumber(0, 256);
        int blue = getRandomNumber(0, 256);
        return new Color(red, green, blue);
    }

    /**
     * @return : boolean
     * @方法名 : isCollide
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 20:24
     * @功能描述 : 矩形碰撞检测
     */
    public static final boolean isCollide(int rectX, int rectY, int radius, int pointX, int pointY) {
        int disX = Math.abs(rectX - pointX);
        int disY = Math.abs(rectY - pointY);
        if (disX < radius && disY < radius) {
            return true;
        }
        return false;
    }

    private static final String[] NAMES = {
            "房屋", "心愿", "左边", "新闻", "早点", "青菜", "土豆", "总结", "礼貌",
            "右边", "黄羊", "鬣羚", "斑羚", "岩羊", "盘羊", "霎兔", "行人", "乐园",
            "花草", "人才", "左手", "目的", "课文", "优点", "年代", "灰尘", "沙子",
            "小说", "儿女", "难题", "明星", "本子", "彩色", "水珠", "路灯", "把握",
            "市场", "雨点", "细雨", "书房", "毛巾", "画家", "元旦", "绿豆", "本领",
            "起点", "老虎", "老鼠", "猴子", "树懒", "斑马", "小狗", "狐狸", "狗熊",
            "黑熊", "大象", "豹子", "麝牛", "狮子", "熊猫", "疣猪", "羚羊", "驯鹿",
            "考拉", "犀牛", "猗剂", "猩猩", "海牛", "水獭", "海豚", "海象", "刺猬",
            "袋鼠", "狁猱", "河马", "海豹", "海狮", "蝙蝠", "白虎", "狸猫", "水牛",
            "山羊", "绵羊", "牦牛", "猿猴", "松鼠", "野猪", "豪猪", "麋鹿", "花豹",
            "野狼", "灰狼", "蜂猴", "熊猴", "叶猴", "紫貂", "貂熊", "熊狸", "云豹",
            "雾豹", "黑鹿", "野马", "饟鹿", "坡鹿", "豚鹿", "野牛", "藏羚", "河狸",
            "驼鹿"
    };

    private static final String[] MODIFIY = {
            "纯真", "稚气", "温润", "好奇", "可爱", "傻傻", "萌萌", "羞羞", "笨笨",
            "呆呆", "美丽", "聪明", "伶俐", "狡猾", "胖乎乎", "粉嫩嫩", "白胖胖", "漂亮",
            "可爱", "聪明", "懂事", "乖巧", "淘气", "淘气", "顽劣", "调皮", "顽皮",
            "天真", "可爱", "无邪", "单纯", "纯洁", "无暇"
    };

    public static final String getRandomName() {
        return MODIFIY[getRandomNumber(0, MODIFIY.length)] + "的" + NAMES[getRandomNumber(0, NAMES.length)];
    }
}