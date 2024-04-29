package in.nucleusteq.plasma.dto.out;

import java.util.Date;

import in.nucleusteq.plasma.enums.LeaveType;
import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Manger's Employee Leave Out DTO.
 */
@Getter
@Setter
public class ManagerEmpLeaveOutDTO {
    /**
     * Team Member's Name.
     */
    private String teamMember;
    /**
     * Leave Type.
     */
    private LeaveType leaveType;
    /**
     * Leave Start Date.
     */
    private Date startDate;
    /**
     * Leave End Date.
     */
    private Date enddate;
    /**
     * Leave Remaining Days.
     */
    private double remainingDays;
}
