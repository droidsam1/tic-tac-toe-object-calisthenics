package com.droidsam.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
