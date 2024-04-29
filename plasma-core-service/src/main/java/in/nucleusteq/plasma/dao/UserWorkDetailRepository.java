package in.nucleusteq.plasma.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nucleusteq.plasma.entity.UserWorkDetail;
/**
 * Repository interface for managing User Work Details.
 */
public interface UserWorkDetailRepository
        extends
            JpaRepository<UserWorkDetail, Integer> {
    /**
     * Retrieves a list of user work detail IDs for employees whose
     *    employment start date falls within a specific range.
     * @param previousMonth The start date of the range.
     * @param currentMonth The end date of the range.
     * @return A list of user work detail IDs for employees with employment start dates between the specified dates.
     */
    @Query("select u.id from UserWorkDetail u where u.employmentStartDate between ?1 and ?2")
    List<Integer> findByEmploymentStartDateBetween(Date previousMonth,
            Date currentMonth);
}
