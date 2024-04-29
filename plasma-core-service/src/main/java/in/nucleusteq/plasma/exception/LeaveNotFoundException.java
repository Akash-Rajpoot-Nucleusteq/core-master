package in.nucleusteq.plasma.exception;
/**
 * Exception indicating a conflict due to a Leave Not Found.
 */
public class LeaveNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a new LeaveNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public LeaveNotFoundException(String message) {
        super(message);
    }
}
