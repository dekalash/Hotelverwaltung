package frontend.util;

/**
 * Checked exception used to handle exceptions thrown by backend interactions.
 * <br><br>
 * All occuring BackendExceptions should be handled by BackendExceptionHandler.
 * Code that may throw a BackendException should be "wrapped" by
 * {@link BackendExceptionHandler#execute(BackendAction)}
 *  or
 * {@link BackendExceptionHandler#executeReturnNullIfException(BackendSupplier)} .
 */
public class BackendException extends Exception {

    /**
     * Constructs a new BackendException wrapping the passed exception.
     * @param backendException the exception that was thrown by interacting with the backend
     */
    public BackendException(Exception backendException) {
        super(backendException);
    }
}
