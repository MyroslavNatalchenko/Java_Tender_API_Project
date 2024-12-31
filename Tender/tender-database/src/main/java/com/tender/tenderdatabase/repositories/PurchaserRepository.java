package com.tender.tenderdatabase.repositories;

import com.tender.tenderdatabase.entity.Purchaser;
import com.tender.tenderdatabase.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaserRepository extends JpaRepository<Purchaser, Long> {

}
