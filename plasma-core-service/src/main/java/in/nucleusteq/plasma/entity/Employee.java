package in.nucleusteq.plasma.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an Employee.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class Employee {
    /**
     * User ID.
     */
    @Id
    @Column(name = "user_id", unique = true)
    private String userId;
    /**
     * Employee's Email.
     */
    @Column(name = "email", unique = true)
    private String email;
    /**
     * Employee's Password.
     */
    @Column(name = "password")
    private String password;
    /**
     * User Personal Detail.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_personal_id")
    private UserPersonalDetail userPersonalDetail;
    /**
     * User Work Detail.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_work_id")
    private UserWorkDetail userWorkDetail;
    /**
     * Financial Details.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_id")
    private FinancialDetail financialDetail;
    /**
     * Recruiter ID.
     */
    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<UserWorkDetail> recruited;
    /**
     * Recruiter Manager ID.
     */
    @OneToMany(mappedBy = "recruiterManager", cascade = CascadeType.ALL)
    private List<UserWorkDetail> recruiterManaged;
    /**
     * Assets Allocation.
     */
    @OneToMany(mappedBy = "employeeId", fetch = FetchType.LAZY)
    private List<AssetAllocation> assetsAllocation;
}
