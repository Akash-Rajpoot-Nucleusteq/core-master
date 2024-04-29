package in.nucleusteq.plasma.dto.out.asset;

import in.nucleusteq.plasma.enums.AssetsStatus;
import lombok.Data;
/**
 * Data transfer object (DTO) for representing a Asset Details Out DTO.
 */
@Data
public class AssetDetailOutDTO {
    /**
     * Asset Id.
     */
    private int assetsId;
    /**
     * Asset Name.
     */
    private String assetsName;
    /**
     * Asset Type.
     */
    private String assetsType;
    /**
     * Assets's Serial Number.
     */
    private String serialNumber;
    /**
     * Asset Provide By.
     */
    private String providedBy;
    /**
     * Asset's Operating System.
     */
    private String operatingSystem;
    /**
     * Work Location.
     */
    private String workLocation;
    /**
     * Assets status.
     */
    private AssetsStatus assetsStatus;
}
