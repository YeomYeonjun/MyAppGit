package service;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class MemberDto {

	private String id;
	private String password;
	private String name;
	private String gender;
	private String inter;
	private String grade;
	private String self;
	
	public MemberDto() {};
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getInter() {
		return inter;
	}
	public void setInter(String inter) {
		this.inter = inter;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	
}
