package com.tender.tenderdatabase.repositories;

import com.tender.tenderdatabase.entity.Purchaser;
import com.tender.tenderdatabase.entity.Tender;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaserRepository extends JpaRepository<Purchaser, Long> {

    @Query("SELECT t FROM Purchaser t WHERE t.tender_src_id = :tender_src_id")
    List<Purchaser> findAllByTender_src_id(@Param("tender_src_id") long tender_src_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Purchaser p WHERE p.tender_src_id = :tender_src_id")
    void deleteByTenderSrcId(@Param("tender_src_id") long tenderSrcId);

    @Query("SELECT t.sourceId FROM Purchaser t")
    List<Integer> findAllSourceIds();
}
