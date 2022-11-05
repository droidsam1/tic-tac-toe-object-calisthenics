package com.droidsam.app;

import java.util.HashMap;
import java.util.Map;

import static com.droidsam.app.Player.NO_PLAYER;

public class Board {

    private final Map<Coordinate, Player> squares;
    private final SquareMatrix squareMatrix;
    private final int size;

    public Board() {
        this.size = 3;
        this.squares = new HashMap<>(getFullSize(size));
        this.squareMatrix = new SquareMatrix(3);
    }

    public void place(Player player, Coordinate position) {
        var status = getStatus();
        if (status != GameStatus.ONGOING) {
            return;
        }

        squares.put(position, player);
        squareMatrix.put(position, player);
    }

    public GameStatus getStatus() {
        if (getWinner() != NO_PLAYER) {
            return GameStatus.WON;
        }
        if (isDraw()) {
            return GameStatus.DRAW;
        }
        return GameStatus.ONGOING;
    }

    public Player getPlayerAt(Coordinate position) {
        return squareMatrix.get(position);
    }

    private boolean isFull() {
        return this.squares.values().stream().filter(p -> !p.equals(NO_PLAYER)).count() == getFullSize(size);
    }

    private int getFullSize(int size) {
        return size * size;
    }

    private boolean isDraw() {
        return this.isFull() && NO_PLAYER.equals(this.getWinner());
    }

    public Player getWinner() {

        if (!this.squareMatrix.getRowForPlayer(Player.X).equals(TypeOfSquareRow.NONE)) {
            return Player.X;
        }
        if (!this.squareMatrix.getRowForPlayer(Player.O).equals(TypeOfSquareRow.NONE)) {
            return Player.O;
        }

        return NO_PLAYER;
    }

}
