package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "videos")
public class Video {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "title")
	private String name;

	private String url;

	@Column(name = "time_count")
	private int timeCount;

	@Column(name = "order_index")
	private int orderIndex;

	@Column(name = "course_id")
	private int coursesId;

	@ManyToOne()
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	private Courses courses;

	public Video() {
		// TODO Auto-generated constructor stub
	}

	public Video(int id, String name, String url, int timeCount, int orderIndex, int coursesId, Courses courses) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.timeCount = timeCount;
		this.orderIndex = orderIndex;
		this.coursesId = coursesId;
		this.courses = courses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public int getCoursesId() {
		return coursesId;
	}

	public void setCoursesId(int coursesId) {
		this.coursesId = coursesId;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

}
