package com.tran.area;

import com.tran.map.Data;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class Area {
    final private AreaKey key;
    final private int posX;
    final private int posY;
    final private int height;


    public Area(int posX, int posY, Data mapData) {
        this.key = new AreaKey(posX, posY);
        this.posX = posX;
        this.posY = posY;
        this.height = mapData.getHeight(posX, posY);
    }

    public AreaKey getKey() {
        return this.key;
    }
}
