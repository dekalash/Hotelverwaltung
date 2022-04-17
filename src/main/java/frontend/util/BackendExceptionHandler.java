package frontend.util;

public class BackendExceptionHandler {

    /**
     * Notifies the user about an exception that occured while interacting with the backend
     * (reading from or writing to the backend).
     * @param backendException the exception that was thrown by the request
     */
    public static void handleBackendException(BackendException backendException) {
        Box.log(backendException.getCause(), "BackendExceptionHandler.handleBackendException");
        //TODO show proper error message
        FxHelper.displayPopUp("Error_PopUp", () -> {
        });
    }

    /**
     * Executes the code passed as action.
     * <br><br>
     * Used for code that does <i>not</i> return a value.
     * <br><br>
     * If an exception is thrown by the code, it is caught and 
     * handled by {@link #handleBackendException(Exception)}.
     * @param backendCode code interacting with the backend that may throw an exception
     */
    public static void execute(BackendAction backendCode) {
        try {
            backendCode.execute();
        } catch (BackendException backendException) {
            handleBackendException(backendException);
        }
    }

    /**
     * Executes the code returning an instance of T.
     * <br><br>
     * Used for code that does return a value.
     * <br><br>
     * If an exception is thrown by the code, it is caught and 
     * handled by {@link #handleBackendException(Exception)}
     * and null is returned by this method.     * 
     * @param <T> the return type of the code that should be executed
     * @param backendCode code interacting with the backend that may throw an exception
     * @return
     * <ul>
     *      <li>null - if the code throws an exception</li>
     *      <li>the result of executing the code - otherwise</li>
     * </ul> 
     */
    public static <T> T executeReturnNullIfException(BackendSupplier<T> backendCode) {
        try {
            return backendCode.get();
        } catch (BackendException backendException) {
            handleBackendException(backendException);
            return null;
        }
    }
}
