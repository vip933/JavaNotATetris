package hse.java.leypunskiyMaksim208hw5.model;

import hse.java.leypunskiyMaksim208hw5.geometry.Figures;
import hse.java.leypunskiyMaksim208hw5.geometry.Point;
import hse.java.leypunskiyMaksim208hw5.geometry.Rotations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameFieldLogicTest {

    @Test
    void canPlaceInEmptySpace() {
        GameField gameField = new GameField();
        assertTrue(gameField.placeFigureField(Figures.L_BIG_SHAPE.getField(), new Point(3, 5)));
    }

    @Test
    void cannotPlaceOutsideBounds() {
        GameField gameField = new GameField();
        assertFalse(gameField.placeFigureField(Figures.L_BIG_SHAPE.getField(), new Point(5, 7)));
    }

    @Test
    void cannotPlaceIfPlaceAlreadyOccupied() {
        GameField gameField = new GameField();
        gameField.placeFigureField(Figures.LINE_SHAPE.getField(), new Point(0, 0));
        assertFalse(gameField.placeFigureField(Figures.T_BIG_SHAPE.getField(), new Point(2, 0)));
    }

    @Test
    void canPlaceFiguresTouchingEachOther() {
        GameField gameField = new GameField();
        gameField.placeFigureField(Figures.LINE_SHAPE.getField(), new Point(0, 0));
        assertTrue(gameField.placeFigureField(Figures.T_BIG_SHAPE.getField(), new Point(0, 1)));
    }

    @Test
    void canPlaceRotatedFigure() {
        GameField gameField = new GameField();
        assertTrue(gameField.placeFigureWithRotation(Figures.Z_NORMAL_SHAPE, Rotations.DEG180, new Point(3, 4)));
    }
}