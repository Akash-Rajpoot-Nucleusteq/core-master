package in.nucleusteq.plasma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nucleusteq.plasma.enums.VendorStatus;
import in.nucleusteq.plasma.entity.VendorDetails;
/**
 * Repository interface for managing Vendor Details.
 */
public interface VendorDetailsRepository  extends
JpaRepository<VendorDetails, Long> {
    /**
     * Retrieves the list of vendor details that have vendor status as pending.
     * @param pendingApproval
     * @return a list of pending Vendor Details.
     */
    List<VendorDetails> findByVendorStatus(VendorStatus pendingApproval);
    /**
     * Retrieves the last user ID.
     * @return The last user ID.
     */
    @Query("SELECT MAX(v.vendorId) FROM VendorDetails v")
    String findLastVendorId();
    /**
     * Find by Vendor Status In.
     * @param asList
     * @return a list contains the updated vendor details.
     */
    List<VendorDetails> findByVendorStatusIn(List<VendorStatus> asList);
}
