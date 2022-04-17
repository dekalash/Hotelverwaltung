package frontend.util;

/**
 * Contains various methods for throwing exceptions.
 * So not exactly a factory in the usual sense, but quite similar.
 */
public class ExceptionFactory {
    public static final String MAY_NOT_BE_NULL = " may not be null"; //leading whitespace

    /**
    * @param object the object that should not be null
    * @param message the exception message
    * @throws NullPointerException "{message}" if the passed object is null
    */
    public static void ensureNotNullMessage(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    /**
     * @param object the object that should not be null
     * @param parameterName the name by which the object should be referenced in the exception message
     * @throws NullPointerException "{parameterName} may not be null" if the passed object is null
     */
    public static void ensureNotNullNamed(Object object, String parameterName) {
        ensureNotNullMessage(object, parameterName + MAY_NOT_BE_NULL);
    }

    /**
     * @param file file name as string that should not be null
     * @throws NullPointerException "file {file} may not be null" if the file is null
     */
    public static void ensureFileNotNull(String file) {
        ensureNotNullNamed(file, "file " + file + " ");
    }
}
