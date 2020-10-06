package com.yelinlan.map;

import com.yelinlan.game.Bullet;
import com.yelinlan.utils.BulletsPool;
import com.yelinlan.utils.MyUtil;

import static com.yelinlan.utils.Constant.*;

import java.awt.*;
import java.util.List;

/**
 * @项目名称: TankPro
 * @类名称: MapTitle
 * @类描述: 地图元素快
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/4 15:22
 **/
public class MapTitle {
    private static Image[] titleImg;
    public static int titleW = 40;
    public static int radius = titleW >> 1;
    private boolean visible = true;
    private String name;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_HOUSE = 1;
    public static final int TYPE_COVER = 2;
    public static final int TYPE_HARD = 3;
    private int type = TYPE_NORMAL;

    static {
        titleImg = new Image[4];

        titleImg[TYPE_NORMAL] = Toolkit.getDefaultToolkit().createImage("res/title.png").getScaledInstance(40, 40, 1);
        titleImg[TYPE_HOUSE] = Toolkit.getDefaultToolkit().createImage("res/house.png").getScaledInstance(40, 40, 1);
        titleImg[TYPE_COVER] = Toolkit.getDefaultToolkit().createImage("res/cover.png").getScaledInstance(40, 40, 1);
        titleImg[TYPE_HARD] = Toolkit.getDefaultToolkit().createImage("res/hard.png").getScaledInstance(40, 40, 1);

        if (titleW <= 0) {
            titleW = titleImg[TYPE_NORMAL].getWidth(null);
        }
    }

    private int x;
    private int y;

    public MapTitle(int x, int y) {
        this.x = x;
        this.y = y;
        if (titleW <= 0) {
            titleW = titleImg[TYPE_NORMAL].getWidth(null);
        }
    }

    public MapTitle() {
    }

    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        if (titleW <= 0) {
            titleW = titleImg[TYPE_NORMAL].getWidth(null);
        }
        //绘制快上的名字
        g.drawImage(titleImg[type], x, y, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return : boolean
     * @方法名 : isCollideBullet
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/4 16:37
     * @功能描述 : 地图块和子弹是否有碰撞
     */
    public boolean isCollideBullet(List<Bullet> bullets) {
        if (!visible || type == TYPE_COVER) {
            return false;
        }
        for (Bullet bullet : bullets) {
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            boolean collide = MyUtil.isCollide(x + radius, y + radius, radius, bulletX, bulletY);
            if (collide) {
                //子弹的销毁
                bullet.setVisible(false);
                BulletsPool.theReturn(bullet);
                return true;
            }
        }
        return false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @方法名 : isHouse
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 12:17
     * @功能描述 : 判断当前的地图块是否是老巢
     */
    public boolean isHouse() {
        return type == TYPE_HOUSE;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}