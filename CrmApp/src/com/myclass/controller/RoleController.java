package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.DAO.RoleDAO;
import com.myclass.constants.UrlConstants;
import com.myclass.entity.Role;

@WebServlet(name = "RoleController", urlPatterns = { UrlConstants.ROLE_ADD, UrlConstants.ROLE_DELETE,
		UrlConstants.ROLE_EDIT, UrlConstants.ROLE_LIST })
public class RoleController extends HttpServlet {
//	private static final long serialVersionUID = 1L;

	// Tao the hien lop RoleDAO
	private RoleDAO roleDAO = null;

	public RoleController() {
		roleDAO = new RoleDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case UrlConstants.ROLE_LIST:
			getList(req, resp);
			break;
		case UrlConstants.ROLE_ADD:
			req.getRequestDispatcher("/views/role/add.jsp").forward(req, resp);
			break;
		case UrlConstants.ROLE_EDIT:
			getEdit(req, resp);
			break;
		case UrlConstants.ROLE_DELETE:
			getDelete(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {

		case UrlConstants.ROLE_ADD:
			postAdd(req, resp);
			break;
		case UrlConstants.ROLE_EDIT:
			postEdit(req, resp);
			break;
		}

	}

	public void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Role> roles = roleDAO.findAll();
		// Goi ham findAll de lay danh sach tu database

		req.setAttribute("roles", roles);
		req.getRequestDispatcher("/views/role/index.jsp").forward(req, resp);
	}

	public void getEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		Role role = roleDAO.findById(id);

		req.setAttribute("role", role);
		req.getRequestDispatcher("/views/role/edit.jsp").forward(req, resp);
	}

	public void getDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));

		if (roleDAO.delete(id) < 1) {
			req.setAttribute("message", "XOÁ THẤT BẠI");
			req.getRequestDispatcher("/views/role/role.jsp").forward(req, resp);
			return;
		}
		// XOA THANH CONG
		resp.sendRedirect(req.getContextPath() + UrlConstants.ROLE_LIST);
	}

	public void postAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lay gia tri
		String name = req.getParameter("name");
		String description = req.getParameter("description");

		// KIEM TRA DU LIEU
		if ((name) == null || (name.isEmpty())) {
			req.setAttribute("message", "VUI LONG NHAP TEN");
			req.getRequestDispatcher("/views/role/add.jsp").forward(req, resp);
		} else {
			// Tao the hien cua Role entity
			// Day du lieu vao de gui len ham addRole
			Role role = new Role();
			role.setName(name);
			role.setDes(description);

			int result = roleDAO.addRole(role);

			if (result < 1) {
				req.setAttribute("message", "Them moi that bai");
				req.getRequestDispatcher("/views/role/add.jsp").forward(req, resp);
				return;
			} else {
				resp.sendRedirect(req.getContextPath() + UrlConstants.ROLE_LIST);
				return;
			}
		}
	}

	public void postEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lay gia tri
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String description = req.getParameter("des");
		Role role = new Role(id, name, description);

		if (roleDAO.updateRole(role) < 1) {
			req.setAttribute("message", "CẬP NHẬT THẤT BẠI");
			req.getRequestDispatcher("/views/role/edit.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + UrlConstants.ROLE_LIST);
		return;
	}
}