package com.dillip.api.service;

import java.util.List;

import com.dillip.api.dto.StudentDTO;
import com.dillip.api.request.StudentRequest;

public interface StudentService {
	
	String addStudent(StudentRequest student);
	
	List<StudentDTO> findAllStudentDetails();
	
	StudentDTO findStudentDetailsByRollNumber(int rollNo);
	
	String updateStudentDetailsByRollNumber(int rollNo,StudentRequest student);
	
	String deleteStudentByRollNumber(int rollNo);
	
	String deleteAllStudent();
	
}
