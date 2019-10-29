package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.constants.UrlConstants;
import com.myclass.dto.UserDto;

public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String action = req.getServletPath();

		// Kiem tra neu URL == /login thi khong can dang nhap
		if (action.equals("/login") || action.startsWith("/error")) {
			chain.doFilter(request, response);
			return;
		}

		// Kiem tra dang nhap
		HttpSession session = req.getSession();
		if (session.getAttribute("USER_LOGIN") == null) {
			// Chuyen huong ve trang dang nhap
			resp.sendRedirect(req.getContextPath() + "/login?error=123");
			return;
		}

		// =========== PHAN QUYEN =============================
		if (action.startsWith("/admin")) {
			UserDto user = (UserDto) session.getAttribute("USER_LOGIN");
			// Kiem tra quyen cua nguoi dung																					
			if (user.getRoleName().equals("ROLE_ADMIN")) {
				// Neu co quyen ADMIN thi cho phep di vao Servlet dich
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(req.getContextPath() + UrlConstants.BLANK_PAGE_BLANK_403);
			}
		} else if (action.startsWith("/manager")) {
			UserDto user = (UserDto) session.getAttribute("USER_LOGIN");
			if (user.getRoleName().equals("ROLE_MANAGER") || user.getRoleName().equals("ROLE_ADMIN")) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(req.getContextPath() + UrlConstants.LOGIN);
			}
		} else {
			chain.doFilter(request, response);
		}
	}
}
