package in.nucleusteq.plasma.service;

import java.util.List;

import in.nucleusteq.plasma.dto.in.asset.AssetAddInDTO;
import in.nucleusteq.plasma.dto.out.asset.AssetDetailOutDTO;
import in.nucleusteq.plasma.dto.out.asset.AssetsListOutDTO;
import in.nucleusteq.plasma.payload.SuccessResponse;

/**
 * Service interface of asset.
 */
public interface AssetService {
    /**
     * addAsset.
     * @param assetAddInDTO
     * @return SuccessResponse
     */
    public SuccessResponse addAsset(AssetAddInDTO assetAddInDTO);
    /**
     * assetList.
     * @return AssetsListOutDTO
     */
    public List<AssetsListOutDTO> assetList();
    /**
     * getUnassignedAssets.
     * @return AssetsListOutDTO
     */
    public List<AssetsListOutDTO> getUnassignedAssets();
    /**
     * getAssetDetails.
     * @param assetId
     * @return AssetDetailOutDTO
     */
    public AssetDetailOutDTO getAssetDetails(int assetId);
}
