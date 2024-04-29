package in.nucleusteq.plasma.dto.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a skill.
 */
@AllArgsConstructor
@NoArgsConstructor
public class SkillDTO {
    /**
     * skillName.
     */
    private String skillName;
    /**
     * Retrieves the name of the skill.
     * @return The name of the skill.
     */
    public String getSkillName() {
        return skillName;
    }
    /**
     * Sets the name of the skill.
     * @param skillName The name of the skill to set.
     */
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
