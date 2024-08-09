package com.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Dao.StudentDao;
import com.demo.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	

	@Autowired
	private StudentDao sd;

	@Override
	public Student save(Student s) {
		
		return sd.save(s);
	}

	@Override
	public List<Student> findAll() {
		
		return sd.findAll();
	}

	@Override
	public int deleteBysid(int sid) {
		
		return sd.deleteBysid(sid);
	}

	@Override
	public void deleteAll() {
		sd.deleteAll();
		
	}

	@Override
	public Student findBysid(int sid) {
		
		return sd.findBysid(sid);
	}

	@Override
	public byte[] findImgBySid(int sid) {
		return sd.findImgBySid(sid);
	}

	@Override
	public byte[] findDocBySid(int sid) {
		return sd.findDocBySid(sid);
	}
	@Override
	public List<Student> findByAny(String s1) {
		return sd.findByAny(s1);
	}

	
		
	

	
	

	
}
