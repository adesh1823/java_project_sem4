package com.yourname.summariser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourname.summariser.Entity.SummaryRecord;

import java.util.List;

public interface SummaryRecordRepository extends JpaRepository<SummaryRecord, Long> {
    List<SummaryRecord> findByUsername(String username);
}
