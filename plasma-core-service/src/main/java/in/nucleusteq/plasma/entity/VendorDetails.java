package in.nucleusteq.plasma.entity;

import in.nucleusteq.plasma.enums.VendorStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * Entity class representing an Vendor Details.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendor_details")
public class VendorDetails {
    /**
     * Vendor Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;
    /**
     * Vendor Id.
     */
    @Column(name = "vendor_id")
    private String vendorId;
    /**
     * Vendor First Name.
     */
    @Column(name = "vendor_fname")
    private String vendorFirstName;
    /**
     * Vendor Middle Name.
     */
    @Column(name = "vendor_mname")
    private String vendorMiddleName;
    /**
     * Vendor Last Name.
     */
    @Column(name = "vendor_lname")
    private String vendorLastName;
    /**
     * Vendor Phone Number.
     */
    @Column(name = "phone_number")
    private String phoneNumber;
    /**
     * Vendor Company Name.
     */
    @Column(name = "company_name")
    private String companyName;
    /**
     * Tax ID.
     */
    @Column(name = "tax_id")
    private Long taxId;
    /**
     * Ein Number.
     */
    @Column(name = "ein_Number")
    private Long einNumber;
    /**
     * Stata of incoparation.
     */
    @Column(name = "state_of_incoparation")
    private String stateOfIncoparation;
    /**
     * No Of Resources.
     */
    @Column(name = "no_of_resources")
    private Long noOfResources;
    /**
     * Vendor Status.
     */
    @Column(name = "vendor_status")
    private VendorStatus vendorStatus;
    /**
     * Vendor Address.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_address_id")
    private UserAddress vendorAddress;
    /**
     * Vendor Document ID.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;
}
