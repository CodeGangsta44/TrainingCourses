package ua.dovhopoliuk.springtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.entity.Vote;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findAllBySpeaker(User speaker);
    Optional<Vote> findBySpeakerAndUser(User speaker, User user);
}
