package in.nucleusteq.plasma.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a TimeSheet Data.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "timesheet_data")
@JsonIgnoreProperties("timesheet")
public class TimesheetData {
/**
 * timesheetDataId.
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timesheet_data_id")
    private Long timesheetDataId;
    /**
     * timesheetDate.
     */
    @Column(name = "timesheet_date")
    private Date timesheetDate;
    /**
     * taskHours.
     */
    @Column(name = "task_hours")
    private int taskHours;
    /**
     * leaveHours.
     */
    @Column(name = "leave_hours")
    private int leaveHours;
    /**
     * timesheet.
     */
    @ManyToOne
    @JoinColumn(name = "timesheet_id")
    private Timesheet timesheet;
}
