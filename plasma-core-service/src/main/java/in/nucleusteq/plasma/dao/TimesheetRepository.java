package in.nucleusteq.plasma.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import in.nucleusteq.plasma.enums.TimesheetStatus;
import in.nucleusteq.plasma.entity.Timesheet;

/**
 * Repository interface for managing timesheet.
 */
@EnableJpaRepositories
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    /**
     * Retrieves a list of timesheet by employee ID.
     * @param employeeId employee's employeeId.
     * @return A list of timesheet with the specified employeeId.
     */
    List<Timesheet> findByEmployeeId(String employeeId);
    /**
     * Retrieves a Timesheet by their timesheet ID.
     * @param timesheetId The timesheet ID of the timesheet.
     * @return An optional containing the timesheet with the specified timesheetId, or empty if not found.
     */
    Optional<Timesheet> findByTimesheetId(Long timesheetId);
    /**
     * Retrieves a list of timesheet by timesheet's status.
     * @param APPROVED timesheetStatus.
     * @return A list of timesheet with the specified timesheet status approved.
     */
    List<Timesheet> findByStatus(TimesheetStatus APPROVED);
    /**
     * Retrieves a list of timesheet by managersId.
     * @param employeeId Manager's employeeId.
     * @return A list of timesheet with the specified employeeId.
     */
    List<Timesheet> findByManagerId(String employeeId);
}
