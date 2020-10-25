/*
 * TCSS 143 - Winter 2018
 * Instructor: Dr. Raghavi Sakpal
 * SweeperGame
 */

/**
 * A SweeperGame class that runs the underlying functions for Mr. Schussler's SimpleSweeper.java.
 *
 * @author Samuel Servane swain91@uw.edu
 * @version 8 February 2018
 */
import java.util.Random;

public class SweeperGame {

    // Fields
    private char[][] gameBoard;         // Creates an array of chars to store Game Board symbols.
    private int treasureX;              // Stores the X coordinate of the treasure.
    private int treasureY;              // Stores the Y coordinate of the treasure.
    private int totalMoves;             // Stores value that counts total moves.
    private boolean found;              // Boolean that ticks from false to true if treasure found.

    // Constructor
    public SweeperGame(int height, int width) {
        found = false;
        Random rnd = new Random();          // Creates new random object to randomize coordinates of treasure.
        treasureY = rnd.nextInt(height);
        treasureX = rnd.nextInt(width);

        this.gameBoard = new char[height][width];                    // Creates the game board.
        for (int row = 0; row < this.gameBoard.length; row++) {
            for (int col = 0; col < this.gameBoard[row].length; col++) {
                this.gameBoard[row][col] = '.';
            }
        }

        gameBoard[treasureY][treasureX] = 'T';      // Assigns treasure to game board location.
        totalMoves = 0;
    }

    /**
     * Computes if space on gameBoard has been accessed before.
     *
     * @param ycoord is the user input y-coordinate.
     * @param xcoord is the user input x- coordinate.
     *
     * @return false if input coordinates have not been accessed before
     * @return true if input coordinates have been accessed before
     */
    public boolean beenSwept(int ycoord, int xcoord) {
        if (gameBoard[ycoord][xcoord] == '.' || gameBoard[ycoord][xcoord] == 'T') {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Determines if treasure has been found at user input coordinates.
     *
     * @param ycoord is the user input y-coordinate.
     * @param xcoord is the user input x-coordinate.
     *
     * @return true if treasure was found.
     * @return false if treasure was not found.
     */
    public boolean treasureFound(int ycoord, int xcoord) {
        if (gameBoard[ycoord][xcoord] == 'T') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines if user input is out of the bounds of the array gameBoard.
     *
     * @param ycoord is the user input y-coordinate.
     * @param xcoord is the user input x-coordinate.
     *
     * @return returns true if the input coordinates are less than the array length.
     * @return false if input coordinates exceed the array length.
     */
    public boolean checkOutOfBounds(int ycoord, int xcoord) {
        if (xcoord < gameBoard.length && ycoord < gameBoard[0].length) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Gets the height of the gameBoard array.
     *
     * @return returns the gameBoard column length.
     */
    public int getBoardHeight() {
        return gameBoard[0].length;
    }

    /**
     * Gets the width of the gameBoard array.
     *
     * @return returns the gameBoard row length.
     */
    public int getBoardWidth() {
        return gameBoard.length;
    }

    /**
     * Gets the variable data from totalMoves.
     *
     * @return returns the amount of total moves..
     */
    public int getTotalMoves() {
        return this.totalMoves;
    }

    /**
     * Searches array at user input coordinates and verifies if treasure is found.
     * If treasure is not found, sets location to an integer corresponding to Manhattan Distance/Taxicab Geometry
     * to the treasure.
     * Increments the totalMoves counter for each move.
     *
     * @param ycoord is the user input y-coordinate.
     * @param xcoord is the user input y-coordinate.
     *
     * @return true is the treasure is found.
     * @ return
     */
    public boolean digSand(int ycoord, int xcoord) {
        if (treasureFound(ycoord, xcoord)) {
            found = true;
            this.totalMoves += 1;
            return true;
        } else {
            /* Computes Manhattan Distance to the treasure. */
            gameBoard[ycoord][xcoord] += (char)((Math.abs(ycoord - treasureY) + Math.abs(xcoord - treasureX)) + 2);
            this.totalMoves += 1;
            return false;
        }
    }

    /* Override the toString() method to display the gameBoard array. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();     // Creates new StringBuilder object to turn gameBoard into a string.

        /* Displays the game board. */
        for (int sand = 0; sand < gameBoard.length; sand++) {
            sb.append(",~,");
        }
        sb.append("\n");
        for (int row = getBoardHeight() - 1; 0 < row + 1; row--) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                if (gameBoard[row][col] == 'T' && !found) {
                    sb.append(";" + '.' + ":");
                } else {
                    sb.append(";" + gameBoard[row][col] + ":");
                }
            }
            sb.append("\n");
            for (int sand = 0; sand < gameBoard[row].length; sand++) {
                sb.append("`~`");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
