package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    private static final Logger logger = LogManager.getLogger();

    private boolean[][] maze;
    private int[][] entryPoints;

    public Maze(String filename) {
        this.maze = MazeReader.readMazeFromFile(filename);
        this.entryPoints = MazeReader.locateEntryPoints(maze);
    }

    private static void logError(Exception e) {
        logger.error("/!\\\\ An error has occured /!\\\\" + e.getMessage());
    }

    public String toString() {
        String mazeString = "\n";
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j]) mazeString += "#";
                else mazeString += " ";
            }
            mazeString += "\n";
        }
        mazeString.concat("\nEntrance at: (" + entryPoints[0][0] + ", " + entryPoints[0][1] + ")");
        mazeString.concat("\nExit at: (" + entryPoints[1][0] + ", " + entryPoints[1][1] + ")");
        return mazeString;
    }
}
