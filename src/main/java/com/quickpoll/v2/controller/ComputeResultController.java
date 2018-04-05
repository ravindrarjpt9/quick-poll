package com.quickpoll.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quickpoll.domain.Vote;
import com.quickpoll.dto.VoteResult;
import com.quickpoll.repository.VoteRepository;

@RestController("computeResultControllerV2")
@RequestMapping("/v2/")
public class ComputeResultController {

	@Autowired
	private VoteRepository voteRepository;
	
	@RequestMapping(value="/computeresult", method=RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
            VoteResult voteResult = new VoteResult();
            Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
            // Algorithm to count votes

            return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

}
