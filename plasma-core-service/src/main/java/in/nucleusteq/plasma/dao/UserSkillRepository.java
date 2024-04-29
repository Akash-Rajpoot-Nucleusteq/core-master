package in.nucleusteq.plasma.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.UserSkill;
import in.nucleusteq.plasma.entity.UserWorkDetail;
/**
 * Repository interface for managing UserSkill.
 */
public interface UserSkillRepository extends JpaRepository<UserSkill, Integer> {
    /**
     * Retrieves a list of UserSkill by their user work detail.
     * @param userWorkDetail The user work detail.
     * @return A list of UserSkill with the specified user work detail.
     */
     List<UserSkill> findByUserWorkDetail(UserWorkDetail userWorkDetail);
}
