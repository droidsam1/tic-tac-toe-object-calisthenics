package com.droidsam.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Player player = new Player();
        Coordinate position = new Coordinate();
        assertDoesNotThrow(() -> game.place(player, position));
    }
}
