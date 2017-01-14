package com.tran.area;

import com.tran.map.Data;
import com.tran.map.SkiMap;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class Area {
    final private AreaKey key;
    final private int posX;
    final private int posY;
    final private int height;
    final private int totalPath;
    final private int totalDrop;
    final private Area nextArea;
    final private Data mapData;
    final private List<AreaKey> downhillNeighbor;

    public Area(AreaKey key, Data mapData) {
        this(key.getPosX(), key.getPosY(), mapData);
    }

    public Area(int posX, int posY, Data mapData) {
        this.key = new AreaKey(posX, posY);
        this.posX = posX;
        this.posY = posY;
        this.mapData = mapData;
        this.height = this.mapData.getHeight(posX, posY);
        this.downhillNeighbor = computeDownhilllNeighbor();
        if (this.downhillNeighbor.isEmpty()) {
            nextArea = null;
            totalPath = 1;
            totalDrop = 0;
        } else {
            nextArea = computeBestNeighbor();
            totalPath = nextArea.totalPath + 1;
            totalDrop = height - nextArea.getHeight() + nextArea.getTotalDrop();
        }
    }

    private List<AreaKey> computeDownhilllNeighbor() {
        HashMap<AreaKey, Integer> neighbor = new HashMap<>();
        int maxX = mapData.getMaxColumns() - 1;
        int maxY = mapData.getMaxRows() - 1;

        if (posX - 1 >= 0) {
            AreaKey westAreaKey = new AreaKey(posX - 1, posY);
            neighbor.put(westAreaKey, mapData.getHeight(westAreaKey));
        }
        if (this.posY - 1 >= 0) {
            AreaKey northAreaKey = new AreaKey(posX, posY - 1);
            neighbor.put(northAreaKey, mapData.getHeight(northAreaKey));
        }
        if (this.posX + 1 <= maxX) {
            AreaKey eastAreaKey = new AreaKey(posX + 1, posY);
            neighbor.put(eastAreaKey, mapData.getHeight(eastAreaKey));
        }
        if (this.posY + 1 <= maxY) {
            AreaKey southAreaKey = new AreaKey(posX, posY + 1);
            neighbor.put(southAreaKey, mapData.getHeight(southAreaKey));
        }
        HashMap<AreaKey, Integer> downhillNeighbor =  (HashMap)neighbor.entrySet().stream()
                                                        .filter(e -> e.getValue() < this.height)
                                                        .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        return new ArrayList<AreaKey>(downhillNeighbor.keySet());
    }

    private Area computeBestNeighbor() {
        SkiMap skiMap = SkiMap.getInstance();
        List<Area> neighborArea = this.downhillNeighbor.parallelStream()
                                        .map(key -> {
                                            if (skiMap.containsKey(key)) {
                                                return skiMap.getArea(key);
                                            } else {
                                                Area area = new Area(key, this.mapData);
                                                return skiMap.put(area);
                                            }
                                        })
                                        .collect(Collectors.toList());
        return neighborArea.stream()
                            .max(Comparator.comparing(Area::getTotalPath)
                                           .thenComparing(Area::getTotalDrop))
                            .get();
    }

    public AreaKey getKey() {
        return this.key;
    }

    public List<AreaKey> getDownhillNeighbor() {
        return downhillNeighbor;
    }

    public int getHeight() {
        return height;
    }

    public int getTotalPath() {
        return totalPath;
    }

    public int getTotalDrop() {
        return totalDrop;
    }

    public Area getNextArea() {
        return nextArea;
    }

    @Override
    public String toString() {
        return "Area{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", height=" + height +
                ", totalPath=" + totalPath +
                ", totalDrop=" + totalDrop +
                '}';
    }
}
