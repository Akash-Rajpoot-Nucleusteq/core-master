package in.nucleusteq.plasma.dto.in.user;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import in.nucleusteq.plasma.enums.BloodGroup;
import in.nucleusteq.plasma.enums.Currency;
import in.nucleusteq.plasma.enums.EmployementStatus;
import in.nucleusteq.plasma.enums.EmploymentNature;
import in.nucleusteq.plasma.enums.Gender;
import in.nucleusteq.plasma.enums.OnshoreOrOffshore;
import in.nucleusteq.plasma.enums.Role;
import in.nucleusteq.plasma.enums.VisaStatus;
import in.nucleusteq.plasma.enums.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a recent on boarding With Document In DTO.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecentOnBoardingWithDocumentInDto {
    /**
     * Employee's First name.
     */
    private String firstName;
    /**
     * Employee's Middle name.
     */
    private String middleName;
    /**
     * Employee's Last name.
     */
    private String lastName;
    /**
     * Employee's Phone Number.
     */
    private String phoneNumber;
    /**
     * Employee's personal Email Id.
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
     * Employee's Address ZipCode.
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
     * Onshore Or Offshore.
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
     * Contracting Rate Currency.
     */
    private Currency contractingRateCurrency;
    /**
     * Employee's Contracting Company name.
     */
    private String contractingCompany;
    /**
     * Employee's Work Mode.
     */
    private WorkMode workMode;
    /**
     * Employee's Employment Start Date.
     */
    private Date employmentStartDate;
    /**
     * Role.
     */
    private Role role;
    /**
     * Designation.
     */
    private String designation;
    /**
     * Employee's Employment Status.
     */
    private EmployementStatus employementStatus;
    /**
     * Employee's skills.
     */
    private List<String> skill;
    /**
     * Employee's RecuiterId..
     */
    private String recruiter_id;
    /**
     * Photo Attachment.
     */
    private MultipartFile photoAttachment;
    /**
     * Resume Attachment.
     */
    private MultipartFile resumeAttachment;
    /**
     * Aadhar Number.
     */
    private String adharNumber;
    /**
     * Adhar DOB.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adharDob;
    /**
     * Adhar Attachment.
     */
    private MultipartFile adharAttachment;
    /**
     * Rental Agreement Attachment.
     */
    private MultipartFile rentalAgreementAttachment;
    /**
     * Education Certificate Attachment.
     */
    private MultipartFile educationCertificateAttachment;
    /**
     * PhotoId Attachment.
     */
    private MultipartFile photoIdAttachment;
    /**
     * Pan Number.
     */
    private String panNumber;
    /**
     * Pan Attachment.
     */
    private MultipartFile panAttachment;
    /**
     * i94 Travel History Attachment.
     */
    private MultipartFile i94TravelHistoryAttachment;
    /**
     * i9 Copy Attachment.
     */
    private MultipartFile i9CopyAttachment;
    /**
     * Passport Country.
     */
    private String passportCountry;
    /**
     * Passport Issue Date.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date passportIssueDate;
    /**
     * Passport Expire Date.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date passportExpDate;
    /**
     * Passport Number.
     */
    private String passportNumber;
    /**
     * Passport Attachment.
     */
    private MultipartFile passportAttachment;
    /**
     * Work Authorization Number.
     */
    private String workAuthorizationNumber;
    /**
     * Work Authorization Valid Till.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workAuthorizationValidTill;
    /**
     * Work Authorization Attachment.
     */
    private MultipartFile workAuthorizationAttachment;
}
