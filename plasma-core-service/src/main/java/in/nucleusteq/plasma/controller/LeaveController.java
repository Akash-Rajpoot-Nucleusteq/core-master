package in.nucleusteq.plasma.controller;

import in.nucleusteq.plasma.dto.in.leave.HolidayInDTO;
import in.nucleusteq.plasma.dto.in.leave.LeaveBankInDTO;
import in.nucleusteq.plasma.dto.in.leave.LeaveDetailsInDTO;
import in.nucleusteq.plasma.dto.out.EmployeeLeaveOutDTO;
import in.nucleusteq.plasma.dto.out.LeaveInfoOutDTO;
import in.nucleusteq.plasma.dto.out.ManagerEmpLeaveOutDTO;
import in.nucleusteq.plasma.entity.LeaveDetails;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.service.LeaveBankService;
import in.nucleusteq.plasma.service.LeaveService;

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

import java.text.ParseException;
import java.util.List;
/**
 * Controller class for managing leave-related operations.
 */
@RestController
@RequestMapping("/plasma/leave")
public class LeaveController {
    /**
     * LeaveService.
     */
    @Autowired
    private  LeaveService leaveService;
    /**
     * LeaveBankService.
     */
    @Autowired
    private  LeaveBankService leaveBankService;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveController.class);
    /**
     * Endpoint to create a holiday.
     *
     * @param holidayDTO The DTO containing information about the holiday to be created.
     * @return ResponseEntity containing the created HolidayInDTO.
     * @throws ParseException if there's an error parsing the date.
     */
    @PostMapping("add/holiday")
    public ResponseEntity<HolidayInDTO> addHoliday(@RequestBody HolidayInDTO holidayDTO) throws ParseException {
        LOGGER.info("Received request to create holiday: {}", holidayDTO);
        HolidayInDTO createdHoliday = leaveService.addHoliday(holidayDTO);
        LOGGER.info("Holiday created successfully: {}", createdHoliday);
        return new ResponseEntity<>(createdHoliday, HttpStatus.CREATED);
    }
    /**
     * Endpoint to retrieve a list of all holidays.
     *
     * @return ResponseEntity containing a list of HolidayInDTO representing all holidays.
     */
    @GetMapping("/holiday-list")
    public ResponseEntity<List<HolidayInDTO>> getAllHolidays() {
        LOGGER.info("Fetching list of all holidays");
        List<HolidayInDTO> holidays = leaveService.getAllHolidays();
        LOGGER.info("Returning list of all holidays: {}", holidays);
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }
    /**
     * Endpoint to apply for leave.
     *
     * @param leaveDetailsInDTO The DTO containing information about the leave to be applied for.
     * @return ResponseEntity containing the applied LeaveDetailsInDTO.
     */
    @PostMapping("/apply")
    public ResponseEntity<LeaveDetailsInDTO> applyLeave(@RequestBody LeaveDetailsInDTO leaveDetailsInDTO) {
        try {
            LOGGER.info("Received request to apply leave: {}", leaveDetailsInDTO);
            LeaveDetailsInDTO appliedLeave = leaveService.applyLeave(leaveDetailsInDTO);
            LOGGER.info("Leave applied successfully: {}", appliedLeave);
            return new ResponseEntity<>(appliedLeave, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error occurred while applying leave", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Endpoint to retrieve a list of all applied leaves.
     * @return ResponseEntity containing a list of LeaveDetailsInDTO representing all applied leaves.
     */
    @GetMapping("/applied")
    public ResponseEntity<List<LeaveDetailsInDTO>> getAllAppliedLeaves() {
        LOGGER.info("Fetching list of all applied leaves");
        List<LeaveDetailsInDTO> appliedLeaves = leaveService.getAllAppliedLeaves();
        LOGGER.info("Returning list of all applied leaves: {}", appliedLeaves);
        return new ResponseEntity<>(appliedLeaves, HttpStatus.OK);
    }
    /**
     * Endpoint to retrieve a list of all applied leaves that will be
     *       shown to the employee.
     * @param employeeId employeeId.
     * @return ResponseEntity containing a list of LeaveDetailsInDTO representing all applied leaves.
     */
    @GetMapping("/show/{employeeId}")
    public ResponseEntity<List<EmployeeLeaveOutDTO>> getEmployeeLeaves(@PathVariable("employeeId") String employeeId) {
        LOGGER.info("Fetching leaves for employee: {}", employeeId);
        List<EmployeeLeaveOutDTO> leaves = leaveService.getEmployeeLeaves(employeeId);
        LOGGER.info("Returning leaves for employee: {}", employeeId);
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }
    /**
     * Endpoint to retrieve a list of all applied leaves by the managers team.
     * @param employeeId managerId.
     * @return ResponseEntity containing a list of LeaveDetailsInDTO representing all applied leaves.
     */
    @GetMapping("/team/{managerId}")
    public ResponseEntity<List<ManagerEmpLeaveOutDTO>> getManagerEmpLeaves(@PathVariable("managerId") String employeeId) {
        LOGGER.info("Fetching leaves for manager: {}", employeeId);
        List<ManagerEmpLeaveOutDTO> leaves = leaveService.getManagerEmpLeaves(employeeId);
        LOGGER.info("Returning leaves for manager: {}", employeeId);
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }
    /**
     * Endpoint to reject the applied leave.
     *
     * @param details leaveDetails.
     * @return ResponseEntity containing a SuccessResponse with the result of the
     *         operation.
     */
    @PostMapping("/reject")
    public ResponseEntity<String> rejectLeaveRequest(@RequestBody LeaveDetails details) {
        try {
            LOGGER.info("Rejecting leave request with ID: {}", details.getLeaveRequestId());
            leaveService.rejectLeaveRequest(details.getLeaveRequestId());
            return new ResponseEntity<>("Rejected Leave", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error occurred while rejecting leave request", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     /**
     * Endpoint to accept the applied leaves.
     *
     * @param leaveRequestId Id of the leave to be accepted
     * @return ResponseEntity containing a SuccessResponse with the result of the
     *         operation.
     */
    @PutMapping("/accept/{leaveRequestId}")
    public ResponseEntity<SuccessResponse> acceptLeave(@PathVariable("leaveRequestId") Long leaveRequestId) {
        LOGGER.info("Accepting leave request with ID: {}", leaveRequestId);
        leaveService.acceptLeave(leaveRequestId);
        LOGGER.info("Leave with ID {} has been accepted", leaveRequestId);
        SuccessResponse successResponse = new SuccessResponse("Leave accepted successfully.", 200);
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Endpoint to update the paid leaves of every employee.
     *
     * @return ResponseEntity containing a SuccessResponse with the result of the
     *         operation.
     */
    @PutMapping("/update-paid-leave")
    public ResponseEntity<SuccessResponse> updatePaidLeave() {
        LOGGER.info("Updating paid leave");
        leaveBankService.updatePaidLeave();
        LOGGER.info("Paid leave updated successfully");
        SuccessResponse successResponse = new SuccessResponse("Total paid leave updated successfully", 200);
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Endpoint to add leave bank for that specific employeeId..
     *
     * @param leaveBankInDTO leaveBankInDTO
     * @return ResponseEntity containing a SuccessResponse with the result of the
     *         operation.
     */
    @PostMapping("/update-leave-bank")
    public ResponseEntity<String> updateLeaveBank(@RequestBody LeaveBankInDTO leaveBankInDTO) {
        LOGGER.info("Updating leave bank for employee: {}", leaveBankInDTO.getEmployeeId());
        leaveBankService.addLeavesToLeaveBank(leaveBankInDTO);
        LOGGER.info("Leaves added successfully for employee: {}", leaveBankInDTO.getEmployeeId());
        return new ResponseEntity<>("Leaves added successfully for employee " + leaveBankInDTO.getEmployeeId(),
                HttpStatus.CREATED);
    }
    /**
     * Endpoint to retrieve a list of leave bank.
     *
     * @param employeeId employeeId
     * @return ResponseEntity containing a SuccessResponse with the result of the
     *         operation.
     */
    @GetMapping("/bank/{employeeId}")
    public ResponseEntity<List<LeaveBankInDTO>> getEmployeeLeaveBank(@PathVariable("employeeId") String employeeId) {
        LOGGER.info("Fetching leave bank for employee: {}", employeeId);
        List<LeaveBankInDTO> leaves = leaveBankService.getEmployeeBankLeaves(employeeId);
        LOGGER.info("Returning leave bank for employee: {}", employeeId);
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }
    /**
     * Endpoint to retrieve the details of employee's sick leave.
     *
     * @param employeeId employeeId
     * @return ResponseEntity containing a LeaveInfoOutDTO which has the details of sick leave.
     */
    @GetMapping("/sick-leave/{employeeId}")
    public ResponseEntity<LeaveInfoOutDTO> getSickLeaveInfoForEmployee(
            @PathVariable("employeeId") String employeeId) {
        LOGGER.info("Fetching sick leave info for employee: {}", employeeId);
        LeaveInfoOutDTO leaveInfoDTO = leaveBankService.getSickLeaveInfoForEmployee(employeeId);
        LOGGER.info("Returning sick leave info for employee: {}", employeeId);
        return ResponseEntity.ok(leaveInfoDTO);
    }
    /**
     * Endpoint to retrieve the details of employee's paid leave.
     *
     * @param employeeId employeeId
     * @return ResponseEntity containing a LeaveInfoOutDTO which has the details of paid leave.
     */
    @GetMapping("/paid-leave/{employeeId}")
    public ResponseEntity<LeaveInfoOutDTO> getPaidLeaveInfoForEmployee(
            @PathVariable("employeeId") String employeeId) {
        LOGGER.info("Fetching paid leave info for employee: {}", employeeId);
        LeaveInfoOutDTO leaveInfoDTO = leaveBankService.getPaidLeaveInfoForEmployee(employeeId);
        LOGGER.info("Returning paid leave info for employee: {}", employeeId);
        return ResponseEntity.ok(leaveInfoDTO);
    }

}
