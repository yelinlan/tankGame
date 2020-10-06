package com.yelinlan.tank;

import com.yelinlan.game.Bullet;
import com.yelinlan.game.Explode;
import com.yelinlan.game.GameFrame;
import com.yelinlan.map.MapTitle;
import com.yelinlan.utils.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.yelinlan.utils.Constant.*;

/**
 * @项目名称: TankPro
 * @类名称: Tank
 * @类描述: 坦克类
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/3 10:15
 **/
public abstract class Tank {
    //方向
    public static final int DIR_UP = 0;
    public static final int DIR_DOWN = 1;
    public static final int DIR_LEFT = 2;
    public static final int DIR_RIGHT = 3;

    //半径
    public static final int RADIUS = 18;
    //默认速度
    public static final int DEFAULT_SPEED = 10;
    //坦克的初始生命
    public static final int DEFAULT_HP = 200;
    public int maxHP = DEFAULT_HP;

    //坦克状态
    public static final int STATE_STAND = 0;
    public static final int STATE_MOVE = 1;
    public static final int STATE_DIE = 2;
    private Color color;

    private int x;
    private int y;
    private int hp = DEFAULT_HP;
    private String name;
    private int atk;
    public static final int ATK_MAX = 100;
    public static final int ATK_MIN = 50;
    private int speed = DEFAULT_SPEED;
    private int dir;
    private int state = STATE_STAND;
    private boolean isEnemy = false;
    //炮弹 弹夹
    private List<Bullet> bullets = new ArrayList();
    //使用容器来保存当前坦克上的所有的爆炸的效果
    private List<Explode> explodes = new ArrayList();
    private Bloodbar bar = new Bloodbar();
    private int oldX = -1;
    private int oldY = -1;
    //上一次开火的时间
    private long fireTime;
    //发射炮弹间隔时间1秒
    public static final int FIRE_INTERVAL = 200;

    public Tank(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        initTank();
    }

    public Tank() {
        initTank();
    }

    private void initTank() {
        color = MyUtil.getRandomColor();
        name = MyUtil.getRandomName();
        atk = MyUtil.getRandomNumber(ATK_MIN, ATK_MAX);
    }

    public void draw(Graphics g) {
        logic();
        drawImgTank(g);
        drawBullets(g);
        drawName(g);
        bar.draw(g);
    }

    private void drawName(Graphics g) {
        g.setColor(color);
        g.setFont(SMALL_FONT);
        g.drawString(name, x - RADIUS, y - 35);
    }

    /**
     * @param g
     * @return : void
     * @方法名 : drawImgTank
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 13:06
     * @功能描述 : 使用图片的方式绘制坦克
     */
    public abstract void drawImgTank(Graphics g);

//    /**
//     * @param g
//     * @return : void
//     * @方法名 : drawTank
//     * @创建人 : 夜林蓝
//     * @创建日期 : 2020/10/3 12:39
//     * @功能描述 : 使用系统的方式画坦克
//     */
//    private void drawTank(Graphics g) {
//        g.setColor(color);
//        g.fillOval(x - RADIUS, y - RADIUS, RADIUS << 1, RADIUS << 1);
//
//        int endX = x;
//        int endY = y;
//        switch (dir) {
//            case DIR_UP:
//                endY = y - 2 * RADIUS;
//                g.drawLine(x - 1, y, endX - 1, endY);
//                g.drawLine(x + 1, y, endX + 1, endY);
//                break;
//            case DIR_DOWN:
//                endY = y + 2 * RADIUS;
//                g.drawLine(x - 1, y, endX - 1, endY);
//                g.drawLine(x + 1, y, endX + 1, endY);
//                break;
//            case DIR_LEFT:
//                endX = x - 2 * RADIUS;
//                g.drawLine(x, y - 1, endX, endY - 1);
//                g.drawLine(x, y + 1, endX, endY + 1);
//                break;
//            case DIR_RIGHT:
//                endX = x + 2 * RADIUS;
//                g.drawLine(x, y - 1, endX, endY - 1);
//                g.drawLine(x, y + 1, endX, endY + 1);
//                break;
//        }
//        g.drawLine(x, y, endX, endY);
//    }

    private void logic() {
        switch (state) {
            case STATE_STAND:
                break;
            case STATE_MOVE:
                move();
                break;
            case STATE_DIE:
                break;
        }
    }

    /**
     * @return : void
     * @方法名 : move
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 11:02
     * @功能描述 : 坦克移动
     */
    private void move() {
        oldX = x;
        oldY = y;
        switch (dir) {
            case DIR_UP:
                y -= speed;
                if (y < RADIUS + GameFrame.titleBarH) {
                    y = RADIUS + GameFrame.titleBarH;
                }
                break;
            case DIR_DOWN:
                y += speed;
                if (y > FRAME_HEIGHT - RADIUS) {
                    y = FRAME_HEIGHT - RADIUS;
                }
                break;
            case DIR_LEFT:
                x -= speed;
                if (x < RADIUS) {
                    x = RADIUS;
                }
                break;
            case DIR_RIGHT:
                x += speed;
                if (x > FRAME_WIDTH - RADIUS) {
                    x = FRAME_WIDTH - RADIUS;
                }
                break;
        }
    }

    /**
     * @param
     * @return : void
     * @方法名 : fire
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 12:28
     * @功能描述 : 发射子弹
     */
    public void fire() {
        if (System.currentTimeMillis() - fireTime < FIRE_INTERVAL) {
            return;
        }
        int bulletX = x;
        int bulletY = y;

        switch (dir) {
            case DIR_UP:
                bulletY -= RADIUS;
                break;
            case DIR_DOWN:
                bulletY += RADIUS;
                break;
            case DIR_LEFT:
                bulletX -= RADIUS;
                break;
            case DIR_RIGHT:
                bulletX += RADIUS;
                break;
        }
        Bullet bullet = BulletsPool.get();
        bullet.setX(bulletX);
        bullet.setY(bulletY);
        bullet.setDir(dir);
        bullet.setAtk(atk);
        bullet.setColor(color);
        bullet.setVisible(true);
        bullets.add(bullet);
        fireTime = System.currentTimeMillis();
    }

    /**
     * @param g
     * @return : void
     * @方法名 : drawBullets
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 12:40
     * @功能描述 : 画所有子弹
     */
    private void drawBullets(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

        //把不可见的子弹归还到池子里
        for (int i = 0; i < bullets.size(); i++) {
            if (!bullets.get(i).isVisible()) {
                BulletsPool.theReturn(bullets.remove(i));
                i--;
            }
        }
    }

    /**
     * @return : void
     * @方法名 : bulletsReturn
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/4 15:09
     * @功能描述 : 坦克销毁的时候
     */
    public void bulletsReturn() {
        for (Bullet bullet : bullets) {
            BulletsPool.theReturn(bullet);
        }
        bullets.clear();
    }

    public void collideBullets(List<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            if (MyUtil.isCollide(this.x, this.y, RADIUS, bulletX, bulletY)) {
                //子弹消失
                bullet.setVisible(false);
                //坦克受到伤害
                hurt(bullet);
                //添加爆炸效果
                addExplode(x, y + RADIUS);
                MusicUtil.playShot();
            }

        }
    }

    /**
     * @方法名 : addExplode
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/4 16:57
     * @功能描述 : 添加爆炸效果
     */
    private void addExplode(int x, int y) {
        Explode explode = ExplodesPool.get();
        explode.setX(x);
        explode.setY(y + RADIUS);
        explode.setIndex(0);
        explodes.add(explode);
    }

    public void hurt(Bullet bullet) {
        final int atk = bullet.getAtk();
        hp -= atk;
        if (hp < 0) {
            hp = 0;
            die();
        }
    }

    /**
     * @return : void
     * @方法名 : die
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/4 13:32
     * @功能描述 : 坦克死亡需要处理的内容
     */
    private void die() {
        if (isEnemy) {
            GameFrame.killEnemyCount++;
            MusicUtil.playBomb();
            //敌人坦克被消灭了归还对象池
            EmenyTanksPool.theReturn(this);
            //本关是否结束?
            if (GameFrame.isCrossLevel()) {
                //判断游戏是否通关了?
                if (GameFrame.isLastLevel()) {
                    GameFrame.setGameState(STATE_WIN);
                } else {
                    //进入下一关
                    GameFrame.startCrossLevel();
//                    GameFrame.nextLevel();
                }
            }
        } else {
            //游戏结束
            delaySecondsToOver(3000);
        }
    }

    /**
     * @return : java.lang.Boolean
     * @方法名 : isDie
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/4 13:58
     * @功能描述 : 是否死亡
     */
    public Boolean isDie() {
        return hp <= 0;
    }

    /**
     * @return : void
     * @方法名 : drawExplode
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 20:58
     * @功能描述 : 绘制当前所有的爆炸的效果
     */
    public void drawExplodes(Graphics g) {
        for (Explode explode : explodes) {
            explode.draw(g);
        }
        for (int i = 0; i < explodes.size(); i++) {
            if (!explodes.get(i).isVisible()) {
                ExplodesPool.theReturn(explodes.remove(i));
                i--;
            }
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public List getBullets() {
        return bullets;
    }

    public void setBullets(List bullets) {
        this.bullets = bullets;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Explode> getExplodes() {
        return explodes;
    }

    public void setExplodes(List<Explode> explodes) {
        this.explodes = explodes;
    }

    class Bloodbar {
        public static final int BAR_LENGTH = 50;
        public static final int BAR_HEIGHT = 6;

        public void draw(Graphics g) {
            //填充底色
            g.setColor(Color.YELLOW);
            g.fillRect(x - RADIUS, y - RADIUS - BAR_HEIGHT * 2, BAR_LENGTH, BAR_HEIGHT);
            //红色的当前血量
            g.setColor(Color.RED);
            g.fillRect(x - RADIUS, y - RADIUS - BAR_HEIGHT * 2, hp * BAR_LENGTH / maxHP, BAR_HEIGHT);
            //蓝色边框
            g.setColor(Color.BLUE);
            g.drawRect(x - RADIUS, y - RADIUS - BAR_HEIGHT * 2, BAR_LENGTH, BAR_HEIGHT);
        }
    }

    /**
     * @param titles
     * @return : void
     * @方法名 : bulletsCollideMapTitles
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/4 17:00
     * @功能描述 : 子弹和地图块的碰撞
     */
    public void bulletsCollideMapTitles(List<MapTitle> titles) {
        for (MapTitle title : titles) {
            if (title.isCollideBullet(bullets)) {
                //添加爆炸效果
                addExplode(title.getX() + MapTitle.radius, title.getY() + MapTitle.titleW / 2);
                //地图水泥块没有击毁的必理
                if (title.getType() == MapTitle.TYPE_HARD) {
                    MusicUtil.playHard();
                    continue;
                }
                MusicUtil.playShot();
                //设置地图块销毁
                title.setVisible(false);
                //归还对象池
                MapTitlePool.theReturn(title);

                //当老巢被击毁之后，一秒钟切换到游戏结束的画面
                if (title.isHouse()) {
                    MusicUtil.playBomb();
                    delaySecondsToOver(3000);
                }
            }
        }
    }


    /**
     * @param millisSecond
     * @方法名 : delaySecondsToOver
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 12:28
     * @功能描述 : 延迟若干毫秒切换到游戏结束
     */
    private void delaySecondsToOver(int millisSecond) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(millisSecond);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GameFrame.setGameState(STATE_LOST);
            }
        }.start();
    }

    /**
     * @方法名 : isCollideTitle
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 10:35
     * @功能描述 : 一个地图块和当前的坦克碰撞的方法
     * 从tile中提取8个点来判断8个点是否有任何一个点和当前的坦克有了碰撞
     * 点的顺序从左上角的点开始·顺时针遍历
     */
    public boolean isCollideTitle(List<MapTitle> titles) {
        for (MapTitle title : titles) {
            if (!title.isVisible() || title.getType() == MapTitle.TYPE_COVER) {
                continue;
            }
            int r = MapTitle.radius;
            int R = RADIUS;
            int tX = title.getX();
            int tY = title.getY();
            int[] titleXs = {tX, tX + r, tX + 2 * r, tX + 2 * r, tX + 2 * r, tX + r, tX, tX};
            int[] titleYs = {tY, tY, tY, tY + r, tY + r * 2, tY + r * 2, tY + r * 2, tY + r};
            for (int i = 0; i < titleXs.length; i++) {
                boolean collide = MyUtil.isCollide(this.x, this.y, R, titleXs[i], titleYs[i]);
                if (collide) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param
     * @return : void
     * @方法名 : back
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 10:56
     * @功能描述 : 坦克回退的方法
     */
    public void back() {
        x = oldX;
        y = oldY;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
}