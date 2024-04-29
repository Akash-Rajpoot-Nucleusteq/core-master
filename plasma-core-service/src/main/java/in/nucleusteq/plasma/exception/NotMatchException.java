package in.nucleusteq.plasma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception indicating a conflict due to a not match exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class NotMatchException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a new NotMatchException with the specified detail message.
     *
     * @param message the detail message.
     */
    public NotMatchException(String message) {
        super(message);
    }
}
