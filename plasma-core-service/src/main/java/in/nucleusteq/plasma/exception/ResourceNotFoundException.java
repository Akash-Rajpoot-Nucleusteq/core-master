package in.nucleusteq.plasma.exception;
/**
 * Exception indicating a conflict due to a resource not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a new ResourceNotFoundException with the specified error
     * message.
     * @param message The error message.
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
