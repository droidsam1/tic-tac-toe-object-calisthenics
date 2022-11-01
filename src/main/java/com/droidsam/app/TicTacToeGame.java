package com.droidsam.app;

import java.security.InvalidParameterException;
import java.util.Collection;

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

        if (board.hasPlayerACompleteRow(Player.X) || board.hasPlayerACompleteColumn(Player.X) || hasPlayerPlaceThreeMarksInDiagonal(Player.X)) {
            return Player.X;
        }

        if (board.hasPlayerACompleteRow(Player.O) || board.hasPlayerACompleteColumn(Player.O) || board.hasPlayerACompleteDiagonal(Player.O)) {
            return Player.O;
        }

        return NO_PLAYER;
    }

    private boolean hasPlayerPlaceThreeMarksInDiagonal(Player player) {
        return hasPlayerThreeMarksIn(board.getMainDiagonal(), player) || hasPlayerThreeMarksIn(board.getInverseMainDiagonal(), player);
    }

    private boolean hasPlayerThreeMarksIn(Collection<Coordinate> mainDiagonal, Player player) {
        return mainDiagonal.stream().allMatch(c -> board.getPlayerAt(c).equals(player));
    }

    public boolean isDraw() {
        return board.isFull() && NO_PLAYER.equals(getWinner());
    }
}
