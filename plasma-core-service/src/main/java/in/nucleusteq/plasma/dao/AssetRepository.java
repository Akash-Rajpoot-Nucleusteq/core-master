package in.nucleusteq.plasma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import in.nucleusteq.plasma.enums.AssetsStatus;
import in.nucleusteq.plasma.entity.Asset;

/**
 * Repository interface for managing assets.
 */
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    /**
     * Retrieves an asset by its serial number.
     * @param serialNumber The serial number of the asset.
     * @return The asset with the specified serial number.
     */
    Asset findBySerialNumber(@Param("serialNumber") String serialNumber);
    /**
     * Retrieves a list of assets by their status.
     * @param assetsStatus The status of the assets.
     * @return A list of assets with the specified status.
     */
    List<Asset> findByAssetsStatus(AssetsStatus assetsStatus);
}
