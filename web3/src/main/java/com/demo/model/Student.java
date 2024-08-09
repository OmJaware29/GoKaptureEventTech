package com.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sid;
	private String sname;
	private String scity;
	@Column(columnDefinition = "double(5,2)")
	private double spercentage;
	@Column(columnDefinition = "longblob")
	private byte[] simg;
	@Column(columnDefinition = "longblob")
	private byte[] sdoc;
	public byte[] getSimg() {
		return simg;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getScity() {
		return scity;
	}
	public void setScity(String scity) {
		this.scity = scity;
	}
	public double getSpercentage() {
		return spercentage;
	}
	public void setSpercentage(double spercentage) {
		this.spercentage = spercentage;
	}
	public byte[] getSdoc() {
		return sdoc;
	}
	public void setSdoc(byte[] sdoc) {
		this.sdoc = sdoc;
	}
	public void setSimg(byte[] simg) {
		this.simg = simg;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", scity=" + scity + ", spercentage=" + spercentage + "]";
	}
	
	

}
