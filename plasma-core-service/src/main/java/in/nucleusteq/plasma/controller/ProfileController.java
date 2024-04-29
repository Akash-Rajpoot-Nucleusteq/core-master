package in.nucleusteq.plasma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nucleusteq.plasma.dto.out.profile.ProfileDetailsOutDTO;
import in.nucleusteq.plasma.service.ProfileService;


/**
 * Controller class for managing profile-related operations.
 */
@RestController
@RequestMapping("/plasma/profile")
public class ProfileController {
    /**
     * ProfileService.
     */
    @Autowired
    private ProfileService profileService;
    /**
     * Endpoint to retrieve profile details of a specific employee.
     *
     * @param employeeId The ID of the employee whose profile details are to be retrieved.
     * @return ResponseEntity containing the ProfileDetailsOutDTO representing the profile details.
     */
    @GetMapping("/details/{employeeId}")
    public ResponseEntity<ProfileDetailsOutDTO> getProfileDetails(
            @PathVariable("employeeId") String employeeId) {
        ProfileDetailsOutDTO details = profileService.getProfileDetails(employeeId);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
