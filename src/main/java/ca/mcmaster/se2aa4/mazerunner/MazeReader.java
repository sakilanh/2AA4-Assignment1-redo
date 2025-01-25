package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MazeReader {

    private static final Logger logger = LogManager.getLogger();  
    private static IllegalArgumentException defaultError = new IllegalArgumentException("The specified file is not a valid maze.");

    private MazeReader() {

    }

    private static void logError(Exception e) {
        logger.error("/!\\\\ An error has occured /!\\\\" + e.getMessage());
    }

    public static boolean[][] readMazeFromFile(String filepath) throws IllegalArgumentException { 
        ArrayList<String> mazeTxt = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {   
            String line;
            while ((line = reader.readLine()) != null) {
                mazeTxt.add(line);
            }
        } catch (Exception e) {
            logError(e);
            throw defaultError;
        }
        if (!validateFile(mazeTxt)) {
            logError(defaultError);
            throw defaultError;
        } else {
            boolean[][] maze = new boolean[mazeTxt.size()][mazeTxt.get(0).length()]; 
            for (int i = 0; i < mazeTxt.size(); i++) {
                logger.info(System.lineSeparator());
                for (int j = 0; j < mazeTxt.get(i).length(); j++) {
                    if (mazeTxt.get(i).charAt(j) == '#') {
                        maze[i][j] = true;
                        logger.info("WALL ");
                    } else {
                        maze[i][j] = false;
                        logger.info("PASS ");
                    }      
                }
            }        
            return maze;         
        }
    }

    private static boolean validateFile(ArrayList<String> mazeTxt) {
        if (mazeTxt.size() < 3 || mazeTxt.get(0).length() < 3) return false;
        int rowLen = mazeTxt.get(0).length();
        for (String row : mazeTxt) {
            if (row.length() != rowLen) return false;
            for (char c : row.toCharArray()) {
                if (c != '#' && c != ' ') return false;
            }
        }
        return true;
    }

    public static int[][] locateEntryPoints(boolean[][] maze) throws IllegalArgumentException {
        int[][] entryPoints = new int[2][2];
        int numOfEntries = 0;
        int lastRowIndex = maze.length - 1;
        int lastColIndex = maze[0].length - 1;
        for (int i = 0; i < maze[0].length; i++) {
            if (!maze[0][i]) {
                entryPoints[numOfEntries] = new int[] {0, i};
                numOfEntries++;
            }
        }
        for (int i = 0; i < maze[lastRowIndex].length; i++) {
            if (!maze[lastRowIndex][i]) {
                entryPoints[numOfEntries] = new int[] {lastRowIndex, i};
                numOfEntries++;
            }
        }
        try {
            for (int i = 1; i < lastRowIndex; i++) {
                if (!maze[i][0]) {
                    entryPoints[numOfEntries] = new int[] {i, 0};
                    numOfEntries++;
                }
                if (!maze[i][lastColIndex]) {
                    entryPoints[numOfEntries] = new int[] {i, lastColIndex};
                    numOfEntries++;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            logError(defaultError);
            throw defaultError;
        }
        logger.info("Entrance: (" + entryPoints[0][0] + ", " + entryPoints[0][1] + ")");
        logger.info("Exit: (" + entryPoints[1][0] + ", " + entryPoints[1][1] + ")");
        return entryPoints;
    }
}
