package com.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;

import com.quickpoll.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
