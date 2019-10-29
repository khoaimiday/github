package com.myclass.controller;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.DAO.JobDAO;
import com.myclass.constants.UrlConstants;
import com.myclass.entity.Job;

@WebServlet(urlPatterns = { UrlConstants.JOB_LIST, UrlConstants.JOB_ADD, UrlConstants.JOB_DELETE, UrlConstants.JOB_EDIT,
		UrlConstants.JOB_DETAILS })
public class JobController extends HttpServlet {

	private JobDAO jobDao;

	public JobController() {
		jobDao = new JobDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {

		case UrlConstants.JOB_LIST:
			List<Job> jobs = jobDao.findAll();
			req.setAttribute("jobs", jobs);
			req.getRequestDispatcher("/views/job/index.jsp").forward(req, resp);
			break;

		case UrlConstants.JOB_ADD:
			req.getRequestDispatcher("/views/job/add.jsp").forward(req, resp);
			break;

		case UrlConstants.JOB_EDIT:
			getEdit(req, resp);
			break;

		case UrlConstants.JOB_DELETE:
			int id = Integer.parseInt(req.getParameter("id"));
			jobDao.delete(id);
			resp.sendRedirect(req.getContextPath() + UrlConstants.JOB_LIST);
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.JOB_ADD:
			postAdd(req, resp);
			break;
		case UrlConstants.JOB_EDIT:
			postEdit(req, resp);
			break;
		}

	}

	private void postEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lay gia tri tu Form
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String start = req.getParameter("startDate");
		String end = req.getParameter("endDate");

		try {
			Date startDate = formatter.parse(start);
			Date endDate = formatter.parse(end);

			Job job = new Job(id, name, startDate, endDate);
			jobDao.update(job);
			if (jobDao.update(job) < 1) {
				req.setAttribute("message", "CAP NHAT THAT BAI");
				req.getRequestDispatcher("/manager/job/edit.jsp").forward(req, resp);
				return;
			}
			resp.sendRedirect(req.getContextPath() + UrlConstants.JOB_LIST);
			return;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void postAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lay gia tri tu form
		String name = req.getParameter("name");

		// Kiem tra du lieu
		if (name == null || name.isEmpty()) {
			req.setAttribute("message", "VUI LONG NHAP TEN JOB");
			req.getRequestDispatcher("/views/job/add.jsp").forward(req, resp);
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				String start = req.getParameter("startDate");
				String end = req.getParameter("endDate");

				System.out.println(start);
				System.out.println(end);

				Date startDate = formatter.parse(start);
				Date endDate = formatter.parse(end);

				System.out.println(startDate);
				System.out.println(endDate);

				// Khoi tao doi tuong Job
				Job job = new Job(name, startDate, endDate);

				int result = jobDao.add(job);
				if (result < 1) {
					req.setAttribute("message", "THEM MOI THAT BAI");
					req.getRequestDispatcher("/views/job/add.jsp").forward(req, resp);
					return;
				} else {
					resp.sendRedirect(req.getContextPath() + UrlConstants.JOB_LIST);
					return;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	private void getEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Job job = jobDao.findById(id);
		req.setAttribute("job", job);
		req.getRequestDispatcher("/views/job/edit.jsp").forward(req, resp);
	}
}
