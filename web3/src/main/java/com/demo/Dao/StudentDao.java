package com.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {

	Student save(Student s);

	List<Student> findAll();

	int deleteBysid(int sid);

	void deleteAll();

	Student findBysid(int sid);

	@Query(value = "select simg from student where sid=?1", nativeQuery = true)
	byte[] findImgBySid(int sid);

	@Query(value = "select sdoc from student where sid=?1", nativeQuery = true)
	byte[] findDocBySid(int sid);

	@Query(value = "select sid,sname,scity,spercentage,sdoc,simg from Student where sid=?1 or spercentage=?1 or sname like %?1% or scity like %?1%",nativeQuery = true)
	List<Student> findByAny(String s1);
}
