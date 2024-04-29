package in.nucleusteq.plasma.entity;

import java.util.Date;
import in.nucleusteq.plasma.enums.FullOrHalfDay;
import in.nucleusteq.plasma.enums.LeaveStatus;
import in.nucleusteq.plasma.enums.LeaveType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Leave Details.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leave_details")
public class LeaveDetails {
    /**
     * Leave Request ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveRequestId;
    /**
     * Leave Start Date.
     */
    @Column(name = "start_date")
    private Date startDate;
    /**
     * Leave End Date.
     */
    @Column(name = "end_date")
    private Date endDate;
    /**
     * Leave Type.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type")
    private LeaveType leaveType;
    /**
     * Leave Status.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LeaveStatus leaveStatus;
    /**
     * Reason.
     */
    @Column(name = "reason")
    private String reason;
    /**
     * Full Or Half Day.
     */
    @Column(name = "full_or_half_day")
    private FullOrHalfDay fullOrHalfDay;
    /**
     * EmployeeId.
     */
    @Column(name = "employee_id")
    private String employeeId;
    /**
     * Manager ID.
     */
    @Column(name = "manager_id")
    private String managerId;
}
