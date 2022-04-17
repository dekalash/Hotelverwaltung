package frontend.util;
/**
 *  functional interface () -> T for code that may throw BackendExceptions
 */

public interface BackendSupplier<T> {
    /**
     * The abstract method of this functional interface.
     * @return an instance of T (or null)
     * @throws BackendException
     */
    public abstract T get() throws BackendException; 
}
