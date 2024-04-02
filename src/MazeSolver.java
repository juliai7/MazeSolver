/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        Stack<MazeCell> reverseSol = new Stack<MazeCell>();
        ArrayList<MazeCell> sol = new ArrayList<MazeCell>();
        reverseSol.push(maze.getEndCell());
        MazeCell curr = maze.getEndCell().getParent();
        while (curr != null) {
            reverseSol.push(curr);
            curr = curr.getParent();
        }
        int size = reverseSol.size();
        for (int i = 0; i < size; i++) {
            sol.add(reverseSol.pop());
        }
        // Should be from start to end cells
        return sol;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell curr = maze.getStartCell();
        Stack<MazeCell> s = new Stack<>();
        while (curr != maze.getEndCell()) {
            int row = curr.getRow();
            int col = curr.getCol();
            if (maze.isValidCell(row - 1, col)) {
                s.push(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setParent(curr);
                maze.getCell(row - 1, col).setExplored(true);
            }
            if (maze.isValidCell(row, col + 1)) {
                s.push(maze.getCell(row, col + 1));
                maze.getCell(row, col + 1).setParent(curr);
                maze.getCell(row, col + 1).setExplored(true);
            }
            if (maze.isValidCell(row + 1, col)) {
                s.push(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setParent(curr);
                maze.getCell(row + 1, col).setExplored(true);
            }
            if (maze.isValidCell(row, col - 1)) {
                s.push(maze.getCell(row, col - 1));
                maze.getCell(row, col - 1).setParent(curr);
                maze.getCell(row, col - 1).setExplored(true);
            }
            curr = s.pop();
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell curr = maze.getStartCell();
        Queue<MazeCell> q = new LinkedList<>();
        while (curr != maze.getEndCell()) {
            int row = curr.getRow();
            int col = curr.getCol();
            if (maze.isValidCell(row - 1, col)) {
                q.add(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setParent(curr);
                maze.getCell(row - 1, col).setExplored(true);
            }
            if (maze.isValidCell(row, col + 1)) {
                q.add(maze.getCell(row, col + 1));
                maze.getCell(row, col + 1).setParent(curr);
                maze.getCell(row, col + 1).setExplored(true);
            }
            if (maze.isValidCell(row + 1, col)) {
                q.add(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setParent(curr);
                maze.getCell(row + 1, col).setExplored(true);
            }
            if (maze.isValidCell(row, col - 1)) {
                q.add(maze.getCell(row, col - 1));
                maze.getCell(row, col - 1).setParent(curr);
                maze.getCell(row, col - 1).setExplored(true);
            }
            curr = q.remove();
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
