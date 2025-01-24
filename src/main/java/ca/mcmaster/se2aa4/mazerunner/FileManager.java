package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileManager {
    private static final Logger logger = LogManager.getLogger();
    
    public boolean validateFile(String filePath) {
        logger.info("**** Validating file: " + filePath);

        // Placeholder: Assume file is always valid
        logger.info("**** File validated successfully: " + filePath);
        return true;
    }
}
