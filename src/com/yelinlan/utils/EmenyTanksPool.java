package com.yelinlan.utils;

import com.yelinlan.tank.EnemyTank;
import com.yelinlan.tank.Tank;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称: TankPro
 * @类名称: EmenyTanksPool
 * @类描述: 坦克对象池
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/4 13:35
 **/
public class EmenyTanksPool {
    public static final int DEFAULT_POOL_SIZE = 20;
    public static final int DEFAULT_POOL_MAX_SIZE = 20;
    //用于保存所有的子弹的容器
    private static List<Tank> pool = new ArrayList<Tank>();

    //在类加载的时候创建200个子弹对象添加到容器中
    static {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            pool.add(new EnemyTank());
        }
    }

    /**
     * @return : com.yelinlan.game.Tank
     * @方法名 : get
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 13:46
     * @功能描述 :从池塘中获取一个子弹对象·
     */
    public static Tank get() {
        Tank Tank = null;
        if (pool.size() == 0) {
            Tank = new EnemyTank();
        } else {
            Tank = pool.remove(0);
        }
        return Tank;
    }

    public static void theReturn(Tank Tank) {
        if (pool.size() == DEFAULT_POOL_MAX_SIZE) {
            return;
        }
        pool.add(Tank);
    }
}