package in.nucleusteq.plasma.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.nucleusteq.plasma.enums.Currency;
import in.nucleusteq.plasma.enums.EmployementStatus;
import in.nucleusteq.plasma.enums.EmploymentNature;
import in.nucleusteq.plasma.enums.OnshoreOrOffshore;
//import in.nucleusteq.plasma.enums.Role;
//import in.nucleusteq.plasma.enums.Role;
import in.nucleusteq.plasma.enums.VisaStatus;
import in.nucleusteq.plasma.enums.WorkMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
 * Entity class representing an User Work Detail.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_work_detail")
public class UserWorkDetail {
    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Visa Status.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "visa_status")
    private VisaStatus visaStatus;
    /**
     * Citizenship.
     */
    @Column(name = "citizenship")
    private String citizenship;
    /**
     * Employment Nature.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_nature")
    private EmploymentNature employmentNature;
    /**
     * Work Mode.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "work_mode")
    private WorkMode workMode;
    /**
     * Employment Start Date.
     */
    @Column(name = "employment_start_date")
    @Temporal(TemporalType.DATE)
    private Date employmentStartDate;
    /**
     * Role.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_work_details_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
    /**
     * Designation.
     */
    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;
    /**
     * Employment Status.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_status")
    private EmployementStatus employmentStatus;
    /**
     * Work Location.
     */
    @Column(name = "work_location")
    private String workLocation;
    /**
     * OnShore Or OffShore.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "onshore_or_offshore")
    private OnshoreOrOffshore onshoreOrOffshore;
    /**
     * Employment Company.
     */
    @Column(name = "employment_Company")
    private String employmentCompany;
    /**
     * Contracting Rate.
     */
    @Column(name = "contracting_rate")
    private int contractingRate;
    /**
     * Contracting Rate Currency.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "contracting_rate_currency")
    private Currency contractingRateCurrency;
    /**
     * Contracting Company.
     */
    @Column(name = "contracting_Company")
    private String contractingCompany;
    /**
     * Recruiter ID.
     */
    @ManyToOne
    @JoinColumn(name = "recruiter")
    private Employee recruiter;
    /**
     * recuiterManager.
     */
    @ManyToOne
    @JoinColumn(name = "recruiter_manager")
    private Employee recruiterManager;
    /**
     * userPersonalDetails.
     */
    @OneToOne(mappedBy = "userWorkDetail")
    private UserPersonalDetail userPersonalDetail;
    /**
     * skills.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userWorkDetail", fetch = FetchType.LAZY)
    private List<UserSkill> skills;
    /**
     * User.
     */
    @OneToOne(mappedBy = "userWorkDetail")
    private Employee user;
}
