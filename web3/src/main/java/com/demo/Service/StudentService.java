package com.demo.Service;

import java.util.List;

import com.demo.model.Student;

public interface StudentService {
	
	

	Student save (Student s);
	List<Student> findAll();
	int deleteBysid(int sid);
	void deleteAll();

	Student findBysid(int sid);
	byte[] findImgBySid(int sid);
	byte[] findDocBySid(int sid);
	List<Student> findByAny(String s1);
	
	
	
}
