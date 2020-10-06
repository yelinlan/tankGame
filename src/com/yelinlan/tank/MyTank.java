package com.yelinlan.tank;

import java.awt.*;

import static com.yelinlan.utils.Constant.*;

/**
 * @项目名称: TankPro
 * @类名称: MyTank
 * @类描述:
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/3 14:57
 **/
public class MyTank extends Tank {
    private static Image[] tankImg;

    static {
        tankImg = new Image[4];
        tankImg[0] = Toolkit.getDefaultToolkit().createImage("res/u.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        tankImg[1] = Toolkit.getDefaultToolkit().createImage("res/d.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        tankImg[2] = Toolkit.getDefaultToolkit().createImage("res/l.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
        tankImg[3] = Toolkit.getDefaultToolkit().createImage("res/r.png").getScaledInstance(RESIZE_W, RESIZE_H, 1);
    }

    public MyTank(int x, int y, int dir) {
        super(x, y, dir);
    }

    @Override
    public void drawImgTank(Graphics g) {
        g.drawImage(tankImg[getDir()], getX() - RADIUS, getY() - RADIUS, null);
    }
}