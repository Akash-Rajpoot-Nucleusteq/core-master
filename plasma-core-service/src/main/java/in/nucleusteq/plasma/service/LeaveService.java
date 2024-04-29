package in.nucleusteq.plasma.service;

import java.text.ParseException;
import java.util.List;

import in.nucleusteq.plasma.dto.in.leave.HolidayInDTO;
import in.nucleusteq.plasma.dto.in.leave.LeaveDetailsInDTO;
import in.nucleusteq.plasma.dto.out.EmployeeLeaveOutDTO;
import in.nucleusteq.plasma.dto.out.ManagerEmpLeaveOutDTO;
/**
 * Service interface of leave.
 */
public interface LeaveService {
    /**
     * createHoliyday.
     * @param holidayDTO
     * @return holiday in dto
     */
    HolidayInDTO addHoliday(HolidayInDTO holidayDTO) throws ParseException;
    /**
     * getAllHoildays.
     * @return list of holiday In DTO
     */
    List<HolidayInDTO> getAllHolidays();
    /**
     * applyLeave.
     * @param leaveDetailsInDTO
     * @return leave details in dtoe
     */
    LeaveDetailsInDTO applyLeave(LeaveDetailsInDTO leaveDetailsInDTO);
    /**
     * getAllAppliedLeaves.
     * @return list of leave details in dto
     */
    List<LeaveDetailsInDTO> getAllAppliedLeaves();
    /**
     * getEmployeeLeaves.
     * @param employeeId
     * @return list of employee leave out dto
     */
    List<EmployeeLeaveOutDTO> getEmployeeLeaves(String employeeId);
    /**
     * getManagerEmpLeaves.
     * @param employeeId
     * @return list of manager emp leave out dto
     */
    List<ManagerEmpLeaveOutDTO> getManagerEmpLeaves(String employeeId);
    /**
     * rejectLeaveRequest.
     * @param leaveRequestId
     */
    void rejectLeaveRequest(Long leaveRequestId);
    /**
     * acceptLeave.
     * @param leaveRequestId
     */
    void acceptLeave(Long leaveRequestId);
}
