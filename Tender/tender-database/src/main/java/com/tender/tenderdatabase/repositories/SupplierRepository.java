package com.tender.tenderdatabase.repositories;

import com.tender.tenderdatabase.entity.Purchaser;
import com.tender.tenderdatabase.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT DISTINCT s " +
            "FROM Supplier s " +
            "JOIN Awarded a ON s.source_id = a.suppliersId " +
            "WHERE a.suppliersId = :sourceId")
    List<Supplier> findSuppliersBySourceId(@Param("sourceId") Long sourceId);

    @Query("SELECT t FROM Supplier t where t.source_id = :sourceId")
    List<Supplier> findBySource_id(@Param("sourceId") Long sourceId);
}
