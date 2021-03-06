package com.quickpoll.v2.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.quickpoll.domain.Poll;
import com.quickpoll.repository.PollRepository;

@RestController("pollControllerV2")
@RequestMapping("/v2/")
public class PollController {

	@Autowired
	private PollRepository pollRepository;
	
	@RequestMapping(value="/polls", method=RequestMethod.GET)
	//@ApiOperation(value = "Retrieves all the polls", response=Poll.class, responseContainer="List")
	public ResponseEntity<Page<Poll>> getAllPolls(Pageable pageable) {
	        Page<Poll> allPolls = pollRepository.findAll(pageable);
	        return new ResponseEntity<>(allPolls, HttpStatus.OK);
	}

	
	@RequestMapping(value="/polls", method=RequestMethod.POST)
	public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {

	        poll = pollRepository.save(poll);
	        // Set the location header for the newly created resource
	        HttpHeaders responseHeaders = new HttpHeaders();
	        URI newPollUri = ServletUriComponentsBuilder
	                                              .fromCurrentRequest()
	                                              .path("/{id}")
	                                              .buildAndExpand(poll.getId())
	                                              .toUri();
	        responseHeaders.setLocation(newPollUri);

	        
	        return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}
	
	/*@RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
	public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
	        Poll p = pollRepository.findOne(pollId);
	        if(p == null) {
                throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }

	        return new ResponseEntity<> (p, HttpStatus.OK);
	}*/

	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
	        // Save the entity
	        Poll p = pollRepository.save(poll);
	        return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
	        pollRepository.deleteById(pollId);
	        return new ResponseEntity<>(HttpStatus.OK);
	}

	/*protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Poll poll = pollRepository.findOne(pollId);
        if(poll == null) {
                throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }*/




}
