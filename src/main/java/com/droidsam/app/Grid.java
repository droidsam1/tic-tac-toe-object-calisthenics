package com.droidsam.app;

public class Grid {

    private final Player[][] squares;

    public Grid(int height, int wide) {
        this.squares = new Player[height][wide];
    }

    public void put(Coordinate position, Player player) {
        squares[position.getX()][position.getY()] = player;
    }

    public Player get(Coordinate position) {
        return this.squares[position.getX()][position.getY()];
    }
}
