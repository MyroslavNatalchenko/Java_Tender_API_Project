package com.tender.tenderdatabase.repositories;

import com.tender.tenderdatabase.entity.Awarded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardedRepository extends JpaRepository<Awarded, Long> {
}
