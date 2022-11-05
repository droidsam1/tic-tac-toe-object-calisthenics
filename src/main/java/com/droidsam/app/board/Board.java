package com.droidsam.app.board;

import com.droidsam.app.game.ThreeMarksInARowWinningRule;
import com.droidsam.app.player.Player;

public class Board {
    private final CoordinatesMatrix coordinatesMatrix;

    public Board() {
        this.coordinatesMatrix = new CoordinatesMatrix(3);
    }

    public void place(Player player, Coordinate position) {
        coordinatesMatrix.put(position, player);
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
