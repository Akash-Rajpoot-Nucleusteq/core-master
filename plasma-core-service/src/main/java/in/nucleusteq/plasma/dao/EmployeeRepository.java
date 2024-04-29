package in.nucleusteq.plasma.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import in.nucleusteq.plasma.entity.Employee;

/**
 * Repository interface for managing Employee.
 */
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    /**
     * Retrieves the last user ID.
     * @return The last user ID.
     */
    @Query("SELECT MAX(u.userId) FROM Employee u")
    String findLastUserId();
    /**
     * Retrieves a list of employees by their user work detail IDs.
     * @param userWorkIds The list of user work detail IDs.
     * @return A list of employees with the specified user work detail IDs.
     */
    List<Employee> findByUserWorkDetailIdIn(List<Integer> userWorkIds);
    /**
     * Retrieves an employee by their user ID.
     * @param employeeId The user ID of the employee.
     * @return An optional containing the employee with the specified user ID, or empty if not found.
     */
    Optional<Employee> findByUserId(String employeeId);
    /**
     * Retrieves an employee by their email.
     * @param userName The email of the employee.
     * @return The employee with the specified email.
     */
    Employee getByEmail(String userName);
}
