package com.droidsam.app.board;

import com.droidsam.app.game.ThreeMarksInARowWinningRule;
import com.droidsam.app.player.Player;

public class Board {
    private final CoordinatesMatrix coordinatesMatrix;
    private final int boardSize;

    public Board() {
        this.boardSize = 3;
        this.coordinatesMatrix = new CoordinatesMatrix(boardSize);
    }

    public void place(Player player, Coordinate position) {
        enforceBoardLimits(position);
        coordinatesMatrix.put(position, player);
    }

    private void enforceBoardLimits(Coordinate input) {
        CoordinateWithinBoardSize.enforceLimits(input, boardSize);
    }

    public Player getPlayerAt(Coordinate position) {
        return coordinatesMatrix.get(position);
    }

    public Player applyWinningRule(ThreeMarksInARowWinningRule ruleToWin) {
        return ruleToWin.getWinner(this.coordinatesMatrix);
    }

    public BoardStatus getFillingStatus() {
        if (coordinatesMatrix.getFillingStatus().equals(MatrixStatus.FULL)) {
            return BoardStatus.FULL;
        }
        return BoardStatus.NOT_FULL;
    }
}
