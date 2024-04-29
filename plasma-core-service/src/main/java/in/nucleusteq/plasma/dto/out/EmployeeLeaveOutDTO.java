package in.nucleusteq.plasma.dto.out;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.nucleusteq.plasma.enums.LeaveStatus;
import in.nucleusteq.plasma.enums.LeaveType;
import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Employee Leave Out DTO.
 */
@Setter
@Getter
public class EmployeeLeaveOutDTO {
    /**
     * Leave Apply Date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyDate;
    /**
     * Leave Duration.
     */
    private double duration;
    /**
     * Leave Type.
     */
    private LeaveType leaveType;
    /**
     * Leave Status.
     */
    private LeaveStatus leaveStatus;
    /**
     * Leave Reason.
     */
    private String reason;
}
