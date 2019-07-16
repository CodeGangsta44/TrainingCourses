package ua.dovhopoliuk.springtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dovhopoliuk.springtask.entity.Conference;

import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    Conference findConferenceById(Long id);
    List<Conference> findAll();
}
