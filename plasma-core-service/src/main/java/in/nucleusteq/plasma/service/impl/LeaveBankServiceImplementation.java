package in.nucleusteq.plasma.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nucleusteq.plasma.constants.LeaveConstants;
import in.nucleusteq.plasma.dao.LeaveBankRepository;
import in.nucleusteq.plasma.dto.in.leave.LeaveBankInDTO;
import in.nucleusteq.plasma.dto.out.LeaveInfoOutDTO;
import in.nucleusteq.plasma.exception.LeaveNotFoundException;
import in.nucleusteq.plasma.entity.LeaveBank;
import in.nucleusteq.plasma.service.LeaveBankService;
/**
 * Service implementation for managing leave bank operations.
 */
@Service
public class LeaveBankServiceImplementation implements LeaveBankService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveBankServiceImplementation.class);
    /**
     * Leave Bank Repository.
     */
    @Autowired
    private LeaveBankRepository leaveBankRepository;
    /**
     * Updates paid leave for all employees by adding 1.5 days to their remaining and total paid leave.
     */
    public void updatePaidLeave() {
        LOGGER.info("Updating paid leave for all employees");

        List<LeaveBank> leaveBanks = leaveBankRepository.findAll();

        if (leaveBanks.isEmpty()) {
            throw new LeaveNotFoundException("Leave bank records not found");
        }

        for (LeaveBank leaveBank : leaveBanks) {
            double remainingPaidLeave = leaveBank.getRemainingPaidLeave();
            double totalPaidLeave = leaveBank.getTotalPaidLeave();
            leaveBank.setRemainingPaidLeave(remainingPaidLeave + 1.5);
            leaveBank.setTotalPaidLeave(totalPaidLeave + 1.5);
        }

        leaveBankRepository.saveAll(leaveBanks);
    }
    /**
     * Adds leaves to the leave bank for a specific employee.
     * @param leaveBankDTO The LeaveBankInDTO containing leave bank details.
     */
    @Override
    public void addLeavesToLeaveBank(LeaveBankInDTO leaveBankDTO) {
        LOGGER.info("Adding leaves to leave bank for employee with ID: {}", leaveBankDTO.getEmployeeId());
        LeaveBank leaveBank = new LeaveBank();
        leaveBank.setEmployeeId(leaveBankDTO.getEmployeeId());
        leaveBank.setTotalSickLeave(LeaveConstants.TOTAL_SICK_LEAVE);
        leaveBank.setRemainingSickLeave(LeaveConstants.TOTAL_SICK_LEAVE);
        leaveBank.setTotalPaidLeave(LeaveConstants.TOTAL_PAID_LEAVE);
        leaveBank.setRemainingPaidLeave(LeaveConstants.TOTAL_PAID_LEAVE);

        leaveBankRepository.save(leaveBank);
    }
    /**
     * Retrieves leave bank details for a specific employee.
     * @param employeeId The ID of the employee.
     * @return List of LeaveBankInDTO containing leave bank details.
     */
    @Override
    public List<LeaveBankInDTO> getEmployeeBankLeaves(String employeeId) {
        LOGGER.info("Fetching leave bank leaves for employee with ID: {}", employeeId);
        List<LeaveBank> leaves = leaveBankRepository.findByEmployeeId(employeeId);
        return leaves.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
    }
    /**
     * Converts a LeaveBank entity to LeaveBankInDTO.
     * @param leaveBank The LeaveBank entity to convert.
     * @return LeaveBankInDTO converted from LeaveBank entity.
     */
    private LeaveBankInDTO convertToDTO(LeaveBank leaveBank) {
        LeaveBankInDTO leaveBankDTO = new LeaveBankInDTO();
//        leaveBankDTO.setRemainingSickLeave(leaveBank.getRemainingSickLeave());
        BeanUtils.copyProperties(leaveBank, leaveBankDTO);
        return leaveBankDTO;
    }

    /**
     * Retrieves sick leave information for a specific employee.
     * @param employeeId The ID of the employee.
     * @return LeaveInfoOutDTO containing sick leave information.
     */
    public LeaveInfoOutDTO getSickLeaveInfoForEmployee(String employeeId) {
        LOGGER.info("Fetching sick leave info for employee with ID: {}", employeeId);

        List<LeaveBank> leaveBanks = leaveBankRepository.findByEmployeeId(employeeId);

        if (leaveBanks.isEmpty()) {
            LOGGER.error("Leave bank record not found for employee: {}", employeeId);

            throw new RuntimeException("Leave bank record not found for employee: " + employeeId);
        }

        LeaveBank leaveBank = leaveBanks.get(0);
        LeaveInfoOutDTO dto = new LeaveInfoOutDTO();
        dto.setTotalLeave(leaveBank.getTotalSickLeave());
        dto.setRemainingLeaves(leaveBank.getRemainingSickLeave());
        dto.setConsumedLeaves(leaveBank.getTotalSickLeave() - leaveBank.getRemainingSickLeave());
        return dto;
    }
    /**
     * Retrieves paid leave information for a specific employee.
     * @param employeeId The ID of the employee.
     * @return LeaveInfoOutDTO containing paid leave information.
     */
    public LeaveInfoOutDTO getPaidLeaveInfoForEmployee(String employeeId) {
        LOGGER.info("Fetching paid leave info for employee with ID: {}", employeeId);
        List<LeaveBank> leaveBanks = leaveBankRepository.findByEmployeeId(employeeId);

         if (leaveBanks.isEmpty()) {
             LOGGER.error("Leave bank record not found for employee: {}", employeeId);

             throw new RuntimeException("Leave bank record not found for employee: " + employeeId);
         }

        LeaveBank leaveBank = leaveBanks.get(0);
        LeaveInfoOutDTO dto = new LeaveInfoOutDTO();
        dto.setTotalLeave(leaveBank.getTotalPaidLeave());
        dto.setRemainingLeaves(leaveBank.getRemainingPaidLeave());
        dto.setConsumedLeaves(leaveBank.getTotalPaidLeave() - leaveBank.getRemainingPaidLeave());
        return dto;
    }
}
