package com.tender.tenderdatabase.repositories;

import com.tender.tenderdatabase.entity.Awarded;
import com.tender.tenderdatabase.entity.Purchaser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardedRepository extends JpaRepository<Awarded, Long> {
    @Query("SELECT t FROM Awarded t WHERE t.tender_src_id = :tender_src_id")
    List<Awarded> findAllByTender_src_id(@Param("tender_src_id") long tender_src_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Awarded a WHERE a.tender_src_id = :tender_src_id")
    void deleteByTenderSrcId(@Param("tender_src_id") long tenderSrcId);
}
