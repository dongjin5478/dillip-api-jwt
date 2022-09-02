package com.dillip.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dillip.api.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}
