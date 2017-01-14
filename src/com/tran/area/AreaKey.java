package com.tran.area;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class AreaKey {
    final private int posX;
    final private int posY;
    final private int hash;

    public AreaKey(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        int sum = posX + posY;
        hash = sum * (sum + 1) / 2 + posX;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AreaKey){
            return hash == ((AreaKey) other).hash;
        }
        return false;
    }
}
