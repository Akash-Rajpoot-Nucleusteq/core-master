package in.nucleusteq.plasma.dto.out;

import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Employee Leave Info Out DTO.
 */
@Getter
@Setter
public class LeaveInfoOutDTO {
    /**
     * Total leave.
     */
    private double totalLeave;
    /**
     * Remaining Leaves.
     */
    private double remainingLeaves;
    /**
     * Consumed Leave.
     */
    private double consumedLeaves;
}
