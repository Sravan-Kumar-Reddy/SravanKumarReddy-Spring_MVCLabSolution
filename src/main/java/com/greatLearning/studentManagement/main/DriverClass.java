package com.greatLearning.studentManagement.main;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;
import com.greatLearning.studentManagement.serviceImpl.StudentServiceImpl;

public class DriverClass {
	
	
	
	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		StudentService studentService = new StudentServiceImpl(sessionFactory);
		
//		Student student = new Student("new Student","new dep","new count");
//		studentService.save(student);
		
//		List<Student> studentList = studentService.findAll();
//		for(Student studentEntry:studentList) {
//			System.out.println(studentEntry);
//		}
		
//		Student student = new Student("another Student","another dep","another count");
//		studentService.save(student);
//		System.out.println(studentService.findById(2));
		studentService.deleteById(2);
		
	}
}
