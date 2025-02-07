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
        } else {
            return null;
        }
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
        //char currentChar;
        char countingChar = 'a';
        for (char currentChar : path.toCharArray()) {
            if (validCharacters.indexOf(currentChar) == -1) {
                throw new IllegalArgumentException("Invalid path string.");
            }
            if (currentChar == countingChar) {
                count++;
            } else {
                if (count != 1) {
                    factorizedPath += String.valueOf(count);
                }
                factorizedPath += countingChar;
                countingChar = currentChar;
                count = 1;
            }
        }
        if (count != 1) {
            factorizedPath += String.valueOf(count);
        }
        factorizedPath += countingChar;
        return factorizedPath.substring(2);
    }

    @SuppressWarnings("empty-statement")
    public static String expandPath(String path) throws IllegalArgumentException {
        if (path.length() < 1) {
            throw new IllegalArgumentException("Invalid path string.");
        }
        if (Character.isDigit(path.charAt(path.length() - 1))) {
            throw new IllegalArgumentException("Invalid path string.");
        }
        String validCharacters = "FLR";
        String expandedPath = "";
        int charCount = 1;
        int j;
        char currentChar;
        for (int i = 0; i < path.length(); i++) {
            currentChar = path.charAt(i);
            if (Character.isDigit(currentChar)) {
                for (j = i; Character.isDigit(path.charAt(j)); j++);
                charCount = Integer.parseInt(path.substring(i, j));
            } else if (validCharacters.indexOf(currentChar) == -1) {
                throw new IllegalArgumentException("Invalid path string.");
            } else {
                for (int k = 0; k < charCount; k++) {
                    expandedPath += currentChar;
                }
                charCount = 1;
            }
        }
        return expandedPath;
    }
}
