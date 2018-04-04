package com.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;

import com.quickpoll.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {

}
