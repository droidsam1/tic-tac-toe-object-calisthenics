package com.droidsam.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeGameTest {

    private TicTacToeGame game;

    @BeforeEach
    public void setup() {
        game = new TicTacToeGame();
    }

    @Test
    public void aPlayerCanPlace() {
        Coordinate position = Coordinate.of(0, 0);

        assertDoesNotThrow(() -> game.place(Player.X, position));
        assertEquals(Player.X, game.getPlayerAtPosition(position));
    }

    @Test
    public void gameRememberPlayersMoves() {
        Coordinate position = Coordinate.of(0, 0);
        Coordinate anotherPosition = Coordinate.of(1, 1);

        assertDoesNotThrow(() -> game.place(Player.X, position));
        assertDoesNotThrow(() -> game.place(Player.Y, anotherPosition));

        assertEquals(Player.X, game.getPlayerAtPosition(position));
        assertEquals(Player.Y, game.getPlayerAtPosition(anotherPosition));
    }

    @Test
    public void playerXAlwaysGoesFirst() {
        assertThrows(InvalidParameterException.class, () -> game.place(Player.Y, Coordinate.of(0, 0)));
    }

    @Test
    public void playersMustAlternatePlacing() {
        assertDoesNotThrow(() -> game.place(Player.X, Coordinate.of(0, 0)));
        assertThrows(InvalidParameterException.class, () -> game.place(Player.X, Coordinate.of(1, 1)));
    }

    @Test
    public void noPlayerWinsUntilPutsAtLeastThreeMarksOnTheBoard() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.Y, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.Y, Coordinate.of(1, 2));

        assertEquals(Player.NO_PLAYER, game.getWinner());
    }

    @Test
    public void playerXWithThreeMarksInARowWinsTheGame() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.Y, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.Y, Coordinate.of(1, 2));
        game.place(Player.X, Coordinate.of(0, 2));

        assertEquals(Player.X, game.getWinner());
    }

    @Test
    public void playerYWithThreeMarksInAnyRowWinsTheGame() {
        game.place(Player.X, Coordinate.of(2, 0));
        game.place(Player.Y, Coordinate.of(1, 0));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.Y, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(0, 2));
        game.place(Player.Y, Coordinate.of(1, 2));

        assertEquals(Player.Y, game.getWinner());
    }

    @Test
    public void playerXWithThreeMarksInAnyColumnWinsTheGame() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.Y, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.Y, Coordinate.of(2, 2));
        game.place(Player.X, Coordinate.of(0, 2));

        assertEquals(Player.X, game.getWinner());
    }

    @Test
    public void playerYWithThreeMarksInAnyColumnWinsTheGame() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.Y, Coordinate.of(1, 0));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.Y, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(2, 2));
        game.place(Player.Y, Coordinate.of(1, 2));

        assertEquals(Player.Y, game.getWinner());
    }
}
