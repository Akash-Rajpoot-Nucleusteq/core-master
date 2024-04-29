package in.nucleusteq.plasma.service;

import in.nucleusteq.plasma.dto.out.profile.ProfileDetailsOutDTO;
/**
 * Service interface of profile.
 */
public interface ProfileService {
    /**
     * get Profile details.
     * @param employeeId
     * @return profileDetailOutDTO
     */
    ProfileDetailsOutDTO getProfileDetails(String employeeId);
}
