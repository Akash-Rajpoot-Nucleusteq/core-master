package in.nucleusteq.plasma.dto.in.leave;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.nucleusteq.plasma.enums.FullOrHalfDay;
import in.nucleusteq.plasma.enums.LeaveStatus;
import in.nucleusteq.plasma.enums.LeaveType;
import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Leave details In DTO.
 */
@Getter
@Setter
public class LeaveDetailsInDTO {
    /**
     * Leave Request Id.
     */
    private Long leaveRequestId;
    /**
     * Leave Start date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * Leave End date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
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
    /**
     * Full day Or Half day.
     */
    private FullOrHalfDay fullOrHalfDay;
    /**
     * employeeId.
     */
    private String employeeId;
    /**
     * Employee's managerId.
     */
    private String managerId;
}
