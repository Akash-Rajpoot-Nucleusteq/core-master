package in.nucleusteq.plasma.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an asset allocation.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assets_allocation")
public class AssetAllocation {
    /**
     * Asset Allocation ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assets_allocation_id")
    private int assetsAllocationId;
    /**
     * Allocation Date.
     */
    @Column(name = "allocation_date")
    private Date allocationDate;
    /**
     * Deallocation Date.
     */
    @Column(name = "deallocation_date")
    private Date deallocationDate;
    /**
     * Asset ID.
     */
    @OneToOne
    @JoinColumn(name = "assets_id")
    private Asset assetsId;
    /**
     * EmployeeId.
     */
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;
}
