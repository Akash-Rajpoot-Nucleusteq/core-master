package in.nucleusteq.plasma.dto.out.vendor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailsOfVendorOutDTO {
    /**
     * Company Name.
     */
    private String companyName;
    /**
     * Vendor ID.
     */
    private String vendorId;
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
     * State Of Incorporation.
     */
    private String stateOfIncoporation;
    /**
     * No of Resources.
     */
    private Long noOfResources;
    /**
     * Tax id.
     */
    private Long taxId;
    /**
     * Ein Number.
     */
    private Long einNumber;
    /**
     * address Line1.
     */
    private String addressLine1;
    /**
     * address Line2.
     */
    private String addressLine2;
    /**
     * City.
     */
    private String city;
    /**
     * State.
     */
    private String state;
    /**
     * Country.
     */
    private String country;
    /**
     * ZipCode.
     */
    private int zipcode;
}
