package in.nucleusteq.plasma.dto.in;

import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Login In Details.
 */
@Getter
@Setter
public class LoginInDTO {
  /**
  * The user's email.
  */
  private String email;
  /**
   * The user's password.
   */
  private String password;
}
