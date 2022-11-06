package com.droidsam.app.board;

import java.util.Objects;

public class Coordinate {

    protected final int x;
    protected final int y;

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected Coordinate(Coordinate coordinate) {
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    public static Coordinate of(int x, int y) {
        return new Coordinate(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
