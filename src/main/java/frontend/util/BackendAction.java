package frontend.util;

/**
 * functional interface () -> void for code that may throw BackendExceptions
 */
public interface BackendAction {
    /**
     * The abstract method of this functional interface.
     * @throws BackendException
     */
    public abstract void execute() throws BackendException;
}
