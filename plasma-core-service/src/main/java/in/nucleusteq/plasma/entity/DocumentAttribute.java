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
 * Entity class representing a Document Attribute.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class DocumentAttribute {
    /**
     * Attribute Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private int attributeId;
    /**
     * Attribute Name.
     */
    @Column(name = "attribute_name")
    private String attributeName;
    /**
     * Attribute Value.
     */
    @Column(name = "attribute_value")
    private String attributeValue;
    /**
     * Document.
     */
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}
