package in.nucleusteq.plasma.dto.in;

import java.util.Date;

import in.nucleusteq.plasma.enums.TimesheetStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object (DTO) for representing a timesheet In Details.
 */
@Setter
@Getter
public class TimesheetInDTO {
    /**
     * Timesheet's startDate.
     */
    private Date startDate;
    /**
     * Timesheet's endDate.
     */
    private Date endDate;
    /**
     * Timesheet's status.
     */
    private TimesheetStatus status;
    /**
     * The user's employeeId.
     */
    private String employeeId;
    /**
     * The employee's managerId.
     */
    private String managerId;
}
