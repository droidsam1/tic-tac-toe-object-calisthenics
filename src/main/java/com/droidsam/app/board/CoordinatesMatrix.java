package com.droidsam.app.board;

import com.droidsam.app.player.Player;

import java.util.HashMap;
import java.util.Map;

public class CoordinatesMatrix {

    private final Map<Coordinate, Player> squares;
    private final int sideSize;

    public CoordinatesMatrix(int sideSize) {
        this.sideSize = sideSize;
        squares = new HashMap<>(sideSize * sideSize);
    }

    public void put(Coordinate position, Player player) {
        squares.put(position, player);
    }

    public MatrixStatus getFillingStatus() {
        if (this.squares.keySet().size() == sideSize * sideSize) {
            return MatrixStatus.FULL;
        }
        return MatrixStatus.NOT_FULL;
    }

    public Player get(Coordinate position) {
        if (!squares.containsKey(position)) {
            return Player.NO_PLAYER;
        }
        return squares.get(position);
    }

    public Integer getSize() {
        return sideSize;
    }
}
