package ua.dovhopoliuk.springtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dovhopoliuk.springtask.entity.ReportRequest;

public interface ReportRequestRepository extends JpaRepository<ReportRequest, Long> {

}
