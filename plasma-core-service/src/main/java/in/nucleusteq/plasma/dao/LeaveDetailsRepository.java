package in.nucleusteq.plasma.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.LeaveDetails;

/**
 * Repository interface for managing leave details.
 */
public interface LeaveDetailsRepository extends JpaRepository<LeaveDetails, Integer> {
    /**
     * Retrieves a list of employees by their employee IDs.
     * @param employeeId The employee's employeeId.
     * @return A list of leave Details with the specified employee ID.
     */
    List<LeaveDetails> findByEmployeeId(String employeeId);
    /**
     * Retrieves a list of manager by their employee IDs.
     * @param managerId The employee's employeeId.
     * @return A list of leave Details with the specified employee ID.
     */
    List<LeaveDetails> findByManagerId(String managerId);
    /**
     * Retrieves a leave detail by their leave Id.
     * @param leaveId The leave id.
     * @return An optional containing the leave details with the specified leave ID, or empty if not found.
     */
    Optional<LeaveDetails> findByLeaveRequestId(Long leaveId);
}
