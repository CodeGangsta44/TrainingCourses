package ua.dovhopoliuk.springtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.entity.Vote;
import ua.dovhopoliuk.springtask.exception.VoteNotFoundException;
import ua.dovhopoliuk.springtask.repository.VoteRepository;

import java.util.List;
import java.util.Objects;

@Service
public class VoteService {
    private VoteRepository voteRepository;
    private UserService userService;

    @Autowired
    public VoteService(VoteRepository voteRepository,
                       UserService userService) {
        this.voteRepository = voteRepository;
        this.userService = userService;
    }

    public List<Vote> getAllVotesBySpeaker(User speaker) {
        return voteRepository.findAllBySpeaker(speaker);
    }

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

    public int getVoteOfCurrentUser(User speaker) {
        Vote vote = voteRepository.findBySpeakerAndUser(speaker, userService.getCurrentUser())
                .orElseThrow(VoteNotFoundException::new);

        return vote.getMark();
    }
}
