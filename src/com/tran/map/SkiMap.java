package com.tran.map;

import com.tran.area.Area;
import com.tran.area.AreaKey;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class SkiMap {
    private static SkiMap instance;
    private ConcurrentHashMap<AreaKey, Area> map = new ConcurrentHashMap<AreaKey, Area>();

    protected SkiMap() {
    }

    public static SkiMap getInstance() {
        if (instance == null) {
            instance = new SkiMap();
        }
        return instance;
    }

    public void populateMap(Data dataMap) {
        List<AreaKey> dataKeys = dataMap.getKeys();
        dataKeys.stream()
                .forEach(key -> {
                    if (!map.containsKey(key)) {
                        map.putIfAbsent(key, new Area(key, dataMap));
                    }
                });
    }

    public Area getStartAreaOfBestPath() {
        Comparator<Map.Entry<AreaKey, Area>> compareByPathAndDrop =
                (Map.Entry<AreaKey, Area> entry1, Map.Entry<AreaKey, Area> entry2) -> {
                    Area area1 = entry1.getValue();
                    Area area2 = entry2.getValue();
                    if (area1.getTotalPath() == area2.getTotalPath()) {
                        return area1.getTotalDrop() - area2.getTotalDrop();
                    } else {
                        return area1.getTotalPath() - area2.getTotalPath();
                    }
                };
        return map.entrySet()
                .stream()
                .max(compareByPathAndDrop)
                .get()
                .getValue();
    }

    public Area put (Area area) {
        if (!map.containsKey(area.getKey())) {
            map.put(area.getKey(), area);
        }
        return area;
    }

    public void printPath(Area area) {
        System.out.println("Start " + area.toString());
        Area current = area.getNextArea();
        while (current != null) {
            System.out.println(current.toString());
            current = current.getNextArea();
        }
    }

    public boolean containsKey(AreaKey key) {
        return map.containsKey(key);
    }

    public Area getArea(AreaKey key) {
        return map.get(key);
    }

    public void cleanMap() {
        // To be called only during unit test
        map = new ConcurrentHashMap<AreaKey, Area>();
    }
}
