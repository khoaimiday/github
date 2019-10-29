package com.myclass.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Role;
import com.myclass.service.RoleService;

@Controller
@RequestMapping("admin/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	/**
	 * Mục đích: Phương thức lấy danh sách quyền. Người tạo: Vũ Đức Huy Ngày tạo:
	 * 24/08/2019 Version: 01
	 */
	@GetMapping("")
	public String index(Model model) {
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "role/index";
	}

	/**
	 * Mục đích: Hiển thị trang thêm mới. Người tạo: Vũ Đức Huy Ngày tạo: 24/08/2019
	 * Version: 01
	 */
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("role", new Role());
		return "role/add";
	}

	/**
	 * Mục đích: Thêm mới quyền vào danh sách Người tạo: Vũ Đức Huy Ngày tạo:
	 * 24/08/2019 Version: 01
	 */
	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("role") Role role, BindingResult error) {
		// Kiem tra nhap lieu
		if (error.hasErrors()) {
			return "role/add";
		}
		// Them moi role vao danh sach
		roleService.insert(role);
		// Chuyen huong ve trang role index
		return "redirect:/admin/role";
	}

	/**
	 * Mục đích: Hiển thị trang cập nhật thông tin quyền của user Người tạo: Vũ Đức
	 * Huy Ngày tạo: 24/08/2019 Version: 01
	 */
	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		// Goi ham findById de lay ra role trung id voi id lay tu url
		Optional<Role> role = roleService.findById(id);
		model.addAttribute("role", role);
		return "role/edit";
	}

	/**
	 * Mục đích: Cập nhật thông tin quyền trong danh sách dựa theo id. Người tạo: Vũ
	 * Đức Huy Ngày tạo: 24/08/2019 Version: 01
	 */
	@PostMapping("edit")
	public String edit(Model model, @Validated @ModelAttribute("role") Role role, BindingResult error) {
		// Bat loi nhap lieu
		if (error.hasErrors()) {
			model.addAttribute("message","Cập nhật thất bại!");
			return "role/edit";
		}
		// Cap nhap role vao danh sach
		roleService.update(role);
		// Chuyen huong ve trang danh sach
		return "redirect:/admin/role";
	}

	/**
	 * Mục đích: Xóa quyền từ danh sách dựa theo id Người tạo: Vũ Đức Huy Ngày tạo:
	 * 24/08/2019 Version: 01
	 */
	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") String id) {
		// Xoa role theo id
		roleService.delete(id);
		// Chuyen huong ve trang danh sach
		return "redirect:/admin/role";
	}
}
