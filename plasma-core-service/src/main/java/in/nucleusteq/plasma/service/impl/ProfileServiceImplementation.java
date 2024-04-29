package in.nucleusteq.plasma.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nucleusteq.plasma.dao.EmployeeRepository;
import in.nucleusteq.plasma.dao.UserPersonalDetailRepository;
import in.nucleusteq.plasma.dao.UserWorkDetailRepository;
import in.nucleusteq.plasma.dto.out.profile.ProfileDetailsOutDTO;
import in.nucleusteq.plasma.entity.Employee;
import in.nucleusteq.plasma.entity.UserPersonalDetail;
import in.nucleusteq.plasma.entity.UserWorkDetail;
import in.nucleusteq.plasma.service.ProfileService;

/**
 * Service implementation for managing profile details.
 */
@Service
public class ProfileServiceImplementation implements ProfileService {
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
     * User Work Detail Repository.
     */
    @Autowired
    private UserWorkDetailRepository userWorkDetailsRepository;

    /**
     * Retrieves profile details of an employee.
     * 
     * @param employeeId The ID of the employee.
     * @return ProfileDetailsOutDTO containing the profile details.
     */
    @Override
    public ProfileDetailsOutDTO getProfileDetails(String employeeId) {
        ProfileDetailsOutDTO details = new ProfileDetailsOutDTO();
        Optional<Employee> optionalEmp = employeeRepository.findByUserId(employeeId);
        Employee user = optionalEmp.get();
        Integer personalDetailsId = user.getUserPersonalDetail().getId();
        Optional<UserPersonalDetail> employeeOptional = userPersonalDetailsRepository.findById(personalDetailsId);
        if (employeeOptional.isPresent()) {
            UserPersonalDetail userPer = employeeOptional.get();
            details.setFirstName(userPer.getFirstName());
            details.setLastName(userPer.getLastName());
            details.setBloodGroup(userPer.getBloodGroup());
            details.setDateOfBirth(userPer.getDateOfBirth());
            details.setGender(userPer.getGender());
            details.setPhoneNumber(userPer.getPhoneNumber());
            details.setPersonalEmail(userPer.getPersonalEmailId());
        } else {
            details.setFirstName("Employee not found");
        }
        details.setWorkEmail(user.getEmail());
        Integer workDetailsId = user.getUserWorkDetail().getId();
        Optional<UserWorkDetail> empOptional = userWorkDetailsRepository.findById(workDetailsId);
        if (empOptional.isPresent()) {
            UserWorkDetail userWork = empOptional.get();
            details.setNationality(userWork.getCitizenship());
            details.setStartDate(userWork.getEmploymentStartDate());
            details.setEmployementNature(userWork.getEmploymentNature());
            details.setWorkLocation(userWork.getWorkLocation());
        }
        return details;
    }
}
