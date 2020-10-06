package com.yelinlan.utils;

import com.yelinlan.game.Bullet;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称: TankPro
 * @类名称: BulletsPool
 * @类描述: 子弹对象池类
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/3 13:40
 **/
public class BulletsPool {
    public static final int DEFAULT_POOL_SIZE = 200;
    public static final int DEFAULT_POOL_MAX_SIZE = 300;
    //用于保存所有的子弹的容器
    private static List<Bullet> pool = new ArrayList<Bullet>();

    //在类加载的时候创建200个子弹对象添加到容器中
    static {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            pool.add(new Bullet());
        }
    }

    /**
     * @return : com.yelinlan.game.Bullet
     * @方法名 : get
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 13:46
     * @功能描述 :从池塘中获取一个子弹对象·
     */
    public static Bullet get() {
        Bullet bullet = null;
        if (pool.size() == 0) {
            bullet = new Bullet();
        } else {
            bullet = pool.remove(0);
        }
        return bullet;
    }

    public static void theReturn(Bullet bullet) {
        if (pool.size() == DEFAULT_POOL_MAX_SIZE) {
            return;
        }
        pool.add(bullet);
    }
}