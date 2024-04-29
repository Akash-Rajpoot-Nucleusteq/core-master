package in.nucleusteq.plasma.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import in.nucleusteq.plasma.dto.in.TimesheetInDTO;
import in.nucleusteq.plasma.dto.out.ManagerTimesheetOutDTO;
import in.nucleusteq.plasma.dto.out.TimesheetDataOutDTO;
import in.nucleusteq.plasma.entity.Timesheet;
import in.nucleusteq.plasma.entity.TimesheetData;
import in.nucleusteq.plasma.payload.SuccessResponse;

/**
 * Service interface of timesheet.
 */
public interface TimesheetService {
    /**
     * Create Timesheet.
     * @param timesheetDTO
     * @return SuccessResponse
     */
    public SuccessResponse createTimesheet(TimesheetInDTO timesheetDTO);
    /**
     * Submit Timesheet.
     * @param timesheetId
     * @param dateAndHoursMap
     * @return SuccessResponse
     */
    public SuccessResponse submitTimesheet(Long timesheetId, Map<Date, Map<String, Integer>> dateAndHoursMap);
    /**
     * get Timesheet Data By EmployeeId.
     * @param employeeId
     * @return map of all the timesheet data
     */
    Map<Long, List<TimesheetData>> getTimesheetDataByEmployeeId(String employeeId);
    /**
     * accept Timesheet.
     * @param timesheetId
     * @return SuccessResponse
     */
    SuccessResponse acceptTimesheet(Long timesheetId);
    /**
     * reject timesheet.
     * @param timesheetId
     * @return SuccessResponse
     */
    SuccessResponse rejctTimesheet(Long timesheetId);
    /**
     * get Approved Timesheet.
     * @return list of timehseet
     */
    List<Timesheet> getApprovedTimesheets();
    /**
     * get timesheet data by timesheetId.
     * @param timesheetId
     * @return map of timehseet data
     */
    Map<Date, TimesheetDataOutDTO> getTimesheetDataByTimesheetId(Long timesheetId);
    /**
     * get Manager's employee timesheet.
     * @param employeeId
     * @return list of manager timesheet out dto
     */
    List<ManagerTimesheetOutDTO> getManagerEmpTimesheet(String employeeId);
}
