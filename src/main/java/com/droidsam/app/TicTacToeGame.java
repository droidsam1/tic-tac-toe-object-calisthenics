package com.droidsam.app;

public class TicTacToeGame {

    private final Board board;

    public TicTacToeGame() {
        board = new Board();
    }

    public void place(Player player, Coordinate position) {
        board.place(player, position);
    }

    public Player getPlayerAtPosition(Coordinate position) {
        return board.getPlayerAt(position);
    }
}
