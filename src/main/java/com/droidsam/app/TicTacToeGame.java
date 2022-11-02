package com.droidsam.app;

import java.security.InvalidParameterException;

import static com.droidsam.app.Player.NO_PLAYER;

public class TicTacToeGame {

    private final Board board;
    private Player lastPlayer;

    public TicTacToeGame() {
        board = new Board();
        lastPlayer = NO_PLAYER;
    }

    public void place(Player player, Coordinate position) {
        enforcePlayerXPlaysFirst(player);
        enforcePlayersAlternatePlacing(player);
        board.place(player, position);
        lastPlayer = player;
    }

    private void enforcePlayersAlternatePlacing(Player player) {
        if (lastPlayer.equals(player)) {
            throw new InvalidParameterException("Players must alternate placing");
        }
    }

    private void enforcePlayerXPlaysFirst(Player player) {
        if (NO_PLAYER.equals(lastPlayer) && Player.X != player) {
            throw new InvalidParameterException("Player X always goes first");
        }
    }

    public Player getPlayerAtPosition(Coordinate position) {
        return board.getPlayerAt(position);
    }

    public Player getWinner() {
        return board.getWinner();
    }

    public GameStatus geStatus() {
        return board.getStatus();
    }
}
