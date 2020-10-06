package com.yelinlan.utils;

import com.yelinlan.game.Bullet;
import com.yelinlan.game.Explode;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称: TankPro
 * @类名称: ExplodesPool
 * @类描述: 子弹对象池类
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/3 13:40
 **/
public class ExplodesPool {
    public static final int DEFAULT_POOL_SIZE = 10;
    public static final int DEFAULT_POOL_MAX_SIZE = 20;
    //用于保存所有的爆炸效果的容器
    private static List<Explode> pool = new ArrayList<>();

    //在类加载的时候创建200个子弹对象添加到容器中
    static {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            pool.add(new Explode());
        }
    }

    /**
     * @return : Explode
     * @方法名 : get
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 13:46
     * @功能描述 :从池塘中获取一个子弹对象·
     */
    public static Explode get() {
        Explode explode = null;
        if (pool.size() == 0) {
            explode = new Explode();
        } else {
            explode = pool.remove(0);
        }
        System.out.println("剩余爆炸效果：" + pool.size());
        return explode;
    }

    public static void theReturn(Explode explode) {
        if (pool.size() == DEFAULT_POOL_MAX_SIZE) {
            return;
        }
        pool.add(explode);
        System.out.println("归还爆炸效果：" + pool.size());
    }
}