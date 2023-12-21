import java.util.*;
import java.io.*;

public class escapeSolution {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\ayomi\\Downloads\\CS210 LAB\\Maze5.txt"); // Corrected file path (ensure it's right)

        int lives = 200;
        int posX = 0; // Starting X position
        int posY = 0; // Starting Y position
        String[] input = new String[20]; // Store maze lines

        try {
            Scanner scan = new Scanner(file);

            for (int i = 0; i < 20; i++) {
                input[i] = scan.nextLine(); // Read the maze
            }
            posX = Integer.parseInt(scan.nextLine()); // Starting X position
            posY = Integer.parseInt(scan.nextLine()); // Starting Y position
            scan.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e);
            return; // Exit if there's an error
        }

        boolean[][] maze = new boolean[20][20]; // Maze representation
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                maze[i][j] = input[i].charAt(j) != 'X'; // Wall check
            }
        }

        Brain myBrain = new Brain();

        while (lives > 0) {
            // Visualize the maze and current position
            printMaze(maze, posX, posY);

            try {
                Thread.sleep(300); // Slow down for visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Ensure we don't go out of bounds
            if (posX <= 0 || posX >= maze.length - 1 || posY <= 0 || posY >= maze[0].length - 1) {
                continue;
            }

            // Get the move from the brain
            String move = myBrain.getMove(maze[posX - 1][posY], maze[posX + 1][posY], maze[posX][posY + 1], maze[posX][posY - 1]);
            // Move based on the brain's decision
            if (move.equals("north") && maze[posX - 1][posY]) {
                posX--;
            } else if (move.equals("south") && maze[posX + 1][posY]) {
                posX++;
            } else if (move.equals("east") && maze[posX][posY + 1]) {
                posY++;
            } else if (move.equals("west") && maze[posX][posY - 1]) {
                posY--;
            }
            lives--;
            if (posY % 19 == 0 || posX % 19 == 0) { // Check for exit
                System.out.println("You found the exit at: " + posX + "," + posY);
                System.exit(0);
            }
        }
        System.out.println("You died in the maze!");
    }

    // Method to print the maze
    private static void printMaze(boolean[][] maze, int posX, int posY) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i == posX && j == posY) {
                    System.out.print("o"); // Current position
                } else {
                    System.out.print(maze[i][j] ? " " : "X"); // Space or wall
                }
            }
            System.out.println();
        }
    }
}

class Brain {
    // A stack used for storing the reverse direction of each move made.
    Stack<String> stacks = new Stack<String>();

    // A 2D array representing a map to track which coordinates have been visited.
    // The map is sized 41x41, and the starting position is assumed to be the center.
    boolean [][] map = new boolean[41][41];

    // Current X and Y coordinates, starting from the center of the map.
    int x = 20;
    int y = 20;

    /**
     * Determines the next move based on the available direction options.
     * The method updates the current position and marks the position on the map as visited.
     * If no new direction is available, it backtracks to the previous position.
     * 
     * param north true if moving north is possible, otherwise false.
     * param south true if moving south is possible, otherwise false.
     * param east true if moving east is possible, otherwise false.
     * param west true if moving west is possible, otherwise false.
     * return The direction of the next move.
     */
    public String getMove(boolean north, boolean south, boolean east, boolean west) {
        // Check if moving north is possible and if the northern cell has not been visited.
        if (north && !map[x - 1][y]) {
            x--;
            map[x][y] = true; // Mark the new position as visited.
            stacks.push("south"); // Push the opposite direction (south) onto the stack.
            return "north"; // Return the direction moved.
        }
        // Similar logic applies for the south, east, and west directions.
        else if (south && !map[x + 1][y]) {
            x++;
            map[x][y] = true;
            stacks.push("north");
            return "south";
        }
        else if (east && !map[x][y + 1]) {
            y++;
            map[x][y] = true;
            stacks.push("west");
            return "east";
        }
        else if (west && !map[x][y - 1]) {
            y--;
            map[x][y] = true;
            stacks.push("east");
            return "west";
        }

        // If none of the directions are possible, pop the last direction from the stack and backtrack.
        String backup = stacks.pop();
        switch (backup) {
            case "north":
                x--; // Move north if the backup direction is north.
                break;
            case "south":
                x++; // Move south if the backup direction is south.
                break;
            case "east":
                y++; // Move east if the backup direction is east.
                break;
            case "west":
                y--; // Move west if the backup direction is west.
                break;
        }
        return backup; // Return the backup direction used.
    }
}