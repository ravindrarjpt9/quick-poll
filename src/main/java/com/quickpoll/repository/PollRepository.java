package com.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.quickpoll.domain.Poll;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {

}
