package com.tender.tenderdatabase.repositories;

import com.tender.tenderdatabase.entity.Type;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
