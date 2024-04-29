package in.nucleusteq.plasma.exception;
/**
 * Exception indicating a conflict due to a timesheet not found.
 */
public class TimesheetNotFoundException extends RuntimeException {
/**
 * serialVersion.
 */
   private static final long serialVersionUID = 1L;
  /**
   * TimesheetNotFoundException.
   * @param message
   */
    public TimesheetNotFoundException(final String message) {
        super(message);
    }
}
