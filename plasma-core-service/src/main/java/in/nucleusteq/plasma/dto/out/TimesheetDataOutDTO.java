package in.nucleusteq.plasma.dto.out;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.nucleusteq.plasma.entity.Timesheet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Timesheet data Out DTO.
 */
@Getter
@Setter
@AllArgsConstructor
public class TimesheetDataOutDTO {
    /**
     * Timesheet Date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date timesheetDate;
    /**
     * Task Hour.
     */
    private int taskHour;
    /**
     * Leave Hour.
     */
    private int leaveHour;
    /**
     * Timesheet Id.
     */
    private Timesheet timesheet;
}
