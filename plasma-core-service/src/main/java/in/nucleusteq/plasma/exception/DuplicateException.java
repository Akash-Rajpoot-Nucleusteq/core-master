package in.nucleusteq.plasma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception indicating a conflict due to a duplicate resource.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a new DuplicateException with the specified detail message.
     *
     * @param message the detail message.
     */
    public DuplicateException(String message) {
        super(message);
    }
}
