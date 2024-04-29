package in.nucleusteq.plasma.dto.in.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import in.nucleusteq.plasma.constants.UserConstants;
import jakarta.validation.constraints.NotBlank;
/**
 * Data transfer object (DTO) for representing a Password Rest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordReset {
    /**
     * Employee Id.
     */
   @NotBlank
   private String employeeId;
   /**
    * Old password.
    */
   @NotBlank
   @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,20}$", message = "Password requirement must")
   @Size(min = UserConstants.MIN_PASSWORD, max = UserConstants.MAX_PASSWORD)
   private String oldPassword;
   /**
    * New Password.
    */
   @NotBlank
   @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,20}$", message = "Password requirement must")
   @Size(min = UserConstants.MIN_PASSWORD, max = UserConstants.MAX_PASSWORD)
   private String newPassword;
}
