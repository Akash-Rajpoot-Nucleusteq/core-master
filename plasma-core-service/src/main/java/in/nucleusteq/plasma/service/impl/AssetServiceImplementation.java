package in.nucleusteq.plasma.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.nucleusteq.plasma.dao.AssetRepository;
import in.nucleusteq.plasma.dto.in.asset.AssetAddInDTO;
import in.nucleusteq.plasma.dto.out.asset.AssetDetailOutDTO;
import in.nucleusteq.plasma.dto.out.asset.AssetsListOutDTO;
import in.nucleusteq.plasma.enums.AssetsStatus;
import in.nucleusteq.plasma.exception.DuplicateException;
import in.nucleusteq.plasma.exception.NotFoundException;
import in.nucleusteq.plasma.entity.Asset;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.service.AssetService;
/**
 * Implementation of the Asset Service.
 */
@Service
public class AssetServiceImplementation implements AssetService {
    /**
     * Asset Repository.
     */
    @Autowired
    private AssetRepository assetRepository;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AssetServiceImplementation.class);
    /**
     * Adds a new asset based on the provided AssetAddInDTO.
     * @param assetAddInDTO The AssetAddInDTO containing asset details.
     * @return A SuccessResponse indicating the success of adding the asset.
     * @throws DuplicateException if an asset with the same serial number already exists.
     */
    @Override
    public SuccessResponse addAsset(AssetAddInDTO assetAddInDTO) {
        LOGGER.info("Adding asset with serial number: {}", assetAddInDTO.getSerialNumber());

        Asset existingAsset = assetRepository.findBySerialNumber(assetAddInDTO.getSerialNumber());
        if (existingAsset != null) {
            LOGGER.warn("Asset with serial number {} already exists.", assetAddInDTO.getSerialNumber());
            throw new DuplicateException("Asset with serial number " + assetAddInDTO.getSerialNumber() + " already exists.");
        }

            Asset asset = new Asset();
            asset.setAssetsName(assetAddInDTO.getAssetsName());
            asset.setAssetsStatus(AssetsStatus.UNASSIGNED);
            asset.setAssetsType(assetAddInDTO.getAssetsType());
            asset.setOperatingSystem(assetAddInDTO.getOperatingSystem());
            asset.setProvidedBy(assetAddInDTO.getProvidedBy());
            asset.setSerialNumber(assetAddInDTO.getSerialNumber());
            asset.setWorkLocation(assetAddInDTO.getWorkLocation());

            assetRepository.save(asset);
            return new SuccessResponse("Asset added successfully.", HttpStatus.ACCEPTED.value());
    }
    /**
     * Retrieves a list of all assets.
     * @return A list of AssetsListOutDTO representing the assets.
     */
    @Override
    public List<AssetsListOutDTO> assetList() {
        List<Asset> assets = assetRepository.findAll();

        return assets.stream()
                .map(asset -> {
                    AssetsListOutDTO assetsListOutDTO = new AssetsListOutDTO();
                    assetsListOutDTO.setAssetsId(asset.getAssetsId());
                    assetsListOutDTO.setAssetsName(asset.getAssetsName());
                    assetsListOutDTO.setAssetsType(asset.getAssetsType());
                    assetsListOutDTO.setSerialNumber(asset.getSerialNumber());
                    assetsListOutDTO.setProvidedBy(asset.getProvidedBy());
                    assetsListOutDTO.setOperatingSystem(asset.getOperatingSystem());
                    assetsListOutDTO.setWorkLocation(asset.getWorkLocation());
                    assetsListOutDTO.setAssetsStatus(asset.getAssetsStatus());
                    return assetsListOutDTO;
                })
                .collect(Collectors.toList());
    }
    /**
     * Retrieves a list of unassigned assets.
     * @return A list of AssetsListOutDTO representing the unassigned assets.
     */
    @Override
    public List<AssetsListOutDTO> getUnassignedAssets() {
        List<Asset> unassignedAssets = assetRepository.findByAssetsStatus(AssetsStatus.UNASSIGNED);

        return unassignedAssets.stream()
                .map(asset -> {
                    AssetsListOutDTO assetDTO = new AssetsListOutDTO();
                    assetDTO.setAssetsId(asset.getAssetsId());
                    assetDTO.setAssetsName(asset.getAssetsName());
                    assetDTO.setAssetsType(asset.getAssetsType());
                    assetDTO.setSerialNumber(asset.getSerialNumber());
                    assetDTO.setProvidedBy(asset.getProvidedBy());
                    assetDTO.setOperatingSystem(asset.getOperatingSystem());
                    assetDTO.setWorkLocation(asset.getWorkLocation());
                    assetDTO.setAssetsStatus(asset.getAssetsStatus());
                    return assetDTO;
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves details of a specific asset by its ID.
     * @param assetId The ID of the asset to retrieve.
     * @return An AssetDetailOutDTO representing the asset details.
     * @throws NotFoundException if the asset with the specified ID is not found.
     */
    @Override
    public AssetDetailOutDTO getAssetDetails(int assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new NotFoundException("Asset with ID " + assetId + " not found."));

        AssetDetailOutDTO assetDetail = new AssetDetailOutDTO();
        assetDetail.setAssetsId(asset.getAssetsId());
        assetDetail.setAssetsName(asset.getAssetsName());
        assetDetail.setAssetsType(asset.getAssetsType());
        assetDetail.setSerialNumber(asset.getSerialNumber());
        assetDetail.setProvidedBy(asset.getProvidedBy());
        assetDetail.setOperatingSystem(asset.getOperatingSystem());
        assetDetail.setWorkLocation(asset.getWorkLocation());
        assetDetail.setAssetsStatus(asset.getAssetsStatus());
        return assetDetail;
    }
}
