package com.droidsam.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TicTacToeGameTest {

    @Test
    public void canStartTheGame() {
        assertNotNull(new TicTacToeGame());
    }
}
