package ua.dovhopoliuk.springtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dovhopoliuk.springtask.entity.Report;
import ua.dovhopoliuk.springtask.entity.ReportRequest;
import ua.dovhopoliuk.springtask.entity.User;

import java.util.List;

public interface ReportRequestRepository extends JpaRepository<ReportRequest, Long> {
    List<ReportRequest> findAllByApprovedByModeratorIsTrue();
    void deleteAllBySpeaker(User speaker);
}
