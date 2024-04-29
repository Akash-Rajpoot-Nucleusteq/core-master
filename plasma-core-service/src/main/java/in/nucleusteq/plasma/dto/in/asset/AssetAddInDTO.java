package in.nucleusteq.plasma.dto.in.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Asset add In Details.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetAddInDTO {
    /**
     * Assets Name.
     */
    private String assetsName;
    /**
     * assets Type.
     */
    private String assetsType;
    /**
     * Serial Number.
     */
    private String serialNumber;
    /**
     * Asset provided By.
     */
    private String providedBy;
    /**
     * Operating System.
     */
    private String operatingSystem;
    /**
     * Work Location.
     */
    private String workLocation;
}
