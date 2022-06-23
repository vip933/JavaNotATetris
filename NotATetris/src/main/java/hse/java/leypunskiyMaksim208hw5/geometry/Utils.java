package hse.java.leypunskiyMaksim208hw5.geometry;

public final class Utils {
    private Utils() { }

    /**
     * Standard matrix addition.
     * @param origin A matrix.
     * @param toAdd B matrix.
     * @return A + B result.
     */
    public static int[][] matrixAddition(int[][] origin, int[][] toAdd) {
        final int[][] newOrigin = new int[origin.length][origin[0].length];
        for (int i = 0; i < origin.length; ++i) {
            for (int j = 0; j < origin[0].length; ++j) {
                newOrigin[i][j] = origin[i][j] + toAdd[i][j];
            }
        }
        return newOrigin;
    }

    /**
     * If there is a value > 1 in our field it means that there is a collision and this matrix cannot exist.
     * @param matrix Matrix which we are checking.
     * @return True if it is correct, false otherwise.
     */
    public static boolean isAdditionCorrect(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (ints[j] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Matrix rotation clockwise.
     * @param matrix Matrix to rotate.
     * @param rotation Angle on which we will rotate matrix.
     * @return Rotated matrix.
     */
    public static int[][] rotate(int[][] matrix, Rotations rotation) {
        switch (rotation) {
            case DEG0 -> {
                return matrix;
            }
            case DEG90 -> {
                return rotate90(matrix);
            }
            // Rotating 90 + rotating 90 == rotating 180.
            case DEG180 -> {
                return rotate90(rotate90(matrix));
            }
            // Rotating 90 + rotating 90 + rotating 90 == rotating 270.
            case DEG270 -> {
                return rotate90(rotate90(rotate90(matrix)));
            }
            default -> throw new IllegalArgumentException("Wrong rotation!");
        }
    }

    /**
     * Standard matrix rotation.
     * @param matrix Matrix which we want to rotate on 90 degrees.
     * @return Rotated on 90 degrees matrix.
     */
    private static int[][] rotate90(int[][] matrix) {
        final int height = matrix.length;
        final int width = matrix[0].length;
        int[][] rotatedMatrix = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedMatrix[j][height - 1 - i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }
}
