package ua.dovhopoliuk.springtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dovhopoliuk.springtask.entity.Conference;
import ua.dovhopoliuk.springtask.entity.Report;
import ua.dovhopoliuk.springtask.entity.User;

import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    Conference findConferenceById(Long id);

    List<Conference> findAll();

    List<Conference> findAllByRegisteredGuestsContainsAndApprovedIsTrueAndFinishedIsFalse(User user);

    List<Conference> findAllByRegisteredGuestsContains(User user);

    List<Conference> findAllByReportsContaining(Report report);

    List<Conference> findAllByApprovedIsTrueAndFinishedIsFalse();

    List<Conference> findAllByApprovedIsFalse();

    List<Conference> findAllByFinishedIsTrue();

    List<Conference> findAllByFinishedIsFalseAndApprovedIsTrue();
}
