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

        if (hasPlayerPlaceThreeMarksInColumn(Player.X) || hasPlayerPlaceThreeMarksInRow(Player.X) || hasPlayerPlaceThreeMarksInDiagonal(Player.X)) {
            return Player.X;
        }

        if (hasPlayerPlaceThreeMarksInColumn(Player.O) || hasPlayerPlaceThreeMarksInRow(Player.O) || hasPlayerPlaceThreeMarksInDiagonal(Player.O)) {
            return Player.O;
        }

        return NO_PLAYER;
    }

    private boolean hasPlayerPlaceThreeMarksInRow(Player player) {
        return hasPlayerThreeMarksIn(board.getRow(0), player) || hasPlayerThreeMarksIn(board.getRow(1), player) || hasPlayerThreeMarksIn(board.getRow(2), player);
    }

    private boolean hasPlayerPlaceThreeMarksInColumn(Player player) {
        return hasPlayerThreeMarksIn(board.getColumn(0), player) || hasPlayerThreeMarksIn(board.getColumn(1), player) || hasPlayerThreeMarksIn(board.getColumn(2), player);
    }

    private boolean hasPlayerPlaceThreeMarksInDiagonal(Player player) {
        return hasPlayerThreeMarksIn(board.getMainDiagonal(), player) || hasPlayerThreeMarksIn(board.getInverseMainDiagonal(), player);
    }

    private boolean hasPlayerThreeMarksIn(Collection<Coordinate> positions, Player player) {
        return positions.stream().allMatch(c -> board.getPlayerAt(c).equals(player));
    }

    public boolean isDraw() {
        return isBoardFull() && NO_PLAYER.equals(getWinner());
    }

    private boolean isBoardFull() {
        return board.getPlayersIn(board.getMainDiagonal()).stream().noneMatch(p -> p.equals(NO_PLAYER));
    }
}
