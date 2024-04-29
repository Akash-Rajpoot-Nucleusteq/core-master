
package in.nucleusteq.plasma.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nucleusteq.plasma.dao.RoleRepository;
import in.nucleusteq.plasma.dao.UserWorkDetailRepository;
import in.nucleusteq.plasma.entity.Role;
import in.nucleusteq.plasma.entity.UserWorkDetail;
import in.nucleusteq.plasma.service.UserWorkDetailService;

/**
 * Service implementation for managing user work details operations.
 */
@Service
public class UserWorkDetailServiceImplementation implements UserWorkDetailService {
    /**
     * Loggers.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserWorkDetailServiceImplementation.class);
    /**
     * UserWorkDetailsRepository.
     */
    @Autowired
    private UserWorkDetailRepository userWorkDetailRepository;
    /**
     * Role Repository.
     */
    @Autowired
    private RoleRepository roleRepository;
    /**
     * saveUserWorkDetails.
     * @param userWorkDetail
     * @return UserWorkDetail
     */
    @Override
    public UserWorkDetail saveUserWorkDetail(UserWorkDetail userWorkDetail) {
        LOGGER.info("Saving the user work Detail");
        Set<Role> validatedRoles = validateAndRetrieveRoles(userWorkDetail.getRoles());
        userWorkDetail.setRoles(validatedRoles);
        return userWorkDetailRepository.save(userWorkDetail);
    }
    /**
     * ValidateAndRetrieveRoles.
     * @param roles
     * @return Role
     */
    private Set<Role> validateAndRetrieveRoles(Set<Role> roles) {
        Set<Role> validatedRoles = new HashSet<>();

        for (Role role : roles) {
            try {
                Role existingRole = roleRepository.findById(role.getRole_id())
                        .orElseThrow(() -> new RuntimeException("Role does not exist with id: " + role.getRole_id()));

                validatedRoles.add(existingRole);
            } catch (Exception e) {
                throw new RuntimeException("Error validating role existence for id: " + role.getRole_id(), e);
            }
        }

        return validatedRoles;
    }
}
