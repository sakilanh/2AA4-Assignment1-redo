package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
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
    }
}