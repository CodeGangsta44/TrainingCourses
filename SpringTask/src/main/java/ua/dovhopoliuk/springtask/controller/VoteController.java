package ua.dovhopoliuk.springtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.entity.Vote;
import ua.dovhopoliuk.springtask.service.VoteService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/votes")
public class VoteController {
    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping(value = "/{speaker}")
    public Map<Integer, Long> getVotesOfSpeaker(@PathVariable User speaker) {
        System.out.println("LIST:");
        System.out.println(voteService.getAllVotesBySpeaker(speaker).toString());
        return voteService.getAllVotesBySpeaker(speaker).stream()
                .collect(Collectors.groupingBy(Vote::getMark, Collectors.counting()));
    }

    @PostMapping(value = "/{speaker}")
    public void setVoteForSpeaker(@PathVariable User speaker, @RequestBody int mark) {
        voteService.saveVote(speaker, mark);
    }

    @GetMapping(value = "/{speaker}/me")
    public int getVoteOfCurrentUser(@PathVariable User speaker) {
        return voteService.getVoteOfCurrentUser(speaker);
    }
}
