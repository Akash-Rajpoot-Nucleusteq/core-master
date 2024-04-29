package in.nucleusteq.plasma.exception.responce;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class APIErrorResponse {
    /**
     * status.
     */
    private HttpStatus status;
    /**
     * timestamp.
     */
    private LocalDateTime timestamp;
    /**
     * message.
     */
    private String message;
    /**
     * debug Message.
     */
    private String debugMessage;
    /**
     * status Number.
     */
    private int statusNumber;
    /**
     * Constructs a new APIErrorResponse object with the specified message and details.
     * @param message the error message.
     * @param details the detailed description of the error.
     */
    public APIErrorResponse(String message, String details) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
    /**
     * Constructs a new APIErrorResponse object with the specified HTTP status.
     * @param status the HTTP status of the error.
     */
    public APIErrorResponse(HttpStatus status) {
        this.status = status;
    }
    /**
     * Constructs a new APIErrorResponse object with the specified HTTP status and exception.
     * @param status the HTTP status of the error.
     * @param ex     the exception that occurred.
     */
    public APIErrorResponse(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }
    /**
     * Constructs a new APIErrorResponse object with the specified HTTP status, message, and exception.
     * @param status  the HTTP status of the error.
     * @param message the error message.
     * @param ex      the exception that occurred.
     */
    public APIErrorResponse(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
