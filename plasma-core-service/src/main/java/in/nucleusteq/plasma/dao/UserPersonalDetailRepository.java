package in.nucleusteq.plasma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.enums.OnBoardingStatus;
import in.nucleusteq.plasma.entity.UserPersonalDetail;
/**
 * Repository interface for managing User Personal Details.
 */
public interface UserPersonalDetailRepository
        extends
            JpaRepository<UserPersonalDetail, Integer> {
    /**
     * Retrieves a list of UserPersonalDetails by their onBoardingStatus is pending.
     * @param pending OnBoardingStatus.
     * @return A list of UserPersonalDetails with the specified onBoardingStatus.
     */
    List<UserPersonalDetail> findByOnBoardingStatus(OnBoardingStatus pending);
}
