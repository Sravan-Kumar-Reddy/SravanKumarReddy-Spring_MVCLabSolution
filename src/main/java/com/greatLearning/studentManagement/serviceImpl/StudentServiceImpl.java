package com.greatLearning.studentManagement.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;

@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
		this.session = this.sessionFactory.openSession();
	}

	@Override
	@Transactional
	public List<Student> findAll() {

		Transaction transaction = session.beginTransaction();

		List<Student> studentList = session.createQuery("from Student", Student.class).list();
		transaction.commit();
		return studentList;
	}
	

	/*
	 * @Override
	 *
	 * @Transactional public List<Student> searchBy(String name, String department)
	 * {
	 *
	 * Transaction transaction = session.beginTransaction(); List<Student>
	 * studentList = null; if ((name != null) && (!department.isEmpty()) &&
	 * (department != null) && (!department.isEmpty())) { studentList = session
	 * .createQuery("from Student where name like %" + name +
	 * " and department like %" + department, Student.class) .list(); } else if
	 * (!name.isEmpty()) { studentList =
	 * session.createQuery("from Student where name like %" + name,
	 * Student.class).list(); } else if (!department.isEmpty()) { studentList =
	 * session.createQuery("from Student where department like %" + department,
	 * Student.class).list(); } else { System.out.println("Invalid Arguments"); }
	 *
	 * transaction.commit(); return studentList;
	 *
	 * }
	 */

	@Override
	@Transactional
	public Student findById(int id) {

		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		transaction.commit();

		return student;
	}

	@Override
	@Transactional
	public void save(Student student) {

		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(student);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Error in creating student: ");
			e.printStackTrace();
			transaction.rollback();
		}

	}

	@Override
	@Transactional
	public void deleteById(int id) {

		Transaction transaction = session.beginTransaction();
		try {
			Student student = session.get(Student.class, id);
			session.delete(student);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Error in deleting student with Student Id: " + id);
			e.printStackTrace();
			transaction.rollback();
		}

		return;
	}

}
