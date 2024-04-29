package in.nucleusteq.plasma.dto.out;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Manager's Teams Timesheet Out DTO.
 */
@Getter
@Setter
public class ManagerTimesheetOutDTO {
    /**
     * Assigne name.
     */
    private String assigneName;
    /**
     * Weed date.
     */
    private Date weekDate;
    /**
     * Hours Submitted.
     */
    private int hourSubmitted;
//private String clientName;
//private String bussinessUnit;
//private String project;
//private String task;
}
