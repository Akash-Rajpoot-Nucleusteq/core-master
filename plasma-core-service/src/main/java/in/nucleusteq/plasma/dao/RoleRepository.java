package in.nucleusteq.plasma.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.Role;
/**
 * Repository interface for managing role Details.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find By Name.
     * @param name
     * @return optional
     */
    Optional<Role> findByName(String name);
}
