package com.major.club.domain.report.repository;

import com.major.club.domain.club.domain.Club;
import com.major.club.domain.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByClub(Club club);
}
