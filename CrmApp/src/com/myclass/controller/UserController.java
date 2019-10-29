package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.mindrot.jbcrypt.BCrypt;

import com.myclass.DAO.RoleDAO;
import com.myclass.DAO.UserDAO;
import com.myclass.constants.UrlConstants;
import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;

@WebServlet(name = "UserController", urlPatterns = { UrlConstants.USER_LIST, UrlConstants.USER_ADD, UrlConstants.USER_EDIT,
		UrlConstants.USER_DELETE, UrlConstants.USER_DETAILS })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = null;
	private RoleDAO roleDAO = null;

	public UserController() {
		userDAO = new UserDAO();
		roleDAO = new RoleDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.USER_LIST:
			req.setAttribute("users", userDAO.findAllWithRole());
			req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
			break;
		case UrlConstants.USER_ADD:
			req.setAttribute("roles", roleDAO.findAll());
			req.getRequestDispatcher("/views/user/add.jsp").forward(req, resp);
			break;
		case UrlConstants.USER_EDIT:
			getEdit(req, resp);
			break;
		case UrlConstants.USER_DETAILS:
			getDetails(req,resp);
			break;
		case UrlConstants.USER_DELETE:
			getDelete(req, resp);
			break;
		default:
			break;
		}
	}

	private void getDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println(id);
		User user = userDAO.findById(id);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/views/user/details.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case UrlConstants.USER_ADD:
			postAdd(req, resp);
			break;
		case UrlConstants.USER_EDIT:
			postEdit(req, resp);
		default:
			break;
		}
	}

	private void postAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");
		String avatar = req.getParameter("avatar");
		int role = Integer.parseInt(req.getParameter("role"));

		// MA HOA MAT KHAU
		password = BCrypt.hashpw(password, BCrypt.gensalt(12));

		// Khoi tao doi tuong User
		User user = new User(email, password, fullname, avatar, role);

		if (userDAO.add(user) == 1) {
			resp.sendRedirect(req.getContextPath() + UrlConstants.USER_LIST);
			return;
		}
		req.setAttribute("message", "THÊM MỚI THẤT BẠI");
		req.getRequestDispatcher("/views/user/add.jsp").forward(req, resp);
	}

	private void getDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));

		if (userDAO.delete(id) < 1) {
			req.setAttribute("message", "XOÁ KHÔNG THÀNH CÔNG");
			req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + UrlConstants.USER_LIST);
	}

	private void getEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		User user = userDAO.findById(id);
		List<Role> roles = roleDAO.findAll();
		req.setAttribute("user", user);
		req.setAttribute("roles", roles);
		req.getRequestDispatcher("/views/user/edit.jsp").forward(req, resp);
	}

	private void postEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String password = BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt(12));
		String avatar = req.getParameter("avatar");
		int role = Integer.parseInt(req.getParameter("role"));

		User user = new User(id, email, password, fullname, avatar, role);

		if (userDAO.userUpdate(user) < 1) {
			req.setAttribute("message", "CẬP NHẬT THẤT BẠI");
			req.getRequestDispatcher("/views/user/edit.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + UrlConstants.USER_LIST);
	}
}
