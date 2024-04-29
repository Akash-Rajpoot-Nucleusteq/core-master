package in.nucleusteq.plasma.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.nucleusteq.plasma.dao.EmployeeRepository;
import in.nucleusteq.plasma.dto.in.user.PasswordReset;
import in.nucleusteq.plasma.exception.NotFoundException;
import in.nucleusteq.plasma.exception.NotMatchException;
import in.nucleusteq.plasma.entity.Employee;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.service.ChangePasswordService;
/**
 * Implementation of the Change Password.
 */
@Service
public class ChangePasswordServiceImplementation
        implements
            ChangePasswordService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordServiceImplementation.class);
    /**
     * Employee Repository.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * Change Password.
     * @param passwordReset
     * @return SuccessResponse
     */
    public SuccessResponse changePassword(final PasswordReset passwordReset) {
        Employee employee = employeeRepository.findById(passwordReset.getEmployeeId())
                .orElseThrow(() -> {
                    LOGGER.error("User not found with ID: {}", passwordReset.getEmployeeId());
                    return new NotFoundException("User not found with ID: " + passwordReset.getEmployeeId());
                });

        if (passwordReset.getOldPassword() != employee.getPassword()) {
            LOGGER.error("Your Current Password is not correct: {}", passwordReset.getOldPassword());
            throw new NotMatchException("Your Current Password is not correct: " + passwordReset.getOldPassword());
        }
        employee.setPassword(passwordReset.getNewPassword());
        employeeRepository.save(employee);

        return new SuccessResponse("Password Change Successfully", HttpStatus.ACCEPTED.value());
    }
}
