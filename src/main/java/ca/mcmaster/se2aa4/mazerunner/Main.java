package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/

public class Main {
    //private static final Logger logger = LogManager.getLogger();

    @SuppressWarnings("UseSpecificCatch")
    public static void main(String[] args) throws ParseException {
        //logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", true, "Path to the input maze file");
        options.addOption("p", true, "Solution path to be checked");
        CommandLineParser parser = new DefaultParser();
        String inputFilePath = "";
        String testPath = "";

        // Parse command-line arguments
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("i")) {
            inputFilePath = cmd.getOptionValue("i");
            if (inputFilePath == null) {
                throw new MissingArgumentException("No input file specified.");
            }
            //logger.info("**** Input file specified: " + inputFilePath);

        } else {
            //logger.error("No input file specified. Use the -i flag to specify the maze file.");
            throw new MissingOptionException("Mandatory flag \"-i\" not used.");
        }
        if (cmd.hasOption("p")) {
            testPath = cmd.getOptionValue("p");
            if (testPath == null) {
                throw new MissingArgumentException("No solution path given.");
            }
            //logger.info("**** Solution path to be checked: " + testPath);
        }

        // Step 1: Load the maze
        //Maze maze = MazeReader.readMazeFromFile(inputFilePath);
        Maze maze = MazeReader.readMazeFromFile(inputFilePath);
        //logger.info("Loaded Maze:");
        //logger.info(maze.toString());

        // Step 2: Solve the maze using MazeSolver.solve()
        if (testPath.equals("")) {
            String solutionPath = MazeSolver.solve(maze);
            if (solutionPath != null) {
                //logger.info("Solution found: " + MazeSolver.factorizePath(solutionPath));
                System.out.println(MazeSolver.factorizePath(solutionPath));
            } else {
                //logger.error("No solution found for the maze.");
                throw new IllegalArgumentException("No solution found for the maze.");
            }
        } else {
            boolean correctSol;
            try {
                correctSol = MazeSolver.verify(maze, MazeSolver.expandPath(testPath));
            } catch (Exception e) {
                correctSol = false;
            }
            if (correctSol) {
                System.out.println("correct path");
            } else {
                System.out.println("incorrect path");
            }
        }
        //logger.info("** End of Maze Runner");
    }
}
