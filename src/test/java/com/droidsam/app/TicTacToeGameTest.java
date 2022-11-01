package com.droidsam.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeGameTest {

    private TicTacToeGame game;

    @BeforeEach
    public void setup() {
        game = new TicTacToeGame();
    }

    @Test
    public void canStartTheGame() {
        assertNotNull(game);
    }

    @Test
    public void aPlayerCanPlace() {
        Player player = Player.X;
        Coordinate position = Coordinate.of(0, 0);
        assertDoesNotThrow(() -> game.place(player, position));
        assertEquals(player, game.getPlayerAtPosition(position));
    }

    @Test
    public void gameRememberPlayersMoves() {
        Player player = Player.X;
        Coordinate position = Coordinate.of(0, 0);
        Player anotherPlayer = Player.Y;
        Coordinate anotherPosition = Coordinate.of(1, 1);

        assertDoesNotThrow(() -> game.place(player, position));
        assertDoesNotThrow(() -> game.place(anotherPlayer, anotherPosition));

        assertEquals(player, game.getPlayerAtPosition(position));
        assertEquals(anotherPlayer, game.getPlayerAtPosition(anotherPosition));
    }
}
