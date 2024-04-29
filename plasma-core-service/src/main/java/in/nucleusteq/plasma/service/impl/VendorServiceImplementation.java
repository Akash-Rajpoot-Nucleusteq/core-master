package in.nucleusteq.plasma.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nucleusteq.plasma.dao.UserAddressRepository;
import in.nucleusteq.plasma.dao.VendorDetailsRepository;
import in.nucleusteq.plasma.dto.in.vendor.VendorDetailsInDTO;
import in.nucleusteq.plasma.dto.out.vendor.ApprovedVendorOutDTO;
import in.nucleusteq.plasma.dto.out.vendor.DetailsOfVendorOutDTO;
import in.nucleusteq.plasma.dto.out.vendor.PendingVendorOnBoardOutDTO;
import in.nucleusteq.plasma.enums.VendorStatus;
import in.nucleusteq.plasma.exception.NotFoundException;
import in.nucleusteq.plasma.exception.ResourceNotFoundException;
import in.nucleusteq.plasma.entity.UserAddress;
import in.nucleusteq.plasma.entity.VendorDetails;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.payload.UserIdEmailGeneration;
import in.nucleusteq.plasma.service.VendorService;

@Service
public class VendorServiceImplementation implements VendorService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VendorServiceImplementation.class);
    /**
     * User Address Repository.
     */
    @Autowired
    private UserAddressRepository userAddressRepository;
    /**
     * VendorDetails Repository.
     */
    @Autowired
    private VendorDetailsRepository vendorDetailsRepository;
    /**
     * Process Vendor OnBoard Service implementation.
     * @param vendorOnboardInDTO
     */
    @Override
    public SuccessResponse processVendorOnboard(VendorDetailsInDTO vendorOnboardInDTO) {
        UserAddress userAddress = new UserAddress(
                vendorOnboardInDTO.getAddressLine1(),
                vendorOnboardInDTO.getAddressLine2(),
                vendorOnboardInDTO.getCity(),
                vendorOnboardInDTO.getState(),
                vendorOnboardInDTO.getCountry(),
                vendorOnboardInDTO.getZipCode());
        userAddressRepository.save(userAddress);
        VendorDetails vendorDetails = new VendorDetails();
        vendorDetails.setCompanyName(vendorOnboardInDTO.getCompanyName());
        vendorDetails.setVendorFirstName(vendorOnboardInDTO.getVendorFirstName());
        vendorDetails.setVendorLastName(vendorOnboardInDTO.getVendorLastName());
        vendorDetails.setVendorMiddleName(vendorOnboardInDTO.getVendorMiddleName());
        vendorDetails.setPhoneNumber(vendorOnboardInDTO.getPhoneNumber());
        vendorDetails.setEinNumber(vendorOnboardInDTO.getEinNumber());
        vendorDetails.setTaxId(vendorOnboardInDTO.getTaxId());
        vendorDetails.setStateOfIncoparation(vendorOnboardInDTO.getStateOfIncoparation());
        vendorDetails.setVendorAddress(userAddress);
        vendorDetails.setVendorStatus(VendorStatus.PENDING_APPROVAL);
        vendorDetailsRepository.save(vendorDetails);
        LOGGER.info("New vendor onboarded successfully.");
        return new SuccessResponse("new vendor onborading successfuly", 200);
    }
    /**
     * Retrives list of pending Onboard vendors.
     * @return PendingVendorOnBooardOutDTO
     */
    @Override
    public List<PendingVendorOnBoardOutDTO> getPendingOnBoardVendor() {
        List<VendorDetails> pendingVendor = vendorDetailsRepository
                .findByVendorStatus(VendorStatus.PENDING_APPROVAL);
        return pendingVendor.stream().map(this::mapToPendingVendorOnBoardOutDTO)
                .toList();
    }

    private PendingVendorOnBoardOutDTO mapToPendingVendorOnBoardOutDTO(
            VendorDetails vendor) {
        PendingVendorOnBoardOutDTO dto = new PendingVendorOnBoardOutDTO();
        dto.setCompanyName(vendor.getCompanyName());
        dto.setStateOfIncoporation(vendor.getStateOfIncoparation());
        dto.setVendorFirstName(vendor.getVendorFirstName());
        dto.setVendorLastName(vendor.getVendorLastName());
        dto.setVendorStatus(vendor.getVendorStatus());
        return dto;
    }
    /**
     * get Approved vendor.
     * @return list of approved vendor out dto
     */
    @Override
    public List<ApprovedVendorOutDTO> getApprovedVendor() {
        List<VendorDetails> approvedVendor = vendorDetailsRepository
                .findByVendorStatus(VendorStatus.ACTIVE);
        return approvedVendor.stream().map(this::mapToApprovedVendorOnBoardOutDTO)
                .toList();
    }
    private ApprovedVendorOutDTO mapToApprovedVendorOnBoardOutDTO(
            VendorDetails vendor) {
        ApprovedVendorOutDTO dto = new ApprovedVendorOutDTO();
        dto.setVendorId(vendor.getVendorId());
        dto.setCompanyName(vendor.getCompanyName());
        dto.setStateOfIncoporation(vendor.getStateOfIncoparation());
        dto.setVendorFirstName(vendor.getVendorFirstName());
        dto.setVendorLastName(vendor.getVendorLastName());
        dto.setVendorStatus(vendor.getVendorStatus());
        return dto;
    }
    /**
     * Update details Of OnBoard Vendor.
     * @param id
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse updateDetailOfOnBoard(Long id) {
        VendorDetails vendorDetails = vendorDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendor not found for ID: " + id));
        String newVendorId = UserIdEmailGeneration.generateVendorId(vendorDetailsRepository.findLastVendorId());
        vendorDetails.setVendorId(newVendorId);
        vendorDetails.setVendorStatus(VendorStatus.ACTIVE);
        vendorDetailsRepository.save(vendorDetails);
        return new SuccessResponse("New OnBoarding of vendor updated successfully", 200);
    }
    /**
     * Getting all the details of vendor from the database.
     * @return Retrieves a list of approved Vendor out dto.
     */
    @Override
    public List<ApprovedVendorOutDTO> allOnBoardVendors() {
        List<VendorDetails> vendorDetails =  vendorDetailsRepository.findByVendorStatusIn(Arrays.asList(
                VendorStatus.ACTIVE, VendorStatus.INACTIVE));
        return vendorDetails.stream().map(this::mapToApprovedVendorOnBoardOutDTO)
                .toList();
    }
    /**
     * get vendor details.
     * @param id
     * @return details of vendor out dto
     */
    @Override
    public DetailsOfVendorOutDTO vendorDetails(Long id) {
        VendorDetails vendorDetail = vendorDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendor not found for ID: " + id));
        DetailsOfVendorOutDTO vendorOutDTO = new DetailsOfVendorOutDTO();
        UserAddress vendorAddress = vendorDetail.getVendorAddress();
        vendorOutDTO.setCompanyName(vendorDetail.getCompanyName());
        vendorOutDTO.setVendorFirstName(vendorDetail.getVendorFirstName());
        vendorOutDTO.setVendorMiddleName(vendorDetail.getVendorMiddleName());
        vendorOutDTO.setVendorLastName(vendorDetail.getVendorLastName());
        vendorOutDTO.setVendorId(vendorDetail.getVendorId());
        vendorOutDTO.setEinNumber(vendorDetail.getEinNumber());
        vendorOutDTO.setTaxId(vendorDetail.getTaxId());
        vendorOutDTO.setStateOfIncoporation(vendorDetail.getStateOfIncoparation());
        vendorOutDTO.setAddressLine1(vendorAddress.getAddress1());
        vendorOutDTO.setAddressLine2(vendorAddress.getAddress2());
        vendorOutDTO.setCity(vendorAddress.getCity());
        vendorOutDTO.setState(vendorAddress.getState());
        vendorOutDTO.setCountry(vendorAddress.getCountry());
        vendorOutDTO.setZipcode(vendorAddress.getZipCode());
        return vendorOutDTO;
    }
    /**
     * update vendor servcie implementation.
     * @param id
     * @param vendorDetailsDTO
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse updateVendor(Long id, VendorDetailsInDTO vendorDetailsDTO) {
        VendorDetails existingVendor = vendorDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));
        existingVendor.setCompanyName(vendorDetailsDTO.getCompanyName());
        existingVendor.setEinNumber(vendorDetailsDTO.getEinNumber());
//existingVendor.setNoOfResources(vendorDetailsDTO);
        existingVendor.setPhoneNumber(vendorDetailsDTO.getPhoneNumber());
        existingVendor.setStateOfIncoparation(vendorDetailsDTO.getStateOfIncoparation());
        existingVendor.setTaxId(vendorDetailsDTO.getTaxId());
        existingVendor.setVendorFirstName(vendorDetailsDTO.getVendorFirstName());
        existingVendor.setVendorLastName(vendorDetailsDTO.getVendorLastName());
        existingVendor.setVendorMiddleName(vendorDetailsDTO.getVendorMiddleName());
        vendorDetailsRepository.save(existingVendor);
        UserAddress vendorAddress = existingVendor.getVendorAddress();
        vendorAddress.setAddress1(vendorDetailsDTO.getAddressLine1());
        vendorAddress.setAddress2(vendorDetailsDTO.getAddressLine2());
        vendorAddress.setCity(vendorDetailsDTO.getCity());
        vendorAddress.setCountry(vendorDetailsDTO.getCountry());
        vendorAddress.setState(vendorDetailsDTO.getState());
        vendorAddress.setZipCode(vendorDetailsDTO.getZipCode());
        userAddressRepository.save(vendorAddress);
        return new SuccessResponse("Vendor details updated successfully", 200);
    }
}
