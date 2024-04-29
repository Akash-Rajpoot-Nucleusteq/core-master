package in.nucleusteq.plasma.service;

import in.nucleusteq.plasma.dto.in.asset.AssetAllocationInDTO;
import in.nucleusteq.plasma.payload.SuccessResponse;
/**
 * Service interface of asset allocation.
 */
public interface AssetAllocationService {
    /**
     * update asset allocation.
     * @param assetAllocationDTO
     * @return SuccessResponse
     */
    public SuccessResponse updateAssetAllocation(AssetAllocationInDTO assetAllocationDTO);
}
