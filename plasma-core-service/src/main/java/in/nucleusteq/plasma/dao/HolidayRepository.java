package in.nucleusteq.plasma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.Holiday;
/**
 * Repository interface for managing holiday.
 */
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
}
