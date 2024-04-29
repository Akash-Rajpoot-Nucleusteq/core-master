package in.nucleusteq.plasma.dto.out.profile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import in.nucleusteq.plasma.enums.BloodGroup;
import in.nucleusteq.plasma.enums.EmploymentNature;
import in.nucleusteq.plasma.enums.Gender;
import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object (DTO) for representing a Profile Details Out DTO.
 */
@Getter
@Setter
//@JsonIgnoreProperties("Designation")
public class ProfileDetailsOutDTO {
    /**
     * Employee's First Name.
     */
    private String firstName;
    /**
     * Employee's Last Name.
     */
    private String lastName;
    /**
     * Employee's Nationality.
     */
    private String nationality;
    /**
     * Employee's Date Of Birth.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    /**
     * Employee's Gender.
     */
    private Gender gender;
    /**
     * Employee's BloodGroup.
     */
    private BloodGroup bloodGroup;
    /**
     * Employee's Phone Number.
     */
    private String phoneNumber;
    /**
     * Employee's Personal Email.
     */
    private String personalEmail;
    /**
     * Employee's Work Email.
     */
    private String workEmail;
    /**
     * Employee's Start Date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * Employee's Employment Nature.
     */
    private EmploymentNature employementNature;
    /**
     * Employee's Work Location.
     */
    private String workLocation;
}
