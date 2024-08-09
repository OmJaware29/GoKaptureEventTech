package com.demo.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.Service.StudentService;
import com.demo.model.Student;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {

	@Autowired
	private StudentService ss;

	@GetMapping(value = { "/", "/index" })
	public ModelAndView m1(ModelAndView m) {
		m.addObject("students", ss.findAll());
		m.setViewName("index");
		return m;

	}

	@PostMapping(value = "/req1")
	public ModelAndView m2(int sid, String sname, String scity, Double spercentage, String b1, ModelAndView m,
			MultipartFile simg, MultipartFile sdoc) throws IOException {
		Student s = new Student();
		s.setSid(sid);
		s.setSname(sname);
		s.setScity(scity);
		s.setSpercentage(spercentage);
		if (simg.getSize() != 0)
			s.setSimg(simg.getBytes());
		if (sdoc.getSize() != 0)
			s.setSdoc(sdoc.getBytes());
		if (b1.equalsIgnoreCase("Add")) {
			s = ss.save(s);
			if (s == null)
				m1(m);
			else {
				m.addObject("status", "Record Added");
				m1(m);
			}
		}
		if (b1.equalsIgnoreCase("Delete")) {
			int check = ss.deleteBysid(s.getSid());

			if (check != 0)
				m.addObject("status", "Record Deleted");
			m1(m);

		}
		if (b1.equalsIgnoreCase("Display")) {
			s = ss.findBysid(s.getSid());
			if (s != null) {
				m.addObject("student", s);
				m.setViewName("first");
			} else
				m1(m);

		}
		if (b1.equalsIgnoreCase("Update")) {

			
			if(simg.getSize()==0)
				s.setSimg(ss.findImgBySid(sid));
			else
				s.setSimg(simg.getBytes());
			
			
			if(sdoc.getSize()==0)
				s.setSdoc(ss.findDocBySid(sid));
			else
				s.setSdoc(sdoc.getBytes());
			s = ss.save(s);
			if (s == null)
				m1(m);
			else {
				m.addObject("status", "Record Updated");
				m1(m);
			}

		}
		if (b1.equalsIgnoreCase("DeleteAll")) {

			ss.deleteAll();
			m1(m);

		}
		return m;
	}

	@GetMapping(value =  {"/findimg/{sid}","reqtodisplay/findimg/{sid}"})
	public void m3(@PathVariable int sid, HttpServletResponse resp) throws IOException {
		byte[] img = ss.findImgBySid(sid);
		OutputStream out = resp.getOutputStream();
		resp.setContentType("image/jpg");
		out.write(img);
		out.flush();
		out.close();

	}

	@GetMapping(value =  {"/finddocs/{sid}","reqtodisplay/finddocs/{sid}"})
	public void m4(@PathVariable int sid, HttpServletResponse resp) throws IOException {
		byte[] img = ss.findDocBySid(sid);
		OutputStream out = resp.getOutputStream();
		resp.setContentType("application/pdf");
		out.write(img);
		out.flush();
		out.close();

	}
	
	
	@GetMapping(value = "reqtodisplay/{sid}")
	public ModelAndView m5(@PathVariable int sid,ModelAndView m)
	{
		Student s= ss.findBysid(sid);
		if (s != null) {
			m.addObject("student", s);
			m.setViewName("first");
		} else
			m1(m);
		return m;
	}
	@PostMapping(value = "/searchbyany")
	public ModelAndView m6(ModelAndView m,String t1) 
	{
		m.addObject("students",ss.findByAny(t1));
		m.setViewName("index");
		return m;
	}

}
