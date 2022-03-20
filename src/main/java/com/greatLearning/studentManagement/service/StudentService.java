package com.greatLearning.studentManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatLearning.studentManagement.entity.Student;

@Service
public interface StudentService {
	
	List<Student> findAll();

//	List<Student> searchBy(String name, String department);

	Student findById(int id);

	void save(Student student);

	void deleteById(int id);

}
