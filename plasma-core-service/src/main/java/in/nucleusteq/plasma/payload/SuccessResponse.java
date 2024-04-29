package in.nucleusteq.plasma.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse {
/**
     * message.
     */
    private String message;
    /**
     * status.
     */
     private int status;
     /**
      * SuccessResponse.
      * @param message
      * @param status
      */
     public SuccessResponse(final String message, final int status) {
        this.message = message;
        this.status = status;
      }
}
