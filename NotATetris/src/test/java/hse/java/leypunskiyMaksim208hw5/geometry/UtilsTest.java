package hse.java.leypunskiyMaksim208hw5.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void matrixAddition() {
    }

    @Test
    void isAdditionCorrect() {
    }

    @Test
    void canRotateSameLengthFigures() {
        Figures figure = Figures.L_BIG_SHAPE;

        int[][] expected = new int[][] {
                {1, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        };
        int[][] actual = Utils.rotate(figure.getField(), Rotations.DEG90);

        assertArrayEquals(expected, actual);
    }

    @Test
    void canRotateDifferentLengthFigures() {
        Figures figure = Figures.Z_NORMAL_SHAPE;

        int[][] expected = new int[][] {
                {0, 1, 1},
                {1, 1, 0}
        };
        int[][] actual = Utils.rotate(figure.getField(), Rotations.DEG90);

        assertArrayEquals(expected, actual);
    }
}