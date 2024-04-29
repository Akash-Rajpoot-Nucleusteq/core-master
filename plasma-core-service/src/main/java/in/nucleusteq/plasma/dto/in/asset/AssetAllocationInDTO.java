package in.nucleusteq.plasma.dto.in.asset;

import java.util.Date;

import lombok.Data;
/**
 * Data transfer object (DTO) for representing a Assert Allocation In Details.
 */
@Data
public class AssetAllocationInDTO {
    /**
     * Asset allocation date.
     */
    private Date allocationDate;
    /**
     * Asset Id.
     */
    private int assetId;
    /**
     * employee's Id.
     */
    private String employeeId;
}
