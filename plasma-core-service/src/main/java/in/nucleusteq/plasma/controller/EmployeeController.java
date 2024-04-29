package in.nucleusteq.plasma.controller;


import in.nucleusteq.plasma.dto.in.user.PasswordReset;
import in.nucleusteq.plasma.dto.in.user.RecentOnBoardingInDTO;
import in.nucleusteq.plasma.dto.in.user.RejectOnBoardInDTO;
import in.nucleusteq.plasma.dto.in.user.UpdatedDetailOfOnBoardInDTO;
import in.nucleusteq.plasma.dto.out.user.DetailOfRecentOnBoardingOutDTO;
import in.nucleusteq.plasma.dto.out.user.PendingOnBoardOutDTO;
import in.nucleusteq.plasma.dto.out.user.RecentOnBoardingListOutDTO;
import in.nucleusteq.plasma.entity.UserWorkDetail;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.service.ChangePasswordService;
import in.nucleusteq.plasma.service.EmployeeService;
import in.nucleusteq.plasma.service.UserWorkDetailService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing employee-related operations.
 */
@RestController
@RequestMapping("/plasma/employee")
public class EmployeeController {
    /**
     * EmployeeService.
     */
    @Autowired
    private EmployeeService employeeService;
    /**
     * UserWorkDetailService.
     */
    @Autowired
    private UserWorkDetailService userWorkDetailService;
    /**
     * Change Password Service.
     */
    @Autowired
    private ChangePasswordService changePasswordService;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    /**
     * Endpoint to process recent onboardings.
     * @param recentOnboardInDTO The DTO containing information about recent
     *                           onboardings.
     * @return ResponseEntity containing a SuccessResponse with the result of the
     *         operation.
     */
    @PostMapping("/onboard/recent")
    public ResponseEntity<SuccessResponse> processRecentOnboard(
            @RequestBody RecentOnBoardingInDTO recentOnboardInDTO) {
        LOGGER.info("Received request to process recent onboard: {}", recentOnboardInDTO);
        SuccessResponse successResponse = employeeService.processRecentOnboard(recentOnboardInDTO);
        LOGGER.info("Recent onboard processed successfully");
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Endpoint to retrieve a list of pending onboard users.
     * @return List of PendingOnBoardOutDTO representing pending onboard users.
     */
    @GetMapping("/pending-onboard")
    public List<PendingOnBoardOutDTO> getPendingOnBoardUsers() {
        LOGGER.info("Fetching list of pending onboard users");
        List<PendingOnBoardOutDTO> pendingOnBoardUsers = employeeService.getPendingOnBoardUsers();
        LOGGER.info("Returning list of pending onboard users");
        return pendingOnBoardUsers;
    }
     /**
     * Endpoint to retrieve details of a recent onboarding.
     * @param id The ID of the recent onboarding.
     * @return ResponseEntity containing DetailOfRecentOnBoardingOutDTO representing
     *         onboarding details.
     */
    @GetMapping("/onboard/{id}")
    public ResponseEntity<DetailOfRecentOnBoardingOutDTO> getOnboardingDetails(@PathVariable("id") int id) {
        LOGGER.info("Received request to get onboarding details for id: {}", id);
        DetailOfRecentOnBoardingOutDTO onboardingDetails = employeeService.onboardForm(id);
        LOGGER.info("Returning onboarding details for id: {}", id);
        return new ResponseEntity<>(onboardingDetails, HttpStatus.OK);
    }
    /**
     * Endpoint to reject an onboard request.
     * @param rejectOnBoard The DTO containing information about the rejection.
     * @return ResponseEntity containing a SuccessResponse with the result of the
     *         operation.
     */
    @PostMapping("/reject")
    public ResponseEntity<SuccessResponse> rejectOnboard(@RequestBody RejectOnBoardInDTO rejectOnBoard) {
        LOGGER.info("Received request to reject onboard: {}", rejectOnBoard);
        SuccessResponse successResponse = employeeService.rejectOnboard(rejectOnBoard);
        LOGGER.info("Onboard rejected successfully");
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Endpoint to update onboard details.
     * @param uBoardInDTO The DTO containing updated onboard details.
     * @return ResponseEntity containing a SuccessResponse with the result of the
     *         operation.
     */
    @PostMapping("/update-onboard-details")
    public ResponseEntity<SuccessResponse> updateOnBoardDetails(@RequestBody UpdatedDetailOfOnBoardInDTO uBoardInDTO) {
        LOGGER.info("Received request to update onboard details: {}", uBoardInDTO);
        SuccessResponse successResponse = employeeService.updateDetailOfOnBoard(uBoardInDTO);
        LOGGER.info("Onboard details updated successfully");
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Endpoint to retrieve a list of recent joiners.
     * @return ResponseEntity containing a list of RecentOnBoardingListOutDTO
     *         representing recent joiners.
     */
    @GetMapping("/recent-joiners")
    public ResponseEntity<List<RecentOnBoardingListOutDTO>> getRecentJoiners() {
        LOGGER.info("Fetching list of recent joiners");
        List<RecentOnBoardingListOutDTO> recentJoiners = employeeService.getListOfRecentOnboarding();
        LOGGER.info("Returning list of recent joiners");
        return new ResponseEntity<>(recentJoiners, HttpStatus.OK);
    }
    /**
     * Endpoint to retrieve a list of all employees.
     * @return ResponseEntity containing a list of RecentOnBoardingListOutDTO
     *         representing all employees.
     */
    @GetMapping("/all-employees")
    public ResponseEntity<List<RecentOnBoardingListOutDTO>> getAllEmployees() {
        LOGGER.info("Fetching list of all employees");
        List<RecentOnBoardingListOutDTO> allEmployees = employeeService.getAllEmployees();
        LOGGER.info("Returning list of all employees");
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }
    /**
     * Endpoint to create user work detail.
     * @param userWorkDetail The user work detail to be created.
     * @return ResponseEntity containing the created UserWorkDetail.
     */
    @PostMapping("/create-work-detail")
    public ResponseEntity<UserWorkDetail> createUserWorkDetail(@RequestBody UserWorkDetail userWorkDetail) {
        LOGGER.info("Received request to create user work detail: {}", userWorkDetail);
        UserWorkDetail savedUserWorkDetail = userWorkDetailService.saveUserWorkDetail(userWorkDetail);
        LOGGER.info("User work detail created successfully");
        return new ResponseEntity<>(savedUserWorkDetail, HttpStatus.CREATED);
    }
    /**
     * Endpoint to change password.
     * @param passwordReset
     * @return ResponseEntity containing the success response.
     */
    @PostMapping("/change-password")
    public  ResponseEntity<SuccessResponse> changePassword(@RequestBody PasswordReset passwordReset) {
        SuccessResponse successResponse = changePasswordService.changePassword(passwordReset);
        LOGGER.info("Password changed successfully for employee ID: {}", passwordReset.getEmployeeId());
        return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
    }
}
