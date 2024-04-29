package in.nucleusteq.plasma.dto.in.vendor;

import lombok.Data;
/**
 * Data transfer object (DTO) for representing a Vendor Details.
 */
@Data
public class VendorDetailsInDTO {
    /**
     * Vendor First Name.
     */
    private String vendorFirstName;
    /**
     * Vendor Middle Name.
     */
    private String vendorMiddleName;
    /**
     * Vendor Last Name.
     */
    private String vendorLastName;
    /**
     * Vendor Phone Number.
     */
    private String phoneNumber;
    /**
     * Vendor tax Id.
     */
    private Long taxId;
    /**
     * Vendor ein Number.
     */
    private Long einNumber;
    /**
     * Vendor state Of Incoparation.
     */
    private String stateOfIncoparation;
    /**
     * Vendor company Name.
     */
    private String companyName;
    /**
     * Vendor Address Line 1.
     */
    private String addressLine1;
    /**
     * Vendor Address Line 2.
     */
    private String addressLine2;
    /**
     * Vendor City.
     */
    private String city;
    /**
     * Vendor state.
     */
    private String state;
    /**
     * Vendor Country.
     */
    private String country;
    /**
     * Vendor ZipCode.
     */
    private int zipCode;
}
