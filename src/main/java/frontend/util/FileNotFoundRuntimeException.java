package frontend.util;

/**
 * An exception class serving as unchecked alternative to FileNotFoundException.
 * Does not extend IOException (because IOException is a checked exception).
 */
public class FileNotFoundRuntimeException extends RuntimeException {
    private static final String NOT_FOUND = " could not be found";

    /**
     * Constructs a new FileNotFoundRuntimeException with the message
     * "{filePath} could not be found"
     * @param filePath the path to the file that could not be found
     */
    FileNotFoundRuntimeException(String filePath) {
        super(filePath + NOT_FOUND);
    }

}
