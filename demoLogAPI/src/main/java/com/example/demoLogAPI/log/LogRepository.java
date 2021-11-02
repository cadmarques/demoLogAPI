package com.example.demoLogAPI.log;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogDefaultRecord, LocalDateTime> {

	
	Page<LogDefaultRecord> findByDateTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
	
}
