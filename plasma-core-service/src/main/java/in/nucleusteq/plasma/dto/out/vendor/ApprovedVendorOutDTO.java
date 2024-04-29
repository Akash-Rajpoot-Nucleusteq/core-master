package in.nucleusteq.plasma.dto.out.vendor;

import in.nucleusteq.plasma.enums.VendorStatus;
import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a approved Vendor Out DTO.
 */
@Getter
@Setter
public class ApprovedVendorOutDTO {
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
     * Vendor Status.
     */
    private VendorStatus vendorStatus;
}
