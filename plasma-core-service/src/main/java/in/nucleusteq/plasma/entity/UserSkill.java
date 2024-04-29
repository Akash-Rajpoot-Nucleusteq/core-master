package in.nucleusteq.plasma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an User Skill.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_skill")
public class UserSkill {
    /**
     * Skill Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * User Work Detail.
     */
    @ManyToOne
    @JoinColumn(name = "user_work_detail_id", referencedColumnName = "id")
    private UserWorkDetail userWorkDetail;
    /**
     * Skill.
     */
    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id")
    private Skills skill;
    /**
     * Constructor.
     * @param userWorkDetail
     * @param skill
     */
    public UserSkill(UserWorkDetail userWorkDetail, Skills skill) {
        super();
        this.userWorkDetail = userWorkDetail;
        this.skill = skill;
    }
}
