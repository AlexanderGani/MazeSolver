/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

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
        // Should be from start to end cells
        Stack<MazeCell> s = new Stack<>();
        //start from end cell
        MazeCell current = maze.getEndCell();
        //while loop - iterate through whole maze
        while (current != maze.getStartCell()) {
            //push current cell
            s.push(current);
            //find parent of current cell, set it equal to current repeat loop - pathfinding
            current = current.getParent();
        }
        //create solution array list to return
        ArrayList<MazeCell> solution = new ArrayList<>();
        //add start cell (obv) to solution
        solution.add(maze.getStartCell());
        //clear stack, populate array list
        while(!s.isEmpty()) {
            solution.add(s.pop());
        }
        //return solution
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> s = new Stack<>();
        //start at the first cell
        MazeCell current = maze.getStartCell();
        //explore start cell
        current.setExplored(true);
        //push to stack so that start cell will be first cell to go thru loop
        s.push(current);
        //while loop until we reach end
        while(!s.empty()) {
            //update current mazeCell as most recent (west cell) for depth first search
            current = s.pop();
            //set rows and columns of new current mazeCell
            int row = current.getRow();
            int col = current.getCol();
            //check end condition
            if (current == maze.getEndCell()) {
                // Return the solution
                return getSolution();
            }
            //declare neighbor
            MazeCell neighbor;
            // Explore neighbors in the order: north, east, south, west
            //check north is valid + explore cell and set parent + add to s
            if(maze.isValidCell(row - 1, col)) {
                if(maze.isValidCell(row - 1, col)) {
                    neighbor = maze.getCell(row - 1, col);
                    neighbor.setExplored(true);
                    neighbor.setParent(current);
                    s.push(neighbor);
                }
            }
            //check east is valid + explore cell and set parent + add to s
            if(maze.isValidCell(row, col + 1)) {
                if(maze.isValidCell(row, col + 1)) {
                    neighbor = maze.getCell(row, col + 1);
                    neighbor.setExplored(true);
                    neighbor.setParent(current);
                    s.push(neighbor);
                }
            }
            //check south is valid + explore cell and set parent + add to s
            if(maze.isValidCell(row + 1, col)) {
                if(maze.isValidCell(row + 1, col)) {
                    neighbor = maze.getCell(row + 1, col);
                    neighbor.setExplored(true);
                    neighbor.setParent(current);
                    s.push(neighbor);
                }
            }
            //check west is valid + explore cell and set parent + add to s
            if(maze.isValidCell(row, col - 1)) {
                if(maze.isValidCell(row, col - 1)) {
                    neighbor = maze.getCell(row, col - 1);
                    neighbor.setExplored(true);
                    neighbor.setParent(current);
                    s.push(neighbor);
                }
            }
        }
        //if there is a problem with the maze return null resulting in error - user will understand why
        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        //use queue for BFS
        Queue<MazeCell> q = new LinkedList<>();
        //set current as start cell
        MazeCell current = maze.getStartCell();
        //explore start cell
        current.setExplored(true);
        //add to queue so that we start with the start cell in the while loop
        q.add(current);
        //while loop until we get to last cell
        while(!q.isEmpty()) {
            //update current mazeCell
            current = q.remove();
            //set row/col of current cell
            int row = current.getRow();
            int col = current.getCol();
            //check condition if we have completed maze
            if (current == maze.getEndCell()) {
                return getSolution();
            }
            //declare neighbor for if statements
            MazeCell neighbor;
            //check north is valid + explore cell and set parent + add to q
            if(maze.isValidCell(row - 1, col)) {
                neighbor = maze.getCell(row - 1, col);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                q.add(neighbor);
            }
            //check east is valid + explore cell and set parent + add to q
            if(maze.isValidCell(row, col + 1)) {
                neighbor = maze.getCell(row, col + 1);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                q.add(neighbor);
            }
            //check south is valid + explore cell and set parent + add to q
            if(maze.isValidCell(row + 1, col)) {
                neighbor = maze.getCell(row + 1, col);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                q.add(neighbor);
            }
            //check west is valid + explore cell and set parent + add to q
            if(maze.isValidCell(row, col - 1)) {
                neighbor = maze.getCell(row, col - 1);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                q.add(neighbor);
            }

        }
        //if there is a problem with the maze return null resulting in error - user will understand why
        return null;
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
