package com.dnvr.vovrs.repository;

import com.dnvr.vovrs.model.StructuralUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StructuralUnitRepository extends JpaRepository<StructuralUnit, Long> {

    Optional<StructuralUnit> findById(Long id);
}
