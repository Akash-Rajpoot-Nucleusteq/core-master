package in.nucleusteq.plasma.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.Skills;
/**
 * Repository interface for managing skills.
 */
public interface SkillRepository extends JpaRepository<Skills, Integer> {
    /**
     * Retrieves skills by skills name.
     * @param skillName The name of the skill.
     * @return An optional containing the skills with the specified skill name, or empty if not found.
     */
    Optional<Skills> findBySkillName(String skillName);
}
