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

    @Query("SELECT t FROM Awarded t WHERE t.id = :id")
    List<Awarded> findAwardedByBDid(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Awarded a WHERE a.tender_src_id = :tender_src_id")
    void deleteByTenderSrcId(@Param("tender_src_id") long tenderSrcId);

    @Modifying
    @Transactional
    @Query("UPDATE Awarded a SET a.offersCount = :offersCount, a.valueForOne = :valueForOne, " +
            "a.valueForTwo = :valueForTwo, a.valueForThree = :valueForThree, " +
            "a.suppliersId = :supplierId, a.count = :count, a.date = :date, " +
            "a.value = :value WHERE a.id = :id")
    void updateAwardedById(@Param("id") long id,
                           @Param("offersCount") int offersCount,
                           @Param("valueForOne") double valueForOne,
                           @Param("valueForTwo") double valueForTwo,
                           @Param("valueForThree") double valueForThree,
                           @Param("supplierId") Long supplierId,
                           @Param("count") String count,
                           @Param("date") String date,
                           @Param("value") String value);

    @Query("SELECT t.id FROM Awarded t")
    List<Long> findAllSourceIds();
}
