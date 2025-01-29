package ca.mcmaster.se2aa4.mazerunner;

public class MazeExplorer {

    static enum Facing {

        UP, RIGHT, DOWN, LEFT;

        public Facing turnRight() {
            return values()[(this.ordinal() + 1) % values().length];
        }

        public Facing turnLeft() {
            return values()[(this.ordinal() + values().length - 1) % values().length];
        }
    }

    private String path;
    private final boolean[][] grid;
    private final int[] entrance;
    private final int[] exit;
    private int[] pos;

    private Facing facing;

    protected MazeExplorer(Maze maze) {
        path = "";
        grid = maze.getMazeArray();
        int[][] entryPoints = maze.getEntryPoints();
        entrance = entryPoints[0];
        exit = entryPoints[1];
        pos = entrance.clone();
        if (entrance[0] == 0) {
            facing = Facing.DOWN;
        } else if (entrance[1] == grid[0].length - 1) {
            facing = Facing.LEFT;
        } else if (entrance[0] == grid.length - 1) {
            facing = Facing.UP;
        } else if (entrance[1] == 0) {
            facing = Facing.RIGHT;
        }
    }

    public String getPath() {
        return path;
    }

    public int[] getPos() {
        return pos;
    }
    
    /*public void setPos(int[] pos) throws IllegalArgumentException {
        this.pos = pos;
    }*/

    public void explore(int maxSteps) {
        int steps = 1;
        this.step();
        while (!java.util.Arrays.equals(pos, exit) && steps < maxSteps) {
            this.turnRight(); // Turn right and add 'R' to the path
            if (!this.step()) {
                this.turnLeft(); // Turn left and add 'L' to the path
                if (!this.step()) {
                    this.turnLeft(); // Turn left and add 'L' to the path again
                    if (!this.step()) {
                        this.turnLeft(); // Turn left and add 'L' to the path once more
                        this.step();
                    }
                }
            }
            steps++;
        }
    }
    

    public void explore() {
        this.explore(1000);
    }

            // Turns the character right and appends 'R' to the path
    private void turnRight() {
        facing = facing.turnRight(); // Update facing direction using Facing enum
        path += 'R'; // Append 'R' to the path
    }

    // Turns the character left and appends 'L' to the path
    private void turnLeft() {
        facing = facing.turnLeft(); // Update facing direction using Facing enum
        path += 'L'; // Append 'L' to the path
    }


    public void follow(String directions) throws IllegalArgumentException {
        for (char c : directions.toCharArray()) {
            switch (c) {
                case 'F' -> {
                    if (!this.step()) {
                        throw new IllegalArgumentException("Path provided results in a crash.");
                    }
                }
                case 'R' -> this.turnRight(); // Turn right and append 'R' to the path
                case 'L' -> this.turnLeft(); // Turn left and append 'L' to the path
                default -> throw new IllegalArgumentException("Path string contains an illegal character.");
            }
        }
    }


    public void reset() {
        pos = entrance.clone();
        path = "";
    }

    private boolean nextTileEmpty() {
        try {
            return switch (this.facing) {
                case UP -> !grid[pos[0] - 1][pos[1]];
                case RIGHT -> !grid[pos[0]][pos[1] + 1];
                case DOWN -> !grid[pos[0] + 1][pos[1]];
                case LEFT -> !grid[pos[0]][pos[1] - 1];
                default -> false;
            };
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean step() {
        if (nextTileEmpty()) {
            switch (facing) {
                case UP -> {
                    pos[0] -= 1;
                }
                case RIGHT -> {
                    pos[1] += 1;
                }
                case DOWN -> {
                    pos[0] += 1;
                }
                case LEFT -> {
                    pos[1] -= 1;
                }
            }
            path += 'F';
            return true;
        } else return false;
    }
}