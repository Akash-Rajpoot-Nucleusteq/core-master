package in.nucleusteq.plasma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.FinancialDetail;
/**
 * Repository interface for managing financial Details.
 */
public interface FinancialDetailRepository
        extends
            JpaRepository<FinancialDetail, Integer> {
}
