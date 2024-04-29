package in.nucleusteq.plasma.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Document.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "document")
public class Document {
    /**
     * Document ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private int documentId;
    /**
     * Document Name.
     */
    @Column(name = "document_name")
    private String documentName;
    /**
     * Document Path.
     */
    @Column(name = "document_path")
    private String documentPath;
    /**
     * User Personal Details.
     */
    @ManyToOne
    @JoinColumn(name = "user_personal_detail_id")
    private UserPersonalDetail userPersonalDetail;
    /**
     * Document Attributes.
     */
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<DocumentAttribute> documentAttributes;
}
