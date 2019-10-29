package com.myclass.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "user_courses")
public class Users_Courses implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private Courses courses;
	
	

	public Users_Courses(User user, Courses courses) {
		super();
		this.user = user;
		this.courses = courses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

}
