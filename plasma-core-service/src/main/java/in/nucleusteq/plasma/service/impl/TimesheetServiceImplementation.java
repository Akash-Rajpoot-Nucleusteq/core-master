package in.nucleusteq.plasma.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import in.nucleusteq.plasma.dao.EmployeeRepository;
import in.nucleusteq.plasma.dao.TimesheetDataRepository;
import in.nucleusteq.plasma.dao.TimesheetRepository;
import in.nucleusteq.plasma.dao.UserPersonalDetailRepository;
import in.nucleusteq.plasma.dto.in.TimesheetInDTO;
import in.nucleusteq.plasma.dto.out.ManagerTimesheetOutDTO;
import in.nucleusteq.plasma.dto.out.TimesheetDataOutDTO;
import in.nucleusteq.plasma.enums.TimesheetStatus;
import in.nucleusteq.plasma.exception.TimesheetNotFoundException;
import in.nucleusteq.plasma.entity.Employee;
import in.nucleusteq.plasma.entity.Timesheet;
import in.nucleusteq.plasma.entity.TimesheetData;
import in.nucleusteq.plasma.entity.UserPersonalDetail;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.service.TimesheetService;
import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing timesheet operations.
 */
@Service
public class TimesheetServiceImplementation implements TimesheetService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TimesheetServiceImplementation.class);
    /**
     * Timesheet Repository.
     */
    @Autowired
    private TimesheetRepository timesheetRepository;
    /**
     * Timesheet data Repository.
     */
    @Autowired
    private TimesheetDataRepository timesheetDataRepository;
    /**
     * Employee Repository.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * User Personal Detail Repository.
     */
    @Autowired
    private UserPersonalDetailRepository userPersonalDetailsRepository;

    /**
     * Creates a new timesheet.
     * @param timesheetDTO The TimesheetInDTO object containing timesheet details.
     * @return A SuccessResponse indicating the success of the operation.
     */
    @Override
    public SuccessResponse createTimesheet(TimesheetInDTO timesheetDTO) {
        Timesheet timesheet = new Timesheet();
        timesheet.setEmployeeId(timesheetDTO.getEmployeeId());
        timesheet.setStartDate(timesheetDTO.getStartDate());
        timesheet.setEndDate(timesheetDTO.getEndDate());
        timesheet.setStatus(timesheetDTO.getStatus());
        timesheet.setManagerId(timesheetDTO.getManagerId());
        timesheetRepository.save(timesheet);
        LOGGER.info("Timesheet entry created successfully");
        return new SuccessResponse("Timesheet create Successfully", HttpStatus.OK.value());

    }

    /**
     * Submits a timesheet along with timesheet data.
     * @param timesheetId     The ID of the timesheet to submit.
     * @param dateAndHoursMap A map containing date as key and hours as value.
     * @return A SuccessResponse indicating the success of the operation.
     */
    @Override
    public SuccessResponse submitTimesheet(Long timesheetId, Map<Date, Map<String, Integer>> dateAndHoursMap) {
        Timesheet timesheet = timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new EntityNotFoundException("Timesheet not found with id: " + timesheetId));
        timesheet.setStatus(TimesheetStatus.SUBMITTED);
        timesheetRepository.save(timesheet);
        for (Map.Entry<Date, Map<String, Integer>> entry : dateAndHoursMap.entrySet()) {
            Date date = entry.getKey();
            int taskHours = entry.getValue().getOrDefault("taskHours", 0);
            int leaveHours = entry.getValue().getOrDefault("leaveHours", 0);
            TimesheetData timesheetData = new TimesheetData();
            timesheetData.setTimesheet(timesheet);
            timesheetData.setTimesheetDate(date);
            timesheetData.setTaskHours(taskHours);
            timesheetData.setLeaveHours(leaveHours);
            timesheetDataRepository.save(timesheetData);
        }
        LOGGER.info("Timesheet data saved successfully");
        return new SuccessResponse("Timesheet data save successfully", HttpStatus.OK.value());
    }

    /**
     * Retrieves timesheet data grouped by timesheet ID for a given employee.
     * @param employeeId The ID of the employee.
     * @return A map containing timesheet ID as key and a list of TimesheetData
     *         objects as value.
     */
    @Override
    public Map<Long, List<TimesheetData>> getTimesheetDataByEmployeeId(String employeeId) {
        List<Timesheet> timesheets = timesheetRepository.findByEmployeeId(employeeId);
        Map<Long, List<TimesheetData>> timesheetDataMap = new HashMap<>();
        for (Timesheet timesheet : timesheets) {
            for (TimesheetData timesheetData : timesheet.getTimesheetDataList()) {
                Long timesheetId = timesheetData.getTimesheet().getTimesheetId();
                if (timesheetDataMap.containsKey(timesheetId)) {
                    timesheetDataMap.get(timesheetId).add(timesheetData);
                } else {
                    List<TimesheetData> dataList = new ArrayList<>();
                    dataList.add(timesheetData);
                    timesheetDataMap.put(timesheetId, dataList);
                }
            }
        }
        LOGGER.info("Timesheet data fetched successfully for employee with ID: {}", employeeId);
        return timesheetDataMap;
    }

    /**
     * Accepts a timesheet.
     * @param timesheetId The ID of the timesheet to accept.
     * @return A SuccessResponse indicating the success of the operation.
     */
    @Override
    public SuccessResponse acceptTimesheet(Long timesheetId) {
        Timesheet timesheet = timesheetRepository.findByTimesheetId(timesheetId)
                .orElseThrow(() -> new TimesheetNotFoundException("Timesheet Not found"));
        timesheet.setStatus(TimesheetStatus.APPROVED);
        timesheetRepository.save(timesheet);
        LOGGER.info("Timesheet with ID {} accepted successfully", timesheetId);
        return new SuccessResponse("Timesheet with ID " + timesheetId + " accepted successfully.",
                HttpStatus.OK.value());
    }

    /**
     * Rejects a timesheet.
     * @param timesheetId The ID of the timesheet to reject.
     * @return A SuccessResponse indicating the success of the operation.
     */
    @Override
    public SuccessResponse rejctTimesheet(Long timesheetId) {
        Timesheet timesheet = timesheetRepository.findByTimesheetId(timesheetId)
                .orElseThrow(() -> new TimesheetNotFoundException("Timesheet Not found"));
        timesheet.setStatus(TimesheetStatus.REJECT);
        timesheetRepository.save(timesheet);
        LOGGER.info("Timesheet with ID {} rejected successfully", timesheetId);
        return new SuccessResponse("Timesheet with ID " + timesheetId + " rejected successfully.",
                HttpStatus.OK.value());

    }

    /**
     * Retrieves all approved timesheets.
     * @return A list of approved timesheets.
     */
    @Override
    public List<Timesheet> getApprovedTimesheets() {
        LOGGER.info("Fetching approved timesheets");
        return timesheetRepository.findByStatus(TimesheetStatus.APPROVED);
    }

    /**
     * Retrieves timesheet data by timesheet ID.
     * @param timesheetId The ID of the timesheet.
     * @return A map containing date as key and TimesheetDataOutDTO as value.
     */
    @Override
    public Map<Date, TimesheetDataOutDTO> getTimesheetDataByTimesheetId(Long timesheetId) {
        LOGGER.info("Fetching timesheet data for timesheet ID: {}", timesheetId);
        List<TimesheetData> timesheetDataList = timesheetDataRepository.findByTimesheetTimesheetId(timesheetId);
        Map<Date, TimesheetDataOutDTO> timesheetDataMap = new HashMap<>();
        for (TimesheetData data : timesheetDataList) {
            timesheetDataMap.put(data.getTimesheetDate(), new TimesheetDataOutDTO(data.getTimesheetDate(),
                    data.getLeaveHours(), data.getTaskHours(), data.getTimesheet()));
        }
        return timesheetDataMap;
    }

    /**
     * Retrieves timesheets managed by a manager.
     * @param employeeId The ID of the manager.
     * @return A list of ManagerTimesheetOutDTO objects.
     */
    @Override
    public List<ManagerTimesheetOutDTO> getManagerEmpTimesheet(String employeeId) {
        LOGGER.info("Fetching timesheets for manager with employee ID: {}", employeeId);
        List<Timesheet> timesheet = timesheetRepository.findByManagerId(employeeId);
        return timesheet.stream().map(this::convertToManagerTimesheetDTO).collect(Collectors.toList());
    }

    /**
     * Converts a Timesheet entity to a ManagerTimesheetOutDTO object.
     * @param timesheet The Timesheet entity to convert.
     * @return A ManagerTimesheetOutDTO object.
     */
    private ManagerTimesheetOutDTO convertToManagerTimesheetDTO(Timesheet timesheet) {
        ManagerTimesheetOutDTO dto = new ManagerTimesheetOutDTO();
        Optional<Employee> userOptional = employeeRepository.findByUserId(timesheet.getEmployeeId());
        Employee user = userOptional.get();
        Integer id = user.getUserPersonalDetail().getId();
        Optional<UserPersonalDetail> employeeOptional = userPersonalDetailsRepository.findById(id);
        if (employeeOptional.isPresent()) {
            UserPersonalDetail employee = employeeOptional.get();
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            dto.setAssigneName(fullName);
        } else {
            dto.setAssigneName("Employee Not Found");
        }
        dto.setWeekDate(timesheet.getStartDate());
        int totalTaskHours = timesheetDataRepository.sumTaskHoursByTimesheetId(timesheet.getTimesheetId());
        dto.setHourSubmitted(totalTaskHours);
        return dto;
    }
}
