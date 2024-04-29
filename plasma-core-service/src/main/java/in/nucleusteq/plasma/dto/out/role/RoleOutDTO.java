package in.nucleusteq.plasma.dto.out.role;

import lombok.Getter;
import lombok.Setter;
/**
 * Data transfer object (DTO) for representing a Role Out DTO.
 */
@Setter
@Getter
public class RoleOutDTO {
    /**
     * Role Id.
     */
    private Long id;
    /**
     * Role Name.
     */
    private String name;
}
