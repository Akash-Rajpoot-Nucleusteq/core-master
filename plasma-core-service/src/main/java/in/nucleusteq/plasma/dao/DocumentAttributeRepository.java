package in.nucleusteq.plasma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.DocumentAttribute;
/**
 * Repository interface for managing document attribute.
 */
public interface DocumentAttributeRepository extends JpaRepository<DocumentAttribute, Integer> {
}
