import java.util.Scanner;

/* TCSS 143 Programming Assignment 2 Driver file
 *
 * This will run the SimpleSweeper game base off a students
 * SweeperGame.java file
 *
 * Modified from Mr. Schuessler's assignment.
 */

public class SimpleSweeper {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int height;
        int width;
        boolean done = false;
        int x;
        int y;

        do {
            System.out.println("Sweep the sand and find the treasure");
            System.out.println("Please enter the number of height and width"
                + "\nof the game board");
            System.out.print("Height of board: ");
            height = s.nextInt();
            System.out.print("Width of board: ");
            width = s.nextInt();
            SweeperGame game = new SweeperGame(height, width);
            s.nextLine();
            do {
                do {
                    System.out.print("Enter treasure coordinates separated by a space: ");
                    x = s.nextInt();
                    y = s.nextInt();
                } while (!game.checkOutOfBounds(x, y) || game.beenSwept(x, y));
                done = game.digSand(x, y);
                System.out.println(game.toString());
                s.nextLine();
            } while (!(done));
          System.out.println("You found the treasure in " + game.getTotalMoves() +" moves!");
          System.out.println("Out of " + game.getBoardHeight() * game.getBoardWidth() + " possible moves.");
          System.out.print("Would you like to find more treasure [Y/N]? ");
        } while (s.nextLine().equalsIgnoreCase("Y"));

    }
}