package in.nucleusteq.plasma.entity;

import in.nucleusteq.plasma.enums.AssetsStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an asset.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "assets")
public class Asset {
    /**
     * Unique identifier for the asset.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assets_id")
    private int assetsId;
    /**
     * Name of the asset.
     */
    @Column(name = "assets_name")
    private String assetsName;
    /**
     * Type of the asset.
     */
    @Column(name = "assets_type")
    private String assetsType;
    /**
     * Serial number of the asset (unique).
     */
    @Column(name = "serial_number", unique = true)
    private String serialNumber;
    /**
     * Entity or source from which the asset is provided.
     */
    @Column(name = "provided_by")
    private String providedBy;
    /**
     * Operating system installed on the asset.
     */
    @Column(name = "operating_system")
    private String operatingSystem;
    /**
     * Location where the asset is assigned or located.
     */
    @Column(name = "work_location")
    private String workLocation;
    /**
     * Status of the asset (e.g., available, in use, under repair).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "assets_status")
    private AssetsStatus assetsStatus;
    /**
     * Asset allocation details associated with this asset.
     */
    @OneToOne(mappedBy = "assetsId", cascade = CascadeType.REMOVE)
    private AssetAllocation assetsAllocation;
}
