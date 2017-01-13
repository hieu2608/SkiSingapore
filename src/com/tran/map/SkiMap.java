package com.tran.map;

import com.tran.area.Area;
import com.tran.area.AreaKey;

import java.util.HashMap;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class SkiMap {
    private static SkiMap instance;
    private HashMap<AreaKey, Area> map = new HashMap<AreaKey, Area>();

    protected SkiMap() {

    }

    public static SkiMap getInstance() {
        if (instance == null) {
            instance = new SkiMap();
        }
        return instance;
    }

    public Area put (Area area) {
        if (map.containsKey(area.getKey())) {
            return map.get(area.getKey());
        }
        return map.put(area.getKey(), area);
    }

    public boolean containsKey(AreaKey key) {
        return map.containsKey(key);
    }

    public Area getArea(AreaKey key) {
        return map.get(key);
    }
}
