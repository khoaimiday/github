package com.myclass.dto;

import java.sql.Date;

public class TaskDto {
	private int id;
	private String taskName;
	private Date startDate;
	private Date endDate;
	private String userName;
	private String jobName;
	private String statusName;

	public TaskDto(int id, String name, Date startDate, Date endDate, String userId, String jobId, String statusId) {
		this.id = id;
		this.taskName = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userName = userId;
		this.jobName = jobId;
		this.statusName = statusId;
	}

	public TaskDto() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
