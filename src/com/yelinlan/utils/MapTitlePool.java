package com.yelinlan.utils;


import com.yelinlan.map.MapTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称: TankPro
 * @类名称: MapTitlePool
 * @类描述: 砖块对象池
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/4 15:30
 **/
public class MapTitlePool {
    public static final int DEFAULT_POOL_SIZE = 50;
    public static final int DEFAULT_POOL_MAX_SIZE = 70;
    //用于保存所有的子弹的容器
    private static List<MapTitle> pool = new ArrayList<MapTitle>();

    //在类加载的时候创建200个子弹对象添加到容器中
    static {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            pool.add(new MapTitle());
        }
    }

    /**
     * @return : com.yelinlan.game.MapTitle
     * @方法名 : get
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/3 13:46
     * @功能描述 :从池塘中获取一个子弹对象·
     */
    public static MapTitle get() {
        MapTitle MapTitle = null;
        if (pool.size() == 0) {
            MapTitle = new MapTitle();
        } else {
            MapTitle = pool.remove(0);
        }
        return MapTitle;
    }

    public static void theReturn(MapTitle MapTitle) {
        if (pool.size() == DEFAULT_POOL_MAX_SIZE) {
            return;
        }
        pool.add(MapTitle);
    }
}