package com.myclass.error;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.constants.UrlConstants;

@WebServlet (urlPatterns = {"/error/403","/error/404"})
public class errorControler extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.BLANK_PAGE_BLANK_403:
			req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
			break;
		case UrlConstants.PAGE_NOT_FOUND_404:
			req.getRequestDispatcher("/views/error/404.jsp").forward(req, resp);
		default:
			break;
		}

	}
}
