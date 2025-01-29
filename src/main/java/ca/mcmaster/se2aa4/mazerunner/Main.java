package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    @SuppressWarnings("UseSpecificCatch")
    public static void main(String[] args) {
        /* 
        logger.info("** Starting Maze Runner");
       
        Options options = new Options();
        options.addOption("i", true, "Path to the input maze file");

        CommandLineParser parser = new DefaultParser();
        try {
            // Parse command-line arguments
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String inputFilePath = cmd.getOptionValue("i");
                logger.info("**** Input file specified: " + inputFilePath);

                // Read and process the maze file
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        for (int idx = 0; idx < line.length(); idx++) {
                            if (line.charAt(idx) == '#') {
                                logger.info("WALL ");
                            } else if (line.charAt(idx) == ' ') {
                                logger.info("PASS ");
                            }
                        }
                        logger.info(System.lineSeparator());
                    }
                } catch (Exception e) {
                    logger.error("/!\\\\ An error has occured /!\\\\" + e.getMessage());
                }


            } else {
                logger.error("No input file specified. Use the -i flag to specify the maze file.");
            }
        } catch (Exception e) {
            logger.error("/!\\\\ An error has occured /!\\\\" + e.getMessage());
        }
        logger.info("** End of Maze Runner");
        */

        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", true, "Path to the input maze file");
        CommandLineParser parser = new DefaultParser();
        String inputFilePath = "";

        try {
            // Parse command-line arguments
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                inputFilePath = cmd.getOptionValue("i");
                logger.info("**** Input file specified: " + inputFilePath);

            } else {
                logger.error("No input file specified. Use the -i flag to specify the maze file.");
            }
        } catch (Exception e) {
            logger.error("/!\\\\ An error has occured /!\\\\" + e.getMessage());
        }

        // Step 1: Load the maze
        Maze maze = MazeReader.readMazeFromFile(inputFilePath);
        logger.info("Loaded Maze:");
        logger.info(maze.toString());

        // Step 2: Solve the maze using MazeSolver.solve()
        String solutionPath = MazeSolver.solve(maze);
        if (solutionPath != null) {
            logger.info("Solution found: " + solutionPath);
        } else {
            logger.error("No solution found for the maze.");
        }

        // Step 3: Test MazeExplorer manually
        MazeExplorer explorer = maze.getExplorer();
        logger.info("Starting manual exploration...");

        // Manually control the explorer
        explorer.explore(); // Automatic exploration with default max steps
        logger.info("Explorer's path: " + explorer.getPath());
        logger.info("Final position: " + java.util.Arrays.toString(explorer.getPos()));

        // Verify the solution using the explorer
        boolean isSolutionValid = MazeSolver.verify(maze, explorer.getPath());
        logger.info("Is the explorer's path valid? " + isSolutionValid);

        logger.info("** End of Maze Runner");
    }
}