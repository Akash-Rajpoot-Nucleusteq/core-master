package in.nucleusteq.plasma.dto.out;

import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a User Details Out DTO.
 */
@Getter
@Setter
public class UserDetailsOutDTO {
    /**
     * User's Name.
     */
    private String name;
    /**
     * User's Employee's Id.
     */
    private String employeeId;
}
