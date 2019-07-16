package ua.dovhopoliuk.springtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dovhopoliuk.springtask.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findReportById(Long id);
}
