package com.myclass.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.ChangePasswod;
import com.myclass.dto.RegisterViewModel;
import com.myclass.service.UserService;

@RestController
@RequestMapping("api/user")
public class ApiUserController {

	@Autowired
	UserService userService;
	
	@GetMapping("")
	public Object index() {
		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("findByEmail")
	public Object get(@RequestParam String email){
		return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("changePassword")
	public ResponseEntity changePass(ChangePasswod changePasswod) {
		if (userService.changePassword(changePasswod)) {
			return new ResponseEntity (HttpStatus.OK);
		}
		return new ResponseEntity (HttpStatus.FAILED_DEPENDENCY);
	} 
	
	@PostMapping( value = "/register", 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object register(@RequestBody RegisterViewModel model) {
		Map<String, String> obj = new HashMap<String, String>();
		
		if (userService.register(model)) {
			obj.put("status", "true");
			obj.put("message", "Đăng ký tài khoản thành công!");
		}else {
			obj.put("status", "false");
			obj.put("message", "Đăng ký tài khoản thất bại!");
		}
		return obj;
	}
}
