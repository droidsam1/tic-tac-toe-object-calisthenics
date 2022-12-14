package com.droidsam.app;

import com.droidsam.app.board.Coordinate;
import com.droidsam.app.game.GameStatus;
import com.droidsam.app.game.TicTacToeGame;
import com.droidsam.app.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeGameTest {

    private TicTacToeGame game;

    private static Stream<Arguments> invalidCoordinates() {
        return Stream.of(Arguments.of(-1, 1), Arguments.of(3, 1), Arguments.of(1, -1), Arguments.of(1, 3));
    }

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
        assertDoesNotThrow(() -> game.place(Player.O, anotherPosition));

        assertEquals(Player.X, game.getPlayerAtPosition(position));
        assertEquals(Player.O, game.getPlayerAtPosition(anotherPosition));
    }

    @Test
    public void playerXAlwaysGoesFirst() {
        assertThrows(InvalidParameterException.class, () -> game.place(Player.O, Coordinate.of(0, 0)));
    }

    @Test
    public void playersMustAlternatePlacing() {
        assertDoesNotThrow(() -> game.place(Player.X, Coordinate.of(0, 0)));
        assertThrows(InvalidParameterException.class, () -> game.place(Player.X, Coordinate.of(1, 1)));
    }

    @Test
    public void noPlayerWinsUntilPutsAtLeastThreeMarksOnTheBoard() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.O, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.O, Coordinate.of(1, 2));

        assertEquals(Player.NO_PLAYER, game.getWinner());
        assertEquals(GameStatus.ONGOING, game.getStatus());
    }

    @Test
    public void playerXWithThreeMarksInARowWinsTheGame() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.O, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.O, Coordinate.of(1, 2));
        game.place(Player.X, Coordinate.of(0, 2));

        assertEquals(Player.X, game.getWinner());
        assertEquals(GameStatus.WON, game.getStatus());
    }

    @Test
    public void playerYWithThreeMarksInAnyRowWinsTheGame() {
        game.place(Player.X, Coordinate.of(2, 0));
        game.place(Player.O, Coordinate.of(1, 0));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.O, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(0, 2));
        game.place(Player.O, Coordinate.of(1, 2));

        assertEquals(Player.O, game.getWinner());
        assertEquals(GameStatus.WON, game.getStatus());
    }

    @Test
    public void playerXWithThreeMarksInAnyColumnWinsTheGame() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.O, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.O, Coordinate.of(2, 2));
        game.place(Player.X, Coordinate.of(0, 2));

        assertEquals(Player.X, game.getWinner());
        assertEquals(GameStatus.WON, game.getStatus());
    }

    @Test
    public void playerYWithThreeMarksInAnyColumnWinsTheGame() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.O, Coordinate.of(1, 0));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.O, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(2, 2));
        game.place(Player.O, Coordinate.of(1, 2));

        assertEquals(Player.O, game.getWinner());
        assertEquals(GameStatus.WON, game.getStatus());
    }

    @Test
    public void playerXWithThreeMarksInAnyDiagonalWinsTheGame() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.O, Coordinate.of(1, 2));
        game.place(Player.X, Coordinate.of(1, 1));
        game.place(Player.O, Coordinate.of(2, 1));
        game.place(Player.X, Coordinate.of(2, 2));

        assertEquals(Player.X, game.getWinner());
        assertEquals(GameStatus.WON, game.getStatus());
    }

    @Test
    public void playerYWithThreeMarksInAnyDiagonalWinsTheGame() {
        game.place(Player.X, Coordinate.of(1, 0));
        game.place(Player.O, Coordinate.of(0, 2));
        game.place(Player.X, Coordinate.of(0, 1));
        game.place(Player.O, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(2, 2));
        game.place(Player.O, Coordinate.of(2, 0));

        assertEquals(Player.O, game.getWinner());
        assertEquals(GameStatus.WON, game.getStatus());
    }


    @Test
    public void gameIsADrawIfAllSquaresAreFilledAndNeitherPlayerAchievesThreeInARow() {
        game.place(Player.X, Coordinate.of(0, 0));
        game.place(Player.O, Coordinate.of(0, 1));
        game.place(Player.X, Coordinate.of(0, 2));
        game.place(Player.O, Coordinate.of(1, 0));
        game.place(Player.X, Coordinate.of(1, 2));
        game.place(Player.O, Coordinate.of(1, 1));
        game.place(Player.X, Coordinate.of(2, 1));
        game.place(Player.O, Coordinate.of(2, 2));
        game.place(Player.X, Coordinate.of(2, 0));

        assertEquals(Player.NO_PLAYER, game.getWinner());
        assertEquals(GameStatus.DRAW, game.getStatus());

    }

    @ParameterizedTest
    @MethodSource("invalidCoordinates")
    public void shouldPlayerPlaceMarksWithinTheLimitsOfTheBoard(int x, int y) {
        assertThrows(IndexOutOfBoundsException.class, () -> game.place(Player.X, Coordinate.of(x, y)));
    }
}
