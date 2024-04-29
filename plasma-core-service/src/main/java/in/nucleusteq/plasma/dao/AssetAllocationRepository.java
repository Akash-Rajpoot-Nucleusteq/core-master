package in.nucleusteq.plasma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.AssetAllocation;
/**
 * Repository interface for managing asset allocation.
 */
public interface AssetAllocationRepository
        extends
            JpaRepository<AssetAllocation, Integer> {
}
