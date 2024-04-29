package in.nucleusteq.plasma.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.Designation;

/**
 * Repository interface for managing Designation.
 */
public interface DesignationRepository extends JpaRepository<Designation, Integer> {
    /**
     * Retrieves a designation by its name.
     * @param designationName The name of the designation.
     * @return An optional containing the designation with the specified name, or empty if not found.
     */
    Optional<Designation> findByDesignationName(String designationName);
}
