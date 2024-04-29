package in.nucleusteq.plasma.dto.out;

import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Employee Login Out DTO.
 */
@Setter
@Getter
public class LoginOutDTO {
    /**
     * The user's email address.
     */
    private String email;
    /**
     * Employee Role.
     */
    private String role;
    /**
     * message.
     */
    private String message;
}
