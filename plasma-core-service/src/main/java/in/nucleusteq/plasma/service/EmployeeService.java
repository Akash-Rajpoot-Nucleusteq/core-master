package in.nucleusteq.plasma.service;

import java.util.List;

import in.nucleusteq.plasma.dto.in.user.RecentOnBoardingInDTO;
import in.nucleusteq.plasma.dto.in.user.RecentOnBoardingWithDocumentInDto;
import in.nucleusteq.plasma.dto.in.user.RejectOnBoardInDTO;
import in.nucleusteq.plasma.dto.in.user.UpdatedDetailOfOnBoardInDTO;
import in.nucleusteq.plasma.dto.out.user.DetailOfRecentOnBoardingOutDTO;
import in.nucleusteq.plasma.dto.out.user.PendingOnBoardOutDTO;
import in.nucleusteq.plasma.dto.out.user.RecentOnBoardingListOutDTO;
import in.nucleusteq.plasma.payload.SuccessResponse;
/**
 * Service interface of employee.
 */
public interface EmployeeService {
    /**
     * Proccess Recent OnBoard.
     * @param recentOnboardInDTO
     * @return SuccessResponse
     */
    public SuccessResponse processRecentOnboard(RecentOnBoardingInDTO recentOnboardInDTO);
    /**
     * processRecenOnboardWithDocument.
     * @param recentOnboardInDTO
     */
    public void processRecentOnboardWithDocument(RecentOnBoardingWithDocumentInDto recentOnboardInDTO);
    /**
     * getPendingOnBoardUser.
     * @return list of Pending OnBoard Out DTO
     */
    public List<PendingOnBoardOutDTO> getPendingOnBoardUsers();
    /**
     * onboardForm.
     * @param id
     * @return DetailOfRecentOnBoardingOutDTO
     */
    public DetailOfRecentOnBoardingOutDTO onboardForm(int id);
    /**
     * updateDetailsOfOnBoard.
     * @param uBoardInDTO
     * @return SuccessResponse
     */
    public SuccessResponse updateDetailOfOnBoard(UpdatedDetailOfOnBoardInDTO uBoardInDTO);
    /**
     * rejectOnboard.
     * @param rejectOnBoard
     * @return SuccessResponse
     */
    public SuccessResponse rejectOnboard(RejectOnBoardInDTO rejectOnBoard);
    /**
     * getListOfRecentOnboarding.
     * @return list of Recent onBoarding List out DTO
     */
    public List<RecentOnBoardingListOutDTO> getListOfRecentOnboarding();
    /**
     * getAllEmployees.
     * @return list of Recent OnBoarding List out DTO
     */
    public List<RecentOnBoardingListOutDTO> getAllEmployees();
}
