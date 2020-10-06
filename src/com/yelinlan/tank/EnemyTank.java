package com.yelinlan.tank;

import com.yelinlan.game.GameFrame;
import com.yelinlan.game.LevelInfo;
import com.yelinlan.utils.EmenyTanksPool;
import com.yelinlan.utils.MyUtil;

import java.awt.*;

import static com.yelinlan.utils.Constant.FRAME_WIDTH;
import static com.yelinlan.utils.Constant.*;

/**
 * @项目名称: TankPro
 * @类名称: EnemyTank
 * @类描述:
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/3 15:00
 **/
public class EnemyTank extends Tank {
    public static final int TYPE_GREEN = 0;
    public static final int TYPE_YELLOW = 1;
    private int type;
    //记录5秒开始的时间
    private long aiTime;
    private static Image[] greenImg;
    private static Image[] yellowImg;

    static {
        greenImg = new Image[4];
        greenImg[0] = Toolkit.getDefaultToolkit().createImage("res/ul.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        greenImg[1] = Toolkit.getDefaultToolkit().createImage("res/dl.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        greenImg[2] = Toolkit.getDefaultToolkit().createImage("res/ll.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        greenImg[3] = Toolkit.getDefaultToolkit().createImage("res/rl.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        yellowImg = new Image[4];
        yellowImg[0] = Toolkit.getDefaultToolkit().createImage("res/u.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        yellowImg[1] = Toolkit.getDefaultToolkit().createImage("res/d.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        yellowImg[2] = Toolkit.getDefaultToolkit().createImage("res/l.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        yellowImg[3] = Toolkit.getDefaultToolkit().createImage("res/r.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);

    }

    public EnemyTank(int x, int y, int dir) {
        super(x, y, dir);
        //敌人创建出来就计时
        aiTime = System.currentTimeMillis();
        type = MyUtil.getRandomNumber(TYPE_GREEN, TYPE_YELLOW + 1);
    }

    public EnemyTank() {
        type = MyUtil.getRandomNumber(TYPE_GREEN, TYPE_YELLOW + 1);
        aiTime = System.currentTimeMillis();
    }


    @Override
    public void drawImgTank(Graphics g) {
        ai();
        g.drawImage(type == TYPE_GREEN ? greenImg[getDir()] : yellowImg[getDir()], getX() - RADIUS, getY() - RADIUS, null);
    }

    //用于创建敌人坦克
    public static Tank createEnemy() {
        int x = MyUtil.getRandomNumber(0, 2) == 0 ? RADIUS : FRAME_WIDTH - RADIUS;
        int y = RADIUS + GameFrame.titleBarH;
        int dir = DIR_DOWN;
        EnemyTank enemy = (EnemyTank) EmenyTanksPool.get();
        enemy.setX(x);
        enemy.setY(y);
        enemy.setDir(dir);
        enemy.setEnemy(true);
        enemy.setState(STATE_MOVE);
        //根据游戏的难度设置敌人的血量
        int maxHp = Tank.DEFAULT_HP * LevelInfo.getInstance().getLevelType();
        enemy.setHp(maxHp);
        enemy.setMaxHP(maxHp);
        //通过关卡信息中的敌人类型，来设当前出生的敌人的类型
        int enemyType = LevelInfo.getInstance().getRandomEnemyType();
        enemy.setType(enemyType);
        return enemy;
    }

    private void ai() {
        if ((System.currentTimeMillis() - aiTime) > EMENY_AI_INTERVAL) {
            setDir(MyUtil.getRandomNumber(DIR_UP, DIR_RIGHT + 1));
            setState(MyUtil.getRandomNumber(0, 2) == 0 ? STATE_STAND : STATE_MOVE);
            aiTime = System.currentTimeMillis();
        }

        if (Math.random() < EMENY_FIRE_PERCENT) {
            fire();
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}