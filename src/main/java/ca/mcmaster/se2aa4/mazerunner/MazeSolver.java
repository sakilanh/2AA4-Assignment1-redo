package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public final class MazeSolver {

    private MazeSolver() {

    }

    public static String solve(Maze maze) {
        MazeExplorer explorer = maze.addExplorer();
        explorer.reset();
        explorer.explore();
        if (Arrays.equals(explorer.getPos(), maze.getEntryPoints()[1])) {
            return explorer.getPath();
        } else return null;
    }

    public static boolean verify(Maze maze, String sol) {
        MazeExplorer explorer = maze.addExplorer();
        explorer.reset();
        explorer.follow(sol);
        return Arrays.equals(explorer.getPos(), maze.getEntryPoints()[1]);
    }

    public static String factorizePath(String path) throws IllegalArgumentException {
        path = path.replaceAll("\\s", "");
        String validCharacters = "FLR";
        String factorizedPath = "";
        int count = 0;
        char currentChar;
        char countingChar = 'a';
        for (int i = 0; i < path.length(); i++) {
            currentChar = path.charAt(i);
            if (validCharacters.indexOf(currentChar) == -1) {
                throw new IllegalArgumentException("Invalid path string.");
            }
            if (currentChar == countingChar) {
                count++;
            } else {
                factorizedPath += String.valueOf(count) + countingChar;
                countingChar = currentChar;
                count = 1;
            }
        }
        return factorizedPath;
    }

    //todo
    public static String expandPath(String path) {
        return null;
    }
}