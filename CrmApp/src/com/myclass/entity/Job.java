package com.myclass.entity;

import java.util.Date;

public class Job {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;

	public Job() {
	}

	public Job(int id, String name, Date startDate, Date endDate) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Job(String name, Date sDate, Date eDate) {
		this.name = name;
		this.startDate = sDate;
		this.endDate = eDate;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
