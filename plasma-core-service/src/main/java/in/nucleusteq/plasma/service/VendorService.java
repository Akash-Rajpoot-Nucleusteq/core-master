package in.nucleusteq.plasma.service;

import java.util.List;

import in.nucleusteq.plasma.dto.in.vendor.VendorDetailsInDTO;
import in.nucleusteq.plasma.dto.out.vendor.ApprovedVendorOutDTO;
import in.nucleusteq.plasma.dto.out.vendor.DetailsOfVendorOutDTO;
import in.nucleusteq.plasma.dto.out.vendor.PendingVendorOnBoardOutDTO;
import in.nucleusteq.plasma.payload.SuccessResponse;
/**
 * Service interface for managing vendor operations.
 */
public interface VendorService {
    /**
     * processVendorOnBoard.
     * @param vendorOnboardInDTO
     * @return SuccessResponse
     */
    SuccessResponse processVendorOnboard(VendorDetailsInDTO vendorOnboardInDTO);
    /**
     * getPendingOnBoardVendor.
     * @return list of pendingVendorOnBoardOutDTO
     */
    List<PendingVendorOnBoardOutDTO> getPendingOnBoardVendor();
    /**
     * getApprovedVendor.
     * @return list of approvedVendorOutDTO
     */
    List<ApprovedVendorOutDTO> getApprovedVendor();
    /**
     * Update details Of Onboard vendors.
     * @param id
     * @return SuccessResponse
     */
    SuccessResponse updateDetailOfOnBoard(Long id);
    /**
     * all Onboard Vendors.
     * @return list of approved onBoard Vendors.
     */
    List<ApprovedVendorOutDTO> allOnBoardVendors();
    /**
     * vendor Detail.
     * @param id
     * @return Deatils of Vendor out DTO
     */
    DetailsOfVendorOutDTO vendorDetails(Long id);
    /**
     * update Vendor.
     * @param id
     * @param vendorDetailsInDTO
     * @return SuccessResponse
     */
    SuccessResponse updateVendor(Long id, VendorDetailsInDTO vendorDetailsInDTO);
}
