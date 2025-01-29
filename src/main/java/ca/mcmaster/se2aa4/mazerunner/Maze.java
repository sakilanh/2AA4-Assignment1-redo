package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private final boolean[][] maze;
    private final int[][] entryPoints;
    private MazeExplorer explorer;

    private String filepath;

    protected Maze(boolean[][] maze, int[][] entryPoints) {
        this.maze = maze;
        this.entryPoints = entryPoints;
    }

    public boolean[][] getMazeArray() {
        return this.maze;
    }

    public int[][] getEntryPoints() {
        return this.entryPoints;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public MazeExplorer getExplorer() {
        return this.explorer;
    }

    public MazeExplorer addExplorer() {
        if (explorer == null) {
            MazeExplorer newExplorer = new MazeExplorer(this);
            this.explorer = newExplorer;
            return newExplorer;
        } else {
            return this.explorer;
        }
    }

    @Override
    public String toString() {
        String mazeString = "\n";
        for (boolean[] maze1 : maze) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze1[j]) {
                    mazeString += "#";
                } else {
                    mazeString += " ";
                }
            }
            mazeString += "\n";
        }
        mazeString += "\nEntrance at: (" + entryPoints[0][0] + ", " + entryPoints[0][1] + ")";
        mazeString += "\nExit at: (" + entryPoints[1][0] + ", " + entryPoints[1][1] + ")";
        return mazeString;
    }
}
