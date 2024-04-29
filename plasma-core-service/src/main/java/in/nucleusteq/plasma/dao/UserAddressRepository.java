package in.nucleusteq.plasma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.UserAddress;
/**
 * Repository interface for managing User address.
 */
public interface UserAddressRepository
        extends
            JpaRepository<UserAddress, Integer> {
}
