package in.nucleusteq.plasma.dto.out.user;

import java.util.Date;
import java.util.List;

import in.nucleusteq.plasma.dto.common.SkillDTO;
import in.nucleusteq.plasma.enums.BloodGroup;
import in.nucleusteq.plasma.enums.Currency;
import in.nucleusteq.plasma.enums.EmployementStatus;
import in.nucleusteq.plasma.enums.EmploymentNature;
import in.nucleusteq.plasma.enums.Gender;
import in.nucleusteq.plasma.enums.OnshoreOrOffshore;
import in.nucleusteq.plasma.enums.Role;
import in.nucleusteq.plasma.enums.VisaStatus;
import in.nucleusteq.plasma.enums.WorkMode;
import lombok.Data;


/**
 * Data transfer object (DTO) for representing a Detail Of Recent OnBoarding Out DTO.
 */
@Data
public class DetailOfRecentOnBoardingOutDTO {
    /**
     * Id.
     */
    private int id;
    /**
     * Employee's First Name.
     */
    private String firstName;
    /**
     * Employee's Middle Name.
     */
    private String middleName;
    /**
     * Employee's Last Name.
     */
    private String lastName;
    /**
     * Employee's Phone Number.
     */
    private String phoneNumber;
    /**
     * Employee's Personal Email ID.
     */
    private String personalEmailId;
    /**
     * Employee's Country Name.
     */
    private String country;
    /**
     * Employee's State.
     */
    private String state;
    /**
     * Employee's City.
     */
    private String city;
    /**
     * Employee's Address Line 1.
     */
    private String addressLine1;
    /**
     * Employee's Address Line 2.
     */
    private String addressLine2;
    /**
     * Employee's Address Zipcode.
     */
    private int zipCode;
    /**
     * Employee's Date Of Birth.
     */
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
     * Employee's Work Location.
     */
    private String workLocation;
    /**
     * OnShore Or OffShore.
     */
    private OnshoreOrOffshore onshoreOrOffshore;
    /**
     * Employee's Visa Status.
     */
    private VisaStatus visaStatus;
    /**
     * Employee's Citizenship.
     */
    private String citizenship;
    /**
     * Employee's Employment Nature.
     */
    private EmploymentNature employmentNature;
    /**
     * Employee's Employment Company.
     */
    private String employmentCompany;
    /**
     * Employee's Contracting Rate.
     */
    private int contractingRate;
    /**
     * Employee's Contracting Rate Currency.
     */
    private Currency contractingRateCurrency;
    /**
     * Employee's Contracting Company Name.
     */
    private String contractingCompany;
    /**
     * Employee's Work Mode.
     */
    private WorkMode workMode;
    /**
     * Employee's Employment Start date.
     */
    private Date employmentStartDate;
    /**
     * Role.
     */
    private Role role;
    /**
     * Employee's Designation.
     */
    private String designation;
    /**
     * Employee's EmployementStatus.
     */
    private EmployementStatus employementStatus;
    /**
     * Employee's Skills.
     */
    private List<SkillDTO> skills;
}
