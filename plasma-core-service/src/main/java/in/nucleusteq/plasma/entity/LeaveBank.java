package in.nucleusteq.plasma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Leave Bank.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leave_bank")
public class LeaveBank {
/**
 * leaveId.
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    /**
     * totalSickLeave.
     */
    @Column(name = "total_sick_leave")
    private Long totalSickLeave;
    /**
     * remainingSickLeave.
     */
    @Column(name = "remaining_sick_leave")
    private Long remainingSickLeave;
    /**
     * totalPaidleave.
     */
    @Column(name = "total_paid_leave")
    private double totalPaidLeave;
    /**
     * remainingPaidLeave.
     */
    @Column(name = "remaining_paid_leave")
    private double remainingPaidLeave;
    /**
     * employeeId.
     */
    @Column(name = "employee_id")
    private String employeeId;
}
