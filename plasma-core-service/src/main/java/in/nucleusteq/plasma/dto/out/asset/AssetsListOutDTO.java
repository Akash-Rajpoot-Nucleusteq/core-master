package in.nucleusteq.plasma.dto.out.asset;

import in.nucleusteq.plasma.enums.AssetsStatus;
import lombok.Data;
/**
 * Data transfer object (DTO) for representing a Assets list Out DTO.
 */
@Data
public class AssetsListOutDTO {
    /**
     * Assets id.
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
     * Asset's Serial number.
     */
    private String serialNumber;
    /**
     * Asset Provided By.
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
     * Assets Status.
     */
    private AssetsStatus assetsStatus;
}
