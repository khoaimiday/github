package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.DAO.LoginDAO;
import com.myclass.DAO.UserDAO;
import com.myclass.constants.UrlConstants;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

@WebServlet(name = "AuthServlet", urlPatterns = { "/login", "/logout" })
public class AuthController extends HttpServlet {
	private LoginDAO loginDAO = null;

	public AuthController() {
		loginDAO = new LoginDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/login":
			// Lay thong tin dang nhap tu form
			String error = req.getParameter("error");
			if (error == null || error.isEmpty()) {
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);

			} else {
				req.setAttribute("message", "Vui lòng đăng nhập");
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
			}
			break;

		case "/logout":
			HttpSession session = req.getSession();
			if (session != null && session.getAttribute("USER_LOGIN") != null) {
				session.removeAttribute("USER_LOGIN");
			}
			resp.sendRedirect(req.getContextPath() + UrlConstants.LOGIN);
			break;
		default:
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// Lay thong tin user dua theo email
		UserDto user = loginDAO.findByEmail(email);

		// Neu ham findByEmail tra ve null chu khong phai mot tai khoan
		if (user == null) {
			req.setAttribute("message", "Tài khoản không tồn tại");
			req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
			return;
		}

		// Kiem tra mat khau dung hay ko
		if (!BCrypt.checkpw(password, user.getPassword())) {
			req.setAttribute("message", "Mật khẩu không đúng");
			req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
			return;
		}

		// Luu thong tin dang nhao vao session
		HttpSession session = req.getSession(); // Khoi tao doi tuong Session
		session.setAttribute("USER_LOGIN", user); // Luu thong user vao Session co ten la USER_LOGIN
		session.setMaxInactiveInterval(1800);
		resp.sendRedirect(req.getContextPath() + UrlConstants.HOME);
	}
}
