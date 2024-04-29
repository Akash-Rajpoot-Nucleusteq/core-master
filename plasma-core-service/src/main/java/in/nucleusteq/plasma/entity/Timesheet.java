package in.nucleusteq.plasma.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import in.nucleusteq.plasma.enums.TimesheetStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Timesheet.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "timesheet")
@JsonIgnoreProperties("timesheetDataList")
public class Timesheet {
/**
 * timesheetId.
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timesheet_id")
    private Long timesheetId;
    /**
     * startDate.
     */
    @Column(name = "start_date")
    private Date startDate;
    /**
     * endDate.
     */
    @Column(name = "end_date")
    private Date endDate;
    /**
     * TimesheetStatus.
     */
    @Column(name = "status")
    private TimesheetStatus status;
    /**
     * employeeId.
     */
    @Column(name = "employee_id")
    private String employeeId;
    /**
     * timesheetDataList.
     */
    @OneToMany(mappedBy = "timesheet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TimesheetData> timesheetDataList;
    /**
     * managerId.
     */
    @Column(name = "manager_id")
    private String managerId;
//private Long assignment_id;
}
