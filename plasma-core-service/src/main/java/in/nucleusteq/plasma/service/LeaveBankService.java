package in.nucleusteq.plasma.service;

import java.util.List;

import in.nucleusteq.plasma.dto.in.leave.LeaveBankInDTO;
import in.nucleusteq.plasma.dto.out.LeaveInfoOutDTO;
/**
 * Service interface of leave bank.
 */
public interface LeaveBankService {
/**
 * updatePaidLeave.
 */
    void updatePaidLeave();
    /**
     * addleavesToLeaveBank.
     * @param leaveBankInDTO
     */
    void addLeavesToLeaveBank(LeaveBankInDTO leaveBankInDTO);
    /**
     * getEmployeeBankLeaves.
     * @param employeeId
     * @return list of leave bank in DTO
     */
    List<LeaveBankInDTO> getEmployeeBankLeaves(String employeeId);
    /**
     * getSickLeaveInfoForEmployee.
     * @param employeeId
     * @return lieave info out dto
     */
    LeaveInfoOutDTO getSickLeaveInfoForEmployee(String employeeId);
    /**
     * getPaidLeaveInfoForEmployee.
     * @param employeeId
     * @return leave info out dto
     */
    LeaveInfoOutDTO getPaidLeaveInfoForEmployee(String employeeId);
}
