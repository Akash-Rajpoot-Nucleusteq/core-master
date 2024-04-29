package in.nucleusteq.plasma.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nucleusteq.plasma.dto.in.asset.AssetAddInDTO;
import in.nucleusteq.plasma.dto.in.asset.AssetAllocationInDTO;
import in.nucleusteq.plasma.dto.out.asset.AssetDetailOutDTO;
import in.nucleusteq.plasma.dto.out.asset.AssetsListOutDTO;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.service.AssetAllocationService;
import in.nucleusteq.plasma.service.AssetService;

/**
 * This class represents the controller for managing assets in the Plasma system.
 * It provides endpoints for adding assets, retrieving asset lists, retrieving
 * unassigned assets, retrieving asset details, and updating asset allocations.
 */
@RestController
@RequestMapping("/plasma/assets")
public class AssetController {
/**
 * assetService.
 */
    @Autowired
    private AssetService assetService;
/**
 * assetAllocationService.
 */
    @Autowired
    private AssetAllocationService assetAllocationService;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AssetController.class);
    /**
     * Endpoint to add a new asset to the system.
     *
     * @param assetAddInDTO The DTO containing information about the asset to be added.
     * @return ResponseEntity containing a SuccessResponse with the result of the asset addition.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addAsset(@RequestBody AssetAddInDTO assetAddInDTO) {
        LOGGER.info("Received request to add asset: {}", assetAddInDTO);
        SuccessResponse successResponse = assetService.addAsset(assetAddInDTO);
        LOGGER.info("Asset added successfully");
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
    /**
     * Endpoint to retrieve a list of all assets in the system.
     * @return ResponseEntity containing a list of AssetsListOutDTO representing the assets.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<AssetsListOutDTO>> getAssetList() {
        LOGGER.info("Received request to get asset list");
        List<AssetsListOutDTO> assetsList = assetService.assetList();
        LOGGER.info("Returning asset list");
        return new ResponseEntity<>(assetsList, HttpStatus.OK);
    }
    /**
     * Endpoint to retrieve a list of unassigned assets in the system.
     * @return ResponseEntity containing a list of AssetsListOutDTO representing unassigned assets.
     */
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/unassigned")
    public ResponseEntity<List<AssetsListOutDTO>> getUnassignedAssets() {
        LOGGER.info("Received request to get unassigned assets");
        List<AssetsListOutDTO> unassignedAssets = assetService.getUnassignedAssets();
        LOGGER.info("Returning unassigned assets");
        return new ResponseEntity<>(unassignedAssets, HttpStatus.OK);
    }
    /**
     * Endpoint to retrieve details of a specific asset by its ID.
     * @param assetId The ID of the asset.
     * @return ResponseEntity containing AssetDetailOutDTO representing the asset details.
     */
    @GetMapping("/{assetId}")
    public ResponseEntity<AssetDetailOutDTO> getAssetDetails(@PathVariable("assetId") int assetId) {
        LOGGER.info("Received request to get asset details for assetId: {}", assetId);
        AssetDetailOutDTO assetDetail = assetService.getAssetDetails(assetId);
        LOGGER.info("Returning asset details for assetId: {}", assetId);
        return new ResponseEntity<>(assetDetail, HttpStatus.OK);
    }
    /**
     * Endpoint for update the asset allocation.
     * @param assetAllocationDTO
     * @return ResposneEntity containing the SuccessResponse.
     */
    @PostMapping("/allocation")
    public ResponseEntity<SuccessResponse> updateAssetAllocation(@RequestBody AssetAllocationInDTO assetAllocationDTO) {
        LOGGER.info("Received request to update asset allocation: {}", assetAllocationDTO);
        SuccessResponse response = assetAllocationService.updateAssetAllocation(assetAllocationDTO);
        LOGGER.info("Asset allocation updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
