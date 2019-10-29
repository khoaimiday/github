package com.myclass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.DAO.JobDAO;
import com.myclass.DAO.StatusDAO;
import com.myclass.DAO.TaskDAO;
import com.myclass.DAO.UserDAO;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Job;
import com.myclass.entity.Status;
import com.myclass.entity.Task;
import com.myclass.entity.User;

@WebServlet(urlPatterns = { "/manager/task", "/manager/task/add", "/manager/task/edit", "/manager/task/delede" })
public class TaskController extends HttpServlet {

	TaskDAO taskDAO;
	UserDAO userDAO;
	JobDAO jobDAO;
	StatusDAO statusDAO;

	public TaskController() {
		taskDAO = new TaskDAO();
		userDAO = new UserDAO();
		jobDAO = new JobDAO();
		statusDAO = new StatusDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/manager/task":
			ArrayList<TaskDto> tasks = (ArrayList<TaskDto>) taskDAO.findAllDtoList();
			req.setAttribute("listTask", tasks);
			req.getRequestDispatcher("/views/task/index.jsp").forward(req, resp);
			break;
		case "/manager/task/add":
			getAdd(req, resp);
			break;
		case "/manager/task/edit":
			ArrayList<User> users = (ArrayList<User>) userDAO.findAll();
			ArrayList<Job> jobs = (ArrayList<Job>) jobDAO.findAll();
			ArrayList<Status> status = (ArrayList<Status>) statusDAO.findAll();

			Task task = taskDAO.findById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("task", task);
			req.setAttribute("listUser", users);
			req.setAttribute("listJob", jobs);
			req.setAttribute("listStatus", status);
			req.getRequestDispatcher("/views/task/edit.jsp").forward(req, resp);
			break;
		case "/manager/task/delete":
			int id = Integer.parseInt(req.getParameter("id"));		
			if (taskDAO.delete(id) < 1) {
				req.setAttribute("message", "XOÁ THẤT BẠI");
				req.getRequestDispatcher("/views/task/index.jsp").forward(req, resp);
				return;
			}
			resp.sendRedirect(req.getContextPath() + "/manager/task");
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/manager/task/add":
			postAdd(req, resp);
			break;
		case "/manager/task/edit":
			postEdit(req, resp);
			break;
		}
	}

	public void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<User> users = (ArrayList<User>) userDAO.findAll();
		ArrayList<Job> jobs = (ArrayList<Job>) jobDAO.findAll();
		ArrayList<Status> status = (ArrayList<Status>) statusDAO.findAll();

		req.setAttribute("listUser", users);
		req.setAttribute("listJob", jobs);
		req.setAttribute("listStatus", status);
		req.getRequestDispatcher("/views/task/add.jsp").forward(req, resp);
	}

	public void postAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String name = req.getParameter("name");
			Date startDate = format.parse(req.getParameter("startDate"));
			Date endDate = format.parse(req.getParameter("endDate"));
			int userId = Integer.parseInt(req.getParameter("userId"));
			int jobId = Integer.parseInt(req.getParameter("JobId"));
			int statusId = Integer.parseInt(req.getParameter("statusId"));
			Task task = new Task(name, startDate, endDate, userId, jobId, statusId);
			int result = taskDAO.add(task);
			if (result < 1) {
				req.setAttribute("message", "THEM MOI THAT BAI");
				req.getRequestDispatcher("/views/task/add.jsp").forward(req, resp);
				return;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/manager/task");
	}

	public void postEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			Date startDate = format.parse(req.getParameter("startDate"));
			Date endDate = format.parse(req.getParameter("endDate"));
			int userId = Integer.parseInt(req.getParameter("userId"));
			int jobId = Integer.parseInt(req.getParameter("JobId"));
			int statusId = Integer.parseInt(req.getParameter("statusId"));

			Task task = new Task(id, name, startDate, endDate, userId, jobId, statusId);
			int result = taskDAO.update(task);
			if (result < 1) {
				req.setAttribute("message", "CẬP NHẬT THẤT BẠI");
				req.getRequestDispatcher("/views/task/edit.jsp").forward(req, resp);
				return;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/manager/task");
		return;
	}
}
