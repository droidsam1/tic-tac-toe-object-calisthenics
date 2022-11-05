package com.droidsam.app;

import static com.droidsam.app.MatrixStatus.FULL;
import static com.droidsam.app.Player.NO_PLAYER;
import static com.droidsam.app.TypeThreeMarksInARow.NONE;

public class Board {
    private final SquareMatrix squareMatrix;

    public Board() {
        this.squareMatrix = new SquareMatrix(3);
    }

    public void place(Player player, Coordinate position) {
        var status = getStatus();
        if (status != GameStatus.ONGOING) {
            return;
        }
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

    private boolean isDraw() {
        return FULL.equals(this.squareMatrix.getFillingStatus()) && NO_PLAYER.equals(this.getWinner());
    }

    public Player getWinner() {

        if (!this.squareMatrix.getPlayerThreeMarksInARow(Player.X).equals(NONE)) {
            return Player.X;
        }
        if (!this.squareMatrix.getPlayerThreeMarksInARow(Player.O).equals(NONE)) {
            return Player.O;
        }

        return NO_PLAYER;
    }

}
