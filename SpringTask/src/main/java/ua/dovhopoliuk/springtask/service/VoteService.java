package ua.dovhopoliuk.springtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.entity.Vote;
import ua.dovhopoliuk.springtask.exception.VoteNotFoundException;
import ua.dovhopoliuk.springtask.repository.VoteRepository;

import java.util.List;
import java.util.Objects;

/**
 * Class that is a votes service implementation.
 * Using to access the votes repository, such as:
 * getting all the votes for the speaker,
 * saving and getting the vote of the current user
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */
@Service
public class VoteService {
    /** Attribute repository of the votes
     * @see VoteRepository
     */
    private VoteRepository voteRepository;

    /** Attribute service of users
     * @see UserService
     */
    private UserService userService;

    /**
     * Constructor for creating new vote service
     * @param voteRepository - repository(DAO) of all system`s votes
     * @param userService - service for getting access for methods, referenced to User domain
     */
    @Autowired
    public VoteService(VoteRepository voteRepository,
                       UserService userService) {
        this.voteRepository = voteRepository;
        this.userService = userService;
    }

    /**
     * Method for getting all votes by chosen speaker
     * @see User
     * @see ua.dovhopoliuk.springtask.entity.Role
     * @param speaker - chosen speaker
     * @return list of all votes by chosen speaker
     * @see List
     */
    public List<Vote> getAllVotesBySpeaker(User speaker) {
        return voteRepository.findAllBySpeaker(speaker);
    }

    /**
     * Method for saving vote(mark) of chosen speaker by current user
     * @see User
     * @see ua.dovhopoliuk.springtask.entity.Role
     * @param speaker - chosen speaker
     * @param mark - mark for chosen speaker by current user
     */
    public void saveVote(User speaker, int mark) {
        User user = userService.getCurrentUser();

        Vote vote = voteRepository.findBySpeakerAndUser(speaker, user)
                .orElse(Vote.builder()
                        .speaker(speaker)
                        .user(user)
                        .mark(mark).build());

        System.out.println(vote);
        vote.setMark(mark);

        voteRepository.save(vote);
    }

    /**
     * Method for getting vote(mark) of chosen speaker by current user
     * @see User
     * @see ua.dovhopoliuk.springtask.entity.Role
     * @param speaker - chosen speaker
     * @return - mark for chosen speaker by current user
     */
    public int getVoteOfCurrentUser(User speaker) {
        Vote vote = voteRepository.findBySpeakerAndUser(speaker, userService.getCurrentUser())
                .orElseThrow(VoteNotFoundException::new);

        return vote.getMark();
    }
}
