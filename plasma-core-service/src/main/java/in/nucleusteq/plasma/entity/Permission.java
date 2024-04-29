package in.nucleusteq.plasma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

	 public Permission(Long id, String permission) {
	        this.id = id;
	        this.permission = permission;
	    }

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id")
	    private Long id;

	    @Column(name="permission", nullable = false)
	    private String permission;

	    // enabled as default
	    @Column(name="enabled")
	    private boolean enabled = true;

	    @Column(name="note")
	    private String note;

}