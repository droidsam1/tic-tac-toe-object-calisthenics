package com.droidsam.app;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

import static com.droidsam.app.MatrixStatus.FULL;
import static com.droidsam.app.Player.NO_PLAYER;
import static com.droidsam.app.TypeThreeMarksInARow.NONE;

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
        if (getStatus() != GameStatus.ONGOING) {
            return;
        }
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

    private boolean isDraw() {
        return FULL.equals(this.board.getFillingStatus()) && NO_PLAYER.equals(this.getWinner());
    }

    public Player getWinner() {
        return Stream.of(Player.O, Player.X).filter(player -> !this.board.getPlayerThreeMarksInARow(player).equals(NONE)).findFirst().orElse(NO_PLAYER);
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

}
