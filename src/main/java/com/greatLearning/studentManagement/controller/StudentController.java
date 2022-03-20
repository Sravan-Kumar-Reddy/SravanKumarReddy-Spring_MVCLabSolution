package com.greatLearning.studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/list")
	public String listBooks(Model theModel) {

		System.out.println("Request Received");
		// get Books from db
		List<Student> studentList = studentService.findAll();

		// add to the spring model
		theModel.addAttribute("Students", studentList);

		return "list-Students";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id,Model theModel) {

		System.out.println("Request Received");
		// get Books from db
		Student student = studentService.findById(id);

		// add to the spring model
		theModel.addAttribute("Student", student);

		return "Student-form";
	}
	
	@RequestMapping("/save")
	public String saveStudent(@RequestParam("studentId") int id,
			@RequestParam("name") String name,@RequestParam("department") String department,@RequestParam("country") String country) {
		
		System.out.println(id);
		Student student;
		if(id!=0)
		{
			student=studentService.findById(id);
			student.setName(name);
			student.setDepartment(department);
			student.setCountry(country);
		}
		else
			student=new Student(name, department, country);
		studentService.save(student);


		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list";
	}


	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {

		studentService.deleteById(theId);

		return "redirect:/students/list";

	}
}
