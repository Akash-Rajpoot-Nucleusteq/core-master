package in.nucleusteq.plasma.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nucleusteq.plasma.dto.in.vendor.VendorDetailsInDTO;
import in.nucleusteq.plasma.dto.out.vendor.ApprovedVendorOutDTO;
import in.nucleusteq.plasma.dto.out.vendor.DetailsOfVendorOutDTO;
import in.nucleusteq.plasma.dto.out.vendor.PendingVendorOnBoardOutDTO;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.service.VendorService;
/**
 * This class represents the controller for managing vendor in the Plasma system.
 * It provides endpoints for adding vendor, retrieving vendor lists, retrieving
 * active vendor, retrieving vendors details, and updating vendor details.
 */
@RestController
@RequestMapping("/plasma/vendor")
public class VendorController {
    /**
     * Vendor Service.
     */
    @Autowired
    private VendorService vendorService;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);
    /**
     * Endpoint to process onboarding of vendor.
     * @param vendorOnboardInDTO The DTO containing information about recent
     *                           onboardings.
     * @return ResponseEntity containing a SuccessResponse with the result of the
     *         operation.
     */
    @PostMapping("/onboard")
    public ResponseEntity<SuccessResponse> processRecentOnboard(
            @RequestBody VendorDetailsInDTO vendorOnboardInDTO) {
        LOGGER.info("Received request to process vendor onboard: {}", vendorOnboardInDTO);
        SuccessResponse successResponse = vendorService.processVendorOnboard(vendorOnboardInDTO);
        LOGGER.info("Vendor onboard processed successfully");
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Endpoint to retrieve a list of pending onboard vendor.
     * @return List of PendingOnBoardOutDTO representing pending onboard vendor.
     */
    @GetMapping("/pending")
    public List<PendingVendorOnBoardOutDTO> getPendingOnBoardVendor() {
        LOGGER.info("Fetching list of pending onboard users");
        List<PendingVendorOnBoardOutDTO> pendingOnBoardVendor = vendorService.getPendingOnBoardVendor();
        LOGGER.info("Returning list of pending onboard users");
        return pendingOnBoardVendor;
    }
    /**
     * Endpoint to retrieve a list of approved onboard vendor.
     * @return List of ApprovedOnBoardOutDTO representing approved onboard vendor.
     */
    @GetMapping("/approved")
    public List<ApprovedVendorOutDTO> getApprovedOnBoardVendor() {
        LOGGER.info("Fetching list of approved onboard users");
        List<ApprovedVendorOutDTO> approvedVendor = vendorService.getApprovedVendor();
        LOGGER.info("Returning list of approved onboard users");
        return approvedVendor;
    }
    /**
     * Endpoints to update details of vendor after approve onBoard.
     * @param id
     * @return SuccessResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateOnBoardDetails(@PathVariable("id")  Long id) {
        LOGGER.info("Received request to update onboard vendor details of", id);
        SuccessResponse successResponse = vendorService.updateDetailOfOnBoard(id);
        LOGGER.info("Onboard vendor details updated successfully");
        return ResponseEntity.ok(successResponse);
    }
    /**
     * Endpoint to retrieve a list of all onBoard vendors.
     * @return Retrieves a list of approvedOnBoardOutDTO
     */
    @GetMapping("/all")
    public List<ApprovedVendorOutDTO> allOnBoardedVendor() {
        LOGGER.info("Fetching list of all onboard vendors");
        List<ApprovedVendorOutDTO> vendorList = vendorService.allOnBoardVendors();
        LOGGER.info("Onboard vendor details updated successfully");
        return vendorList;
    }
    /**
    * Endpoint to retrieve details of a vendor.
    * @param id The ID of the vendor.
    * @return ResponseEntity containing DetailOfVendorOutDTO representing
    *         vendor details.
    */
   @GetMapping("/details/{id}")
   public ResponseEntity<DetailsOfVendorOutDTO> getVendorDetails(@PathVariable("id") Long id) {
       LOGGER.info("Received request to get vendor details for id: {}", id);
       DetailsOfVendorOutDTO vendorDetails = vendorService.vendorDetails(id);
       LOGGER.info("Returning vendor details for id: {}", id);
       return new ResponseEntity<>(vendorDetails, HttpStatus.OK);
   }
   /**
    * Update Vendor Details.
    * @param id
    * @param vendorDetailsInDTO
    * @return SuccessResponse
    */
   @PutMapping("/update/{id}")
   public ResponseEntity<SuccessResponse> updateVendorDetails(
           @PathVariable("id") Long id, @RequestBody VendorDetailsInDTO vendorDetailsInDTO) {
       LOGGER.info("Received request to update vendor onboard: {}", vendorDetailsInDTO);
       SuccessResponse successResponse = vendorService.updateVendor(id, vendorDetailsInDTO);
       LOGGER.info("Vendor update processed successfully");
       return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
   }
}
