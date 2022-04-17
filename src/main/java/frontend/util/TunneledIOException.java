package frontend.util;

/**
 * An exception class thrown to tunnel io exceptions.
 * The tunneled exception should generally not be recoverable.
 * All TunneledIOExceptions should force the application to exit.
 * <br><br>
 * The tunneled exception can be retrieved as
 * <pre>
 * tunneledIOExceptionInstance.getCause();
 * </pre>
 */
public class TunneledIOException extends RuntimeException {

    /**
     * Constructs a new TunneledIOException wrapping the passed exception.
     * @param tunneledException the exception that should be tunneled.
     */
    public TunneledIOException(Exception tunneledException) {
        super(tunneledException);
    }

}
