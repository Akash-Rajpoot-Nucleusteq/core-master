package in.nucleusteq.plasma.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nucleusteq.plasma.dao.EmployeeRepository;
import in.nucleusteq.plasma.dao.HolidayRepository;

import in.nucleusteq.plasma.dto.in.leave.HolidayInDTO;
import in.nucleusteq.plasma.dto.in.leave.LeaveDetailsInDTO;
import in.nucleusteq.plasma.dao.LeaveBankRepository;
import in.nucleusteq.plasma.dao.LeaveDetailsRepository;
import in.nucleusteq.plasma.dao.UserPersonalDetailRepository;
import in.nucleusteq.plasma.dto.out.EmployeeLeaveOutDTO;
import in.nucleusteq.plasma.dto.out.ManagerEmpLeaveOutDTO;
import in.nucleusteq.plasma.enums.FullOrHalfDay;
import in.nucleusteq.plasma.enums.LeaveStatus;
import in.nucleusteq.plasma.enums.LeaveType;
import in.nucleusteq.plasma.exception.LeaveNotFoundException;
import in.nucleusteq.plasma.entity.Employee;
import in.nucleusteq.plasma.entity.Holiday;
import in.nucleusteq.plasma.entity.LeaveBank;
import in.nucleusteq.plasma.entity.LeaveDetails;
import in.nucleusteq.plasma.entity.UserPersonalDetail;
//import in.nucleusteq.plasma.repository.HolidayRepository;
//import in.nucleusteq.plasma.repository.LeaveBankRepository;
//import in.nucleusteq.plasma.repository.LeaveDetailsRepository;
//import in.nucleusteq.plasma.repository.UserPersonalDetailRepository;
//import in.nucleusteq.plasma.repository.UserRepository;
import in.nucleusteq.plasma.service.LeaveService;

/**
 * Service implementation for managing leave operations.
 */
@Service
public class LeaveServiceImplementation implements LeaveService {
    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveServiceImplementation.class);
    /**
     * Holiday Repository.
     */
    @Autowired
    private HolidayRepository holidayRepository;
    /**
     * Leave Details Repository.
     */
    @Autowired
    private LeaveDetailsRepository leaveDetailsRepository;
    /**
     * User Personal Detail Repository.
     */
    @Autowired
    private UserPersonalDetailRepository userPersonalDetailsRepository;
    /**
     * Employee Repository.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     *Leave Bank Repository.
     */
    @Autowired
    private LeaveBankRepository leaveBankRepository;

    /**
     * Add a new holiday.
     * @param holidayDTO The HolidayInDTO containing holiday details.
     * @return The created HolidayInDTO.
     * @throws ParseException If parsing fails.
     */
    @Override
    public HolidayInDTO addHoliday(HolidayInDTO holidayDTO) throws ParseException {
        Holiday holiday = new Holiday();
        holiday.setDate(holidayDTO.getDate());
        holiday.setHolidayName(holidayDTO.getHolidayName());
        Holiday savedHoliday = holidayRepository.save(holiday);
        LOGGER.info("Holiday created successfully: {}", savedHoliday);
        return convertToDTO(savedHoliday);
    }

    /**
     * Retrieves all holidays.
     * @return List of HolidayInDTO containing holiday details.
     */
    @Override
    public List<HolidayInDTO> getAllHolidays() {
        List<Holiday> holidays = holidayRepository.findAll();
        LOGGER.info("Fetched {} holidays");
        return holidays.stream().map(this::convertToDTO).toList();
    }

    private void copyProperties(Holiday source, HolidayInDTO destination) {
        BeanUtils.copyProperties(source, destination);
    }

    /**
     * Converts a Holiday entity to HolidayInDTO.
     * @param holiday The Holiday entity to convert.
     * @return HolidayInDTO converted from Holiday entity.
     */
    private HolidayInDTO convertToDTO(Holiday holiday) {
        HolidayInDTO holidayDTO = new HolidayInDTO();
        copyProperties(holiday, holidayDTO);
        return holidayDTO;
    }

    /**
     * Applies for leave.
     * @param leaveDetailsInDTO The LeaveDetailsInDTO containing leave application
     *                          details.
     * @return The applied LeaveDetailsInDTO.
     */
    @Override
    public LeaveDetailsInDTO applyLeave(LeaveDetailsInDTO leaveDetailsInDTO) {
        try {
            LeaveDetails leaveDetails = new LeaveDetails();
            leaveDetails.setStartDate(leaveDetailsInDTO.getStartDate());
            leaveDetails.setEndDate(leaveDetailsInDTO.getEndDate());
            leaveDetails.setLeaveStatus(leaveDetailsInDTO.getLeaveStatus());
            leaveDetails.setEmployeeId(leaveDetailsInDTO.getEmployeeId());
            leaveDetails.setFullOrHalfDay(leaveDetailsInDTO.getFullOrHalfDay());
            leaveDetails.setLeaveType(leaveDetailsInDTO.getLeaveType());
            //Client module
            leaveDetails.setManagerId(leaveDetailsInDTO.getManagerId());
            leaveDetails.setReason(leaveDetailsInDTO.getReason());

            LeaveDetails savedLeaveApplication = leaveDetailsRepository.save(leaveDetails);

            return convertToDTO(savedLeaveApplication);
        } catch (Exception e) {
            throw new RuntimeException("Failed to apply leave", e);
        }
    }

    private void copyProperties(LeaveDetails source, LeaveDetailsInDTO destination) {
        BeanUtils.copyProperties(source, destination);
    }

    /**
     * Converts a LeaveDetails entity to LeaveDetailsInDTO.
     * @param leaveDetails The LeaveDetails entity to convert.
     * @return LeaveDetailsInDTO converted from LeaveDetails entity.
     */
    private LeaveDetailsInDTO convertToDTO(LeaveDetails leaveDetails) {
        LeaveDetailsInDTO leaveDetailsDTO = new LeaveDetailsInDTO();
        copyProperties(leaveDetails, leaveDetailsDTO);
        return leaveDetailsDTO;
    }

    /**
     * Retrieves all applied leaves.
     * @return List of LeaveDetailsInDTO containing applied leave details.
     */
    @Override
    public List<LeaveDetailsInDTO> getAllAppliedLeaves() {
        List<LeaveDetails> appliedLeaves = leaveDetailsRepository.findAll();
        LOGGER.info("Fetching all applied leaves");
        return appliedLeaves.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Retrieves leaves applied by a specific employee.
     * @param employeeId The ID of the employee.
     * @return List of EmployeeLeaveOutDTO containing leave details.
     */
    @Override
    public List<EmployeeLeaveOutDTO> getEmployeeLeaves(String employeeId) {
        List<LeaveDetails> leaves = leaveDetailsRepository.findByEmployeeId(employeeId);
        LOGGER.info("Fetching leaves for employee with ID: {}", employeeId);
        return leaves.stream().map(this::convertToEmployeeLeaveDTO).collect(Collectors.toList());
    }

    /**
     * Converts a LeaveDetails entity to EmployeeLeaveOutDTO.
     * @param leaveDetails The LeaveDetails entity to convert.
     * @return EmployeeLeaveOutDTO converted from LeaveDetails entity.
     */
    private EmployeeLeaveOutDTO convertToEmployeeLeaveDTO(LeaveDetails leaveDetails) {
        EmployeeLeaveOutDTO dto = new EmployeeLeaveOutDTO();
        dto.setApplyDate(leaveDetails.getStartDate());
        LocalDate startDate = leaveDetails.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = leaveDetails.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        double duration;
        if (leaveDetails.getFullOrHalfDay() == FullOrHalfDay.FULL_DAY) {
            duration = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        } else if (leaveDetails.getFullOrHalfDay() == FullOrHalfDay.HALF_DAY) {
            duration = 0.5;
        } else {
            duration = 0;
        }

        dto.setDuration(duration);
        dto.setLeaveStatus(leaveDetails.getLeaveStatus());
        dto.setLeaveType(leaveDetails.getLeaveType());
        dto.setReason(leaveDetails.getReason());
        return dto;
    }

    /**
     * Retrieves leaves applied by employees under a specific manager.
     * @param managerId The ID of the manager.
     * @return List of ManagerEmpLeaveOutDTO containing leave details.
     */
    @Override
    public List<ManagerEmpLeaveOutDTO> getManagerEmpLeaves(String managerId) {
        List<LeaveDetails> leaves = leaveDetailsRepository.findByManagerId(managerId);
        return leaves.stream().map(this::convertToManagerEmpLeaveDTO).collect(Collectors.toList());
    }

    /**
     * Converts a LeaveDetails entity to ManagerEmpLeaveOutDTO.
     * @param leaveDetails The LeaveDetails entity to convert.
     * @return ManagerEmpLeaveOutDTO converted from LeaveDetails entity.
     */
    private ManagerEmpLeaveOutDTO convertToManagerEmpLeaveDTO(LeaveDetails leaveDetails) {
        ManagerEmpLeaveOutDTO dto = new ManagerEmpLeaveOutDTO();
        Optional<Employee> userOptional = employeeRepository.findByUserId(leaveDetails.getEmployeeId());
        Employee user = userOptional.get();
//        Optional<UserPersonalDetail> employeeOptional = userPersonalDetailsRepository.findById(user.getUserPersonalDetail());
        Integer id = user.getUserPersonalDetail().getId();
        Optional<UserPersonalDetail> employeeOptional = userPersonalDetailsRepository.findById(id);
        if (employeeOptional.isPresent()) {
            UserPersonalDetail employee = employeeOptional.get();
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            dto.setTeamMember(fullName);
        } else {
            dto.setTeamMember("Employee not found");
        }
        dto.setStartDate(leaveDetails.getStartDate());

        List<LeaveBank> leaveBanks = leaveBankRepository.findByEmployeeId(leaveDetails.getEmployeeId());
        if (leaveBanks.isEmpty()) {
            throw new LeaveNotFoundException("Leave bank record not found");
        }

        LeaveBank leaveBank = leaveBanks.get(0);
        if (leaveDetails.getLeaveType() == LeaveType.SICK_LEAVE) {

            dto.setRemainingDays(leaveBank.getRemainingSickLeave());
        } else if (leaveDetails.getLeaveType() == LeaveType.PAID_LEAVE) {

            dto.setRemainingDays(leaveBank.getRemainingPaidLeave());
        }
        dto.setLeaveType(leaveDetails.getLeaveType());
        dto.setEnddate(leaveDetails.getEndDate());
        return dto;
    }

    /**
     * Rejects a leave request.
     * @param leaveId The ID of the leave request to reject.
     */
    @Override
    public void rejectLeaveRequest(Long leaveId) {

        LeaveDetails leaveRequest = leaveDetailsRepository.findByLeaveRequestId(leaveId)
                .orElseThrow(() -> new LeaveNotFoundException("Leave request not found with ID: " + leaveId));

        leaveRequest.setLeaveStatus(LeaveStatus.REJECT);
        leaveDetailsRepository.save(leaveRequest);
        LOGGER.info("Leave request rejected successfully");
    }

    /**
     * Accepts a leave request.
     * @param leaveId The ID of the leave request to accept.
     */
    public void acceptLeave(Long leaveId) {
        LeaveDetails leave = leaveDetailsRepository.findByLeaveRequestId(leaveId)
                .orElseThrow(() -> new LeaveNotFoundException("Leave not found"));

        leave.setLeaveStatus(LeaveStatus.APPROVED);
        leaveDetailsRepository.save(leave);

        List<LeaveBank> leaveBanks = leaveBankRepository.findByEmployeeId(leave.getEmployeeId());
        if (leaveBanks.isEmpty()) {
            throw new LeaveNotFoundException("Leave bank record not found");
        }

        LeaveBank leaveBank = leaveBanks.get(0);

        LocalDate startDate = leave.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = leave.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long duration = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (leave.getLeaveType() == LeaveType.SICK_LEAVE) {
            leaveBank.setRemainingSickLeave(leaveBank.getRemainingSickLeave() - duration);
        } else if (leave.getLeaveType() == LeaveType.PAID_LEAVE) {
            if (leave.getFullOrHalfDay() == FullOrHalfDay.FULL_DAY) {
                leaveBank.setRemainingPaidLeave(leaveBank.getRemainingPaidLeave() - duration);
            } else if (leave.getFullOrHalfDay() == FullOrHalfDay.HALF_DAY) {
                leaveBank.setRemainingPaidLeave(leaveBank.getRemainingPaidLeave() - duration / 2);
            }
            leaveBank.setRemainingPaidLeave(leaveBank.getRemainingPaidLeave() - duration);
        }
        leaveBankRepository.save(leaveBank);
        LOGGER.info("Leave request accepted successfully");
    }
}
