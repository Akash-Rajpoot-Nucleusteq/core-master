package in.nucleusteq.plasma.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import in.nucleusteq.plasma.exception.DuplicateException;
import in.nucleusteq.plasma.exception.NotFoundException;
import in.nucleusteq.plasma.exception.NotMatchException;
import in.nucleusteq.plasma.exception.responce.APIErrorResponse;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.nucleusteq.plasma.exception.ResourceNotFoundException;
import in.nucleusteq.plasma.exception.UnauthorizedAccessException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;


/**
 * GlobalExceptionHandler class handles exceptions globally and provides appropriate responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * Handles NotFoundException and returns an appropriate response.
     * @param ex the NotFoundException instance
     * @return ResponseEntity containing APIErrorResponse
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleNotFoundException(NotFoundException ex) {
        LOGGER.error("Resource not found: {}", ex.getMessage());
        APIErrorResponse apiError = new APIErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    /**
     * Handles DuplicateException and returns an appropriate response.
     * @param ex the DuplicateException instance
     * @return ResponseEntity containing APIErrorResponse
     */
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<APIErrorResponse> handleDuplicateException(DuplicateException ex) {
        LOGGER.error("Duplicate resource: {}", ex.getMessage());
        APIErrorResponse apiError = new APIErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), ex);
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
    /**
     * Handles NotMatchException and returns an appropriate response.
     * @param ex the NotMatchException instance
     * @return ResponseEntity containing APIErrorResponse
     */
    @ExceptionHandler(NotMatchException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final ResponseEntity<APIErrorResponse> handleNotMatchException(NotMatchException ex) {
        LOGGER.error("Conflict in match entity" + ex.getMessage());
        APIErrorResponse errorResponse =  new APIErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    /**
     * Handles UnauthorizedAccessException and returns an appropriate response.
     * @param exception the UnauthorizedAccessException instance
     * @return ResponseEntity containing APIErrorResponse
     */
    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ResponseEntity<APIErrorResponse> handleUnauthorizedAccessException(
            final UnauthorizedAccessException exception) {
        LOGGER.error("Unauthorized access: {}", exception.getMessage());
        APIErrorResponse errorResponse = new APIErrorResponse(exception.getMessage(), exception.getStackTrace().toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    /**
     * Handles ResourceNotFoundException and returns an appropriate response.
     * @param exception the ResourceNotFoundException instance
     * @return ResponseEntity containing APIErrorResponse
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity<APIErrorResponse> handleResourceNotFound(
            final ResourceNotFoundException exception) {
        LOGGER.error("Resource not found: {}", exception.getMessage());
        APIErrorResponse errorResponse = new APIErrorResponse(
                "Not Found", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    /**
     * Handles SpelEvaluationException and returns an appropriate response.
     * @param exception the SpelEvaluationException instance
     * @return ResponseEntity containing APIErrorResponse
     */
    @ExceptionHandler(SpelEvaluationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ResponseEntity<APIErrorResponse> spelEvaluationException(final SpelEvaluationException exception) {
        LOGGER.error("SpEL Evaluation Exception: {}", exception.getMessage());
        APIErrorResponse errorResponse = new APIErrorResponse("Access denied",
                "Insufficient privilege to access this resource");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    /**
     * Handles MalformedJwtException and returns an appropriate response.
     * @param exception the MalformedJwtException instance
     * @return ResponseEntity containing APIErrorResponse
     */
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ResponseEntity<APIErrorResponse> malformedJwtExceptionException(final MalformedJwtException exception) {
        LOGGER.error("Malformed JWT Exception: {}", exception.getMessage());
        APIErrorResponse errorResponse = new APIErrorResponse("Access denied", "Tempered token");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    /**
     * Handles ExpiredJwtException and returns an appropriate response.
     * @param exception the ExpiredJwtException instance
     * @return ResponseEntity containing APIErrorResponse
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ResponseEntity<APIErrorResponse> expiredJwtException(final ExpiredJwtException exception) {
        LOGGER.error("Expired JWT Exception: {}", exception.getMessage());
        APIErrorResponse errorResponse = new APIErrorResponse("Access denied", "Token is expired.");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
