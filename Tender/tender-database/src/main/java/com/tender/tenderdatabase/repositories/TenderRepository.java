package com.tender.tenderdatabase.repositories;

import com.tender.tenderdatabase.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {

    @Query("select x from Tender x where x.sourceId not in (:sourceIds)")
    List<Tender> getTendersExcludingSourceIds(@Param("sourceIds") List<Long> sourceIds);

    @Query("select x from Tender x where x.sourceId in (:sourceIds)")
    List<Tender> withSourceIds(List<Long> sourceIds);

    List<Tender> findAllBySourceId(long sourceId);

    @Query("SELECT t.sourceId FROM Tender t")
    List<Integer> findAllSourceIds();

}
