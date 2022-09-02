package com.dillip.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dillip.api.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
