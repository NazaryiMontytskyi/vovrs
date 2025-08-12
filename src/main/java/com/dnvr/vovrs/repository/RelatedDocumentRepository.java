package com.dnvr.vovrs.repository;

import com.dnvr.vovrs.model.RelatedDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RelatedDocumentRepository extends JpaRepository<RelatedDocument, Long> {

    Optional<RelatedDocument> findById(Long id);
}
