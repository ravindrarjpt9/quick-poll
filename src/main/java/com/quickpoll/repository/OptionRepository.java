package com.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;

import com.quickpoll.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long>{

}
