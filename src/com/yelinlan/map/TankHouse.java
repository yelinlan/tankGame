package com.yelinlan.map;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.yelinlan.utils.Constant.*;

/**
 * @项目名称: TankPro
 * @类名称: TankHouse
 * @类描述: 玩家的大本营
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/5 11:36
 **/
public class TankHouse {
    public static final int HOUSE_X = (FRAME_WIDTH - 3 * MapTitle.titleW >> 1) + 2;
    public static final int HOUSE_Y = FRAME_HEIGHT - 2 * MapTitle.titleW;

    //一共六块地图块
    private List<MapTitle> titles = new ArrayList<>();

    public TankHouse() {
        int[] hX = {HOUSE_X, HOUSE_X + MapTitle.titleW, HOUSE_X + MapTitle.titleW * 2, HOUSE_X + MapTitle.titleW * 2, HOUSE_X + MapTitle.titleW, HOUSE_X};
        int[] hY = {HOUSE_Y, HOUSE_Y, HOUSE_Y, HOUSE_Y + MapTitle.titleW, HOUSE_Y + MapTitle.titleW, HOUSE_Y + MapTitle.titleW};
        for (int i = 0; i < hX.length; i++) {
            titles.add(new MapTitle(hX[i], hY[i]));
        }
        //设置基地类型
        titles.get(titles.size() - 2).setType(MapTitle.TYPE_HOUSE);
    }

    public List<MapTitle> getTitles() {
        return titles;
    }

    public void setTitles(List<MapTitle> titles) {
        this.titles = titles;
    }
}