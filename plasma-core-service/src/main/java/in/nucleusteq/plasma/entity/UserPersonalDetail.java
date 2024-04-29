package in.nucleusteq.plasma.entity;

import java.util.Date;
import java.util.List;

import in.nucleusteq.plasma.enums.BloodGroup;
import in.nucleusteq.plasma.enums.Gender;
import in.nucleusteq.plasma.enums.OnBoardingStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an User Personal Detail.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_personal_detail")
public class UserPersonalDetail {
    /**
     * User Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    /**
     * First Name.
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * Middle Name.
     */
    @Column(name = "middle_name")
    private String middleName;
    /**
     * Last Name.
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * Phone Number.
     */
    @Column(name = "phone_number")
    private String phoneNumber;
    /**
     * Personal Email ID.
     */
    @Column(name = "personal_email_id")
    private String personalEmailId;
    /**
     * Date of Birth.
     */
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    /**
     * Gender.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    /**
     * Blood Group.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroup bloodGroup;
    /**
     * On Boarding Status.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "onBoardingStatus")
    private OnBoardingStatus onBoardingStatus;
    /**
     * User Address ID.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_address_id")
    private UserAddress userAddress;
    /**
     * User Work ID.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_work_id")
    private UserWorkDetail userWorkDetail;
    /**
     * User Personal Details Id.
     */
    @OneToOne(mappedBy = "userPersonalDetail")
    private Employee user;
    /**
     * Document Id.
     */
    @OneToMany(mappedBy = "userPersonalDetail", cascade = CascadeType.ALL)
    private List<Document> documents;
    /**
     * Constructs a new UserPersonalDetail object with the specified attributes.
     * @param firstName       the first name of the user.
     * @param middleName      the middle name of the user.
     * @param lastName        the last name of the user.
     * @param phoneNumber     the phone number of the user.
     * @param personalEmailId the personal email ID of the user.
     * @param dateOfBirth     the date of birth of the user.
     * @param gender          the gender of the user.
     * @param bloodGroup      the blood group of the user.
     * @param onBoardingStatus the onboarding status of the user.
     * @param userAddress     the address of the user.
     * @param userWorkDetail  the work details of the user.
     */
    public UserPersonalDetail(String firstName, String middleName, String lastName, String phoneNumber,
            String personalEmailId, Date dateOfBirth, Gender gender, BloodGroup bloodGroup,
            OnBoardingStatus onBoardingStatus, UserAddress userAddress, UserWorkDetail userWorkDetail) {
        super();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.personalEmailId = personalEmailId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.onBoardingStatus = onBoardingStatus;
        this.userAddress = userAddress;
        this.userWorkDetail = userWorkDetail;
    }
}
