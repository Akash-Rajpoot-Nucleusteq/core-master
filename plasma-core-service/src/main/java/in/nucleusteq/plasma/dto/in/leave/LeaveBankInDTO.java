package in.nucleusteq.plasma.dto.in.leave;

import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a leave bank In DTO.
 */
@Setter
@Getter
public class LeaveBankInDTO {
    /**
     * Total Sick Leave.
     */
    private Long totalSickLeave;
    /**
     * Remaining Sick Leave.
     */
    private Long remainingSickLeave;
    /**
     * Total Paid Leave.
     */
    private double totalPaidLeave;
    /**
     * Remaining Paid Leave.
     */
    private double remainingPaidLeave;
    /**
     * Employee Id.
     */
    private String employeeId;
}
