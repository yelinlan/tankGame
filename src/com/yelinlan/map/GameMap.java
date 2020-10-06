package com.yelinlan.map;

import com.yelinlan.game.GameFrame;
import com.yelinlan.game.LevelInfo;
import com.yelinlan.tank.Tank;
import com.yelinlan.utils.MapTitlePool;
import com.yelinlan.utils.MyUtil;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.yelinlan.utils.Constant.*;

/**
 * @项目名称: TankPro
 * @类名称: Map
 * @类描述: 游戏地图类
 * @创建人: 夜林蓝
 * @创建时间: 2020/10/4 15:27
 **/
public class GameMap {
    private static final int MAP_X = Tank.RADIUS * 3;
    private static final int MAP_Y = Tank.RADIUS * 3 + GameFrame.titleBarH;
    private static final int MAP_WIDTH = FRAME_WIDTH - Tank.RADIUS * 6;
    private static final int MAP_HEIGHT = FRAME_HEIGHT - Tank.RADIUS * 8 - GameFrame.titleBarH;

    //地图元素块的容器
    private List<com.yelinlan.map.MapTitle> titles = new ArrayList<MapTitle>();

    public GameMap() {
    }

    private com.yelinlan.map.TankHouse house;

    /**
     * @方法名 : initMap
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/4 15:41
     * @功能描述 : 初始化地图元素块  初始化地图元素块,level :第几沁
     */
    public void initMap(int level) {
        titles.clear();
        loadLevel(level);
        //初始化大本营
        house = new TankHouse();
        addHouse();
    }

    /**
     * @方法名 : addHouse
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 12:06
     * @功能描述 : 将基地的所有的元粲块添加到地图的容器中
     */
    private void addHouse() {
        titles.addAll(house.getTitles());
    }


    /**
     * @param titles
     * @return : boolean
     * @方法名 : isCollide
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/4 16:12
     * @功能描述 :某一个点确定的是否和tiles 集合中的所有的块有重叠的部分
     */
    private boolean isCollide(List<MapTitle> titles, int x, int y) {
        for (MapTitle title : titles) {
            int titleX = title.getX();
            int titleY = title.getY();
            if (Math.abs(titleX - x) < MapTitle.titleW && Math.abs(titleY - y) < MapTitle.titleW) {
                return true;
            }
        }
        return false;
    }

    /**
     * @方法名 : drawBk
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 13:44
     * @功能描述 : 只对没有遮挡效果的块进行绘制
     */
    public void drawBk(Graphics g) {
        for (MapTitle title : titles) {
            if (title.getType() != MapTitle.TYPE_COVER) {
                title.draw(g);
            }
        }
    }

    /**
     * @方法名 : draw
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 13:45
     * @功能描述 : 只绘制有遮挡效果的块
     */
    public void drawCover(Graphics g) {
        for (MapTitle title : titles) {
            if (title.getType() == MapTitle.TYPE_COVER) {
                title.draw(g);
            }
        }
    }

    /**
     * @方法名 : clearDestroyTitle
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 10:30
     * @功能描述 : 将所有的不可见的地图块从容器中移除
     */
    public void clearDestroyTitle() {
        for (int i = 0; i < titles.size(); i++) {
            if (!titles.get(i).isVisible()) {
                titles.remove(i);
                i--;
            }
        }
    }

    public List<MapTitle> getTitles() {
        return titles;
    }

    public void setTitles(List<MapTitle> titles) {
        this.titles = titles;
    }

    /**
     * @param type   地图块的类型
     * @param DIS    地图块之间的中心点的间隔如果是块的宽度意味着是连续的,
     *               如果大于块的宽度就是不连续的
     * @param startX 添加地图块的起始的x坐标
     * @param startY 添加地图块的起始的Y坐标
     * @param endX   添加地图块的结束的x坐标
     * @方法名 : addRow
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 13:19
     * @功能描述 : 往地图块容器中添加一行指定类型的地图块
     */
    private void addRow(int startX, int startY, int endX, int type, final int DIS) {
        int count = 0;
        //如果是连续的，计算一行有多个地图块
        count = (endX - startX) / (MapTitle.titleW + DIS);
        for (int i = 0; i < count; i++) {
            MapTitle title = MapTitlePool.get();
            title.setType(type);
            title.setX(startX + i * (MapTitle.titleW + DIS));
            title.setY(startY);
            titles.add(title);
        }
    }

    /**
     * @param type   地图块的类型
     * @param DIS    地图块之间的中心点的间隔如果是块的宽度意味着是连续的,
     *               如果大于块的宽度就是不连续的
     * @param startX 添加地图块的起始的x坐标
     * @param startY 添加地图块的起始的Y坐标
     * @param endY   添加地图块的结束的Y坐标
     * @方法名 : addCol
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 13:19
     * @功能描述 : 往地图块容器中添加一列指定类型的地图块
     */
    private void addCol(int startX, int startY, int endY, int type, final int DIS) {
        int count = 0;
        //如果是连续的，计算一列有多个地图块
        count = (endY - startY) / (MapTitle.titleW + DIS);
        for (int i = 0; i < count; i++) {
            MapTitle title = MapTitlePool.get();
            title.setType(type);
            title.setX(startX);
            title.setY(startY + i * (MapTitle.titleW + DIS));
            titles.add(title);
        }
    }

    /**
     * @param type   地图块的类型
     * @param DIS    地图块之间的中心点的间隔如果是块的宽度意味着是连续的,
     *               如果大于块的宽度就是不连续的
     * @param startX 添加地图块的起始的x坐标
     * @param startY 添加地图块的起始的Y坐标
     * @param endY   添加地图块的结束的Y坐标
     * @param endX   添加地图块的结束的Y坐标
     * @方法名 : addRect
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 13:19
     * @功能描述 : 对指定的矩形区域添加元素块
     */
    private void addRect(int startX, int startY, int endX, int endY, int type, final int DIS) {
        int rows = (endY - startY) / (MapTitle.titleW + DIS);
        for (int i = 0; i < rows; i++) {
            addRow(startX, startY + i * (MapTitle.titleW + DIS), endX, type, DIS);
        }
    }

    /**
     * @方法名 : loadLevel
     * @创建人 : 夜林蓝
     * @创建日期 : 2020/10/5 14:27
     * @功能描述 : 加载关卡信息
     */
    private void loadLevel(int level) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("level/lv_" + level));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将所有的地图信息都加载进来
        int enemyCount = Integer.parseInt(prop.getProperty("enemyCount"));
        //0,1 对敌人类型解析
        String[] enemyType = prop.getProperty("enemyType").split(",");
        int[] type = new int[enemyType.length];
        for (int i = 0; i < enemyType.length; i++) {
            type[i] = Integer.parseInt(enemyType[i]);
        }

        String method = prop.getProperty("method");
        int invokeCount = Integer.parseInt(prop.getProperty("invokeCount"));
        //把实参都读取到数组中来。
        String[] params = new String[invokeCount];
        for (int i = 1; i <= invokeCount; i++) {
            params[i - 1] = prop.getProperty("param" + i);
        }

        LevelInfo levelInfo = LevelInfo.getInstance();
        levelInfo.setLevel(level);//等级
        levelInfo.setEnemyCount(enemyCount);//敌人数量
        levelInfo.setEnemyType(type);//敌人类型
        levelInfo.setLevelType(Integer.parseInt(prop.getProperty("levelType")));
        //关卡难度
        //使用读取到的参数，调用对应的方法。
        invokeMethod(method, params);
    }


    private void invokeMethod(String method, String[] params) {
        for (String param : params) {
            String[] split = param.split(",");
            int[] arr = new int[split.length];
            int i;
            for (i = 0; i < split.length - 1; i++) {
                arr[i] = Integer.parseInt(split[i]);
            }
            final int DIS = MapTitle.titleW;
            int dis = (int) (Double.parseDouble(split[i]) * DIS);
            switch (method) {
                case "addRow":
                    addRow(MAP_X + arr[0] * DIS, MAP_Y + arr[1] * DIS, MAP_X + MAP_WIDTH - arr[2], arr[3], dis);
                    break;
                case "addCol":
                    addCol(MAP_X + arr[0] * DIS, MAP_Y + arr[1] * DIS,
                            MAP_Y + MAP_HEIGHT - arr[2] * DIS, arr[3], dis);
                    break;
                case "addRect":
                    addRect(MAP_X + arr[0] * DIS, MAP_Y + arr[1] * DIS, MAP_X + MAP_WIDTH - arr[2] * DIS
                            , MAP_Y + MAP_HEIGHT - arr[3] * DIS, arr[4], dis);
                    break;
            }
        }
    }
}