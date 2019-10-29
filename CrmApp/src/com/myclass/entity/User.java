package com.myclass.entity;

public class User {
	private int id;
	private String email;
	private String password;
	private String fullname;
	private String avatar;
	private String phone;
	private String country;
	private int role;

	public User() {

	}

	public User(int id, String email, String password, String fullname, String avatar, int role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.role = role;
	}

	public User(String email, String password, String fullname, String avatar, int role) {
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.role = role;
	}

	public User(int id, String email, String password, String fullname, String avatar, String phone, String country,
			int role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.country = country;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
