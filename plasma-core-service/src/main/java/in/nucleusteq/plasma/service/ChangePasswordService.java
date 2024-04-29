package in.nucleusteq.plasma.service;

import in.nucleusteq.plasma.dto.in.user.PasswordReset;
import in.nucleusteq.plasma.payload.SuccessResponse;
/**
 * Service interface of user change password.
 */
public interface ChangePasswordService {
    /**
     * change Password.
     * @param passwordReset
     * @return SuccessResponse
     */
    SuccessResponse changePassword(final PasswordReset passwordReset);
}
