package in.nucleusteq.plasma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.LeaveBank;

/**
 * Repository interface for managing leave bank.
 */
public interface LeaveBankRepository extends JpaRepository<LeaveBank, Integer> {
    /**
     * Retrieves a list of employees by their employee IDs.
     * @param employeeId employee's employeeId.
     * @return A list of employees with the specified user employee IDs.
     */
     List<LeaveBank> findByEmployeeId(String employeeId);
}
