package com.droidsam.app.game;

import com.droidsam.app.board.Board;
import com.droidsam.app.board.Coordinate;
import com.droidsam.app.player.Player;

import java.security.InvalidParameterException;

import static com.droidsam.app.board.BoardStatus.FULL;
import static com.droidsam.app.player.Player.NO_PLAYER;

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
        return board.applyWinningRule(new ThreeMarksInARowWinningRule(3));
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
