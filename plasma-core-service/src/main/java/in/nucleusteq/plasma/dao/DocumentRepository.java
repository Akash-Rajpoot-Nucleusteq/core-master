package in.nucleusteq.plasma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nucleusteq.plasma.entity.Document;
/**
 * Repository interface for managing document.
 */
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
