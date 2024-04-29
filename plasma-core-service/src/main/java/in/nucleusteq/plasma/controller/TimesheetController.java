package in.nucleusteq.plasma.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.nucleusteq.plasma.dto.in.TimesheetInDTO;
import in.nucleusteq.plasma.dto.out.ManagerTimesheetOutDTO;
import in.nucleusteq.plasma.dto.out.TimesheetDataOutDTO;
import in.nucleusteq.plasma.entity.Timesheet;
import in.nucleusteq.plasma.entity.TimesheetData;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.service.TimesheetService;

/**
 * Controller class for managing timesheet-related operations.
 */
@RestController
@RequestMapping("/plasma/timesheet")
public class TimesheetController {
    /**
     * TimesheetService.
     */
    @Autowired
    private TimesheetService timesheetService;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TimesheetController.class);
    /**
     * Endpoint to create a new timesheet.
     * @param timesheetDTO The DTO containing information about the timesheet to be
     *                     created.
     * @return ResponseEntity containing a SuccessResponse indicating the result of
     *         the operation.
     */
    @PostMapping("/create")
    public ResponseEntity<SuccessResponse> createTimesheet(@RequestBody TimesheetInDTO timesheetDTO) {
        LOGGER.info("Creating timesheet entry");
        SuccessResponse successResponse = timesheetService.createTimesheet(timesheetDTO);
        LOGGER.info("Timesheet entry created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }
    /**
     * Endpoint to submit a timesheet.
     * @param timesheetId     The ID of the timesheet to be submitted.
     * @param dateAndHoursMap The map containing dates and hours worked.
     * @return ResponseEntity containing a SuccessResponse indicating the result of
     *         the operation.
     */
    @PostMapping("/submit/{timesheetId}")
    public ResponseEntity<SuccessResponse> submitTimesheet(@PathVariable("timesheetId") Long timesheetId,
            @RequestBody Map<Date, Map<String, Integer>> dateAndHoursMap) {
        LOGGER.info("Submitting timesheet with ID: {}", timesheetId);
        SuccessResponse successResponse = timesheetService.submitTimesheet(timesheetId, dateAndHoursMap);
        LOGGER.info("Timesheet submitted successfully");
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Retrieves the timesheet data for a specific employee.
     * @param employeeId The ID of the employee.
     * @return ResponseEntity containing a map of timesheet data keyed by timesheet
     *         ID.
     */
    @GetMapping("/show/{employeeId}")
    public ResponseEntity<Map<Long, List<TimesheetData>>> getEmployeeTimesheet(
            @PathVariable("employeeId") String employeeId) {
        LOGGER.info("Fetching timesheet data for employee with ID: {}", employeeId);
        Map<Long, List<TimesheetData>> timesheet = timesheetService.getTimesheetDataByEmployeeId(employeeId);
        return ResponseEntity.ok(timesheet);
    }
    /**
     * Accepts a timesheet.
     * @param timesheetId The ID of the timesheet to be accepted.
     * @return ResponseEntity containing a SuccessResponse indicating the result of
     *         the operation.
     */
    @PutMapping("/accept/{timesheetId}")
    public ResponseEntity<SuccessResponse> acceptTimesheet(@PathVariable("timesheetId") Long timesheetId) {
        LOGGER.info("Accepting timesheet with ID: {}", timesheetId);
        SuccessResponse successResponse = timesheetService.acceptTimesheet(timesheetId);
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Rejects a timesheet.
     * @param timesheetId The ID of the timesheet to be rejected.
     * @return ResponseEntity containing a SuccessResponse indicating the result of
     *         the operation.
     */
    @PutMapping("/reject/{timesheetId}")
    public ResponseEntity<SuccessResponse> rejectTimesheet(@PathVariable("timesheetId") Long timesheetId) {
        LOGGER.info("Rejecting timesheet with ID: {}", timesheetId);
        SuccessResponse successResponse = timesheetService.rejctTimesheet(timesheetId);
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Retrieves list of approved timesheer.
     * @return ResponseEntity containing a list of approvedTimesheet.
     */
    @GetMapping("/approved")
    public ResponseEntity<List<Timesheet>> getApprovedTimesheets() {
        LOGGER.info("Fetching approved timesheets");
        List<Timesheet> approvedTimesheets = timesheetService.getApprovedTimesheets();
        LOGGER.info("Fetched {} approved timesheets", approvedTimesheets.size());
        return new ResponseEntity<>(approvedTimesheets, HttpStatus.OK);
    }
    /**
     * Retrieves TimesheetData using the timesheetID.
     * @param timesheetId The ID of the timesheet to be rejected.
     * @return ResponseEntity containing a map of timesheetData details.
     */
    @GetMapping("/data/{timesheetId}")
    public ResponseEntity<Map<Date, TimesheetDataOutDTO>> getTimesheetDataByTimesheetId(
            @PathVariable("timesheetId") Long timesheetId) {
        LOGGER.info("Fetching timesheet data for ID: {}", timesheetId);
        Map<Date, TimesheetDataOutDTO> timesheetDataMap = timesheetService.getTimesheetDataByTimesheetId(timesheetId);
        LOGGER.info("Fetched timesheet data for ID: {}", timesheetId);
        return new ResponseEntity<>(timesheetDataMap, HttpStatus.OK);
    }
    /**
     * Retrieves the timesheet of managers team's submitted timesheet.
     * @param employeeId managers's employeeID
     * @return ResponseEntity containing a list of timesheet info.
     */
    @GetMapping("/pending/{managerId}")
    public ResponseEntity<List<ManagerTimesheetOutDTO>> getManagerEmpLeaves(
            @PathVariable("managerId") String employeeId) {
        LOGGER.info("Fetching pending timesheets for manager with ID: {}", employeeId);
        List<ManagerTimesheetOutDTO> timesheet = timesheetService.getManagerEmpTimesheet(employeeId);
        LOGGER.info("Fetched {} pending timesheets for manager with ID: {}", timesheet.size(), employeeId);
        return new ResponseEntity<>(timesheet, HttpStatus.OK);
    }
}
