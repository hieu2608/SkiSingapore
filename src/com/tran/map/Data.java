package com.tran.map;

import com.tran.area.AreaKey;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class Data {
    final private int maxRows;
    final private int maxColumns;
    final private HashMap<AreaKey, Integer> heightMap;

    public Data(String fileName) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.maxRows = getMaxRowsFromHeader(lines.get(0));
        this.maxColumns = getMaxColumnsFromHeader(lines.get(0));
        this.heightMap = getMapData(lines.get(1));
    }

    private int getMaxRowsFromHeader(String header) {
        String[] parts = header.split("\\s+");
        return Integer.valueOf(parts[0]);
    }

    private int getMaxColumnsFromHeader(String header) {
        String[] parts = header.split("\\s+");
        return Integer.valueOf(parts[1]);
    }

    private HashMap<AreaKey,Integer> getMapData(String content) {
        String[] data = content.split("\\s+");
        HashMap<AreaKey, Integer> map = new HashMap<AreaKey, Integer>();
        int count = 0;
        for (int row = 0; row < this.maxRows; row++) {
            for (int column = 0; column < this.maxColumns; column++) {
                map.put(new AreaKey(row, column), Integer.valueOf(data[count]));
                count++;
            }
        }
        return map;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public int getMaxColumns() {
        return maxColumns;
    }

    public int getHeight(int posX, int posY) {
        return getHeight(new AreaKey(posX, posY));
    }

    public int getHeight(AreaKey key) {
        return heightMap.get(key);
    }
}
