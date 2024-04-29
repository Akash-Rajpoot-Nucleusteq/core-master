package in.nucleusteq.plasma.service.impl;

import in.nucleusteq.plasma.dao.AssetAllocationRepository;
import in.nucleusteq.plasma.dao.AssetRepository;
import in.nucleusteq.plasma.dao.EmployeeRepository;
import in.nucleusteq.plasma.enums.AssetsStatus;
import in.nucleusteq.plasma.exception.NotFoundException;
import in.nucleusteq.plasma.entity.Asset;
import in.nucleusteq.plasma.entity.AssetAllocation;
import in.nucleusteq.plasma.entity.Employee;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.dto.in.asset.AssetAllocationInDTO;
import in.nucleusteq.plasma.service.AssetAllocationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
/**
 * Implementation of the Asset Allocation Service.
 */
@Service
public class AssetAllocationServiceImplementation
        implements
            AssetAllocationService {
    /**
     * Asset Allocation Repository.
     */
    @Autowired
    private AssetAllocationRepository assetAllocationRepository;
    /**
     * Asset Repository.
     */
    @Autowired
    private AssetRepository assetRepository;
    /**
     * Employee Repository.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * Logger.
     */
     private static final Logger LOGGER = LoggerFactory.getLogger(AssetAllocationServiceImplementation.class);
    /**
     * Updates asset allocation based on the provided AssetAllocationInDTO.
     * @param assetAllocationDTO The AssetAllocationInDTO containing allocation details.
     * @return A SuccessResponse indicating the success of the asset allocation.
     * @throws NotFoundException if the asset or employee is not found.
     */
    @Override
    public SuccessResponse updateAssetAllocation(
            AssetAllocationInDTO assetAllocationDTO) {

        Asset asset = assetRepository.findById(assetAllocationDTO.getAssetId())
                .orElseThrow(() -> {
                    LOGGER.error("Asset with ID {} not found.", assetAllocationDTO.getAssetId());
                    return new NotFoundException("Asset with ID "
                            + assetAllocationDTO.getAssetId() + " not found.");
                });

        Employee employee = employeeRepository.findById(assetAllocationDTO.getEmployeeId())
                .orElseThrow(() -> {
                    LOGGER.error("Employee with ID {} not found.", assetAllocationDTO.getEmployeeId());
                    return new NotFoundException("Employee with ID "
                            + assetAllocationDTO.getEmployeeId() + " not found.");
                });
        AssetAllocation assetAllocation = new AssetAllocation();
        assetAllocation
                .setAllocationDate(assetAllocationDTO.getAllocationDate());
        assetAllocation.setAssetsId(asset);
        assetAllocation.setEmployeeId(employee);
        assetAllocationRepository.save(assetAllocation);
        asset.setAssetsStatus(AssetsStatus.ASSIGNED);
        assetRepository.save(asset);
        String logMessage = String.format("Asset '%s' allocated successfully to %s %s",
                asset.getAssetsName(),
                employee.getUserPersonalDetail().getFirstName(),
                employee.getUserPersonalDetail().getLastName());
        LOGGER.info(logMessage);

        return new SuccessResponse(logMessage, HttpStatus.OK.value());
    }
}
