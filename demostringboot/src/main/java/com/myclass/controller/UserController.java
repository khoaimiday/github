package com.myclass.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.User;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@Controller
@RequestMapping("admin/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	/**
	 * Mục đích: Phương thức lấy danh sách tài khoản Người tạo: Vũ Đức Huy Ngày tạo:
	 * 24/08/2019 Version: 01
	 */
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("users", userService.findAllUsers());
		return "user/index";
	}

	/**
	 * Mục đích: Hiển thị trang thêm mới Người tạo: Vũ Đức Huy Ngày tạo: 24/08/2019
	 * Version: 01
	 */
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.findAll());
		return "user/add";
	}

	/**
	 * Mục đích: Thêm mới tài khoản vào danh sách Người tạo: Vũ Đức Huy Ngày tạo:
	 * 24/08/2019 Version: 01
	 */
	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("user") User user, BindingResult error) {
		if (error.hasErrors()) {
			model.addAttribute("roles", roleService.findAll());
			return "user/add";
		}
		userService.insert(user);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/user";
	}

	/**
	 * Mục đích: Hiển thị trang cập nhật thông tin tài khoản của user Người tạo: Vũ
	 * Đức Huy Ngày tạo: 24/08/2019 Version: 01
	 */
	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("roles", roleService.findAll());
		return "user/add";
	}

	/**
	 * Mục đích: Cập nhật thông tin tài khoản trong danh sách dựa theo id Người tạo:
	 * Vũ Đức Huy Ngày tạo: 24/08/2019 Version: 01
	 */
	@PostMapping("edit")
	public String edit(Model model, @Valid @ModelAttribute("user") User user, BindingResult error) {
		if (error.hasErrors()) {
			model.addAttribute("message","Cập nhật thất bại!");
			model.addAttribute("roles", roleService.findAll());
			return "user/edit";
		}
		// Cập nhật User
		userService.update(user);
		return "redirect:/admin/user";
	}

	/**
	 * Mục đích: Xóa tài khoản từ danh sách dựa theo id Người tạo: Vũ Đức Huy
	 * Ngày tạo: 24/08/2019 Version: 01
	 */
	@GetMapping("delete/{id}")
	public String delete(Model model,@PathVariable("id") String id) {		
		// Xóa User theo id
		userService.delete(id);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/user";
	}
	
	/**
	 * Mục đích: Thông tin chi tiết user theo id Người tạo: Vũ Đức Huy
	 * Ngày tạo: 24/08/2019 Version: 01
	 */
	@GetMapping("profile/{id}")
	public String profile(Model model, @PathVariable("id") String id) {
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("roles", roleService.findAll());
		return "user/profile";
	}
}
