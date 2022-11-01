package com.droidsam.app;

import java.security.InvalidParameterException;

public class TicTacToeGame {

    private final Board board;

    public TicTacToeGame() {
        board = new Board();
    }

    public void place(Player player, Coordinate position) {
        enforcePlayerXPlaysFirst(player);
        board.place(player, position);
    }

    private void enforcePlayerXPlaysFirst(Player player) {
        if (board.isEmpty() && Player.X != player) {
            throw new InvalidParameterException("Player X always goes first");
        }
    }

    public Player getPlayerAtPosition(Coordinate position) {
        return board.getPlayerAt(position);
    }
}
