package in.nucleusteq.plasma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.nucleusteq.plasma.entity.TimesheetData;

/**
 * Repository interface for managing timsheet data.
 */
public interface TimesheetDataRepository extends JpaRepository<TimesheetData, Long> {
    /**
     * Retrieves a list of timesheetData by their rimesheet ID.
     * @param timesheetId The Id of timesheetId.
     * @return A list of timesheet data with the specified timsheet ID.
     */
    List<TimesheetData> findByTimesheetTimesheetId(Long timesheetId);
    /**
     * Calculates the sum of task hours for a given timesheet ID.
     * @param timesheetId The ID of the timesheet.
     * @return The sum of task hours for the specified timesheet ID.
     */
    @Query("SELECT COALESCE(SUM(td.taskHours), 0) FROM TimesheetData td WHERE td.timesheet.id = :timesheetId")
    int sumTaskHoursByTimesheetId(@Param("timesheetId") Long timesheetId);
}
