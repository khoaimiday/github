package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.CustomUserDetails;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Khong tim thay User!"); }
		
		// Tao danh sach chua ten quyen
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// Lay ra ten quyen
		String roleName = user.getRole().getName();
		// Luu ten quyen vao danh sach quyen
		authorities.add(new SimpleGrantedAuthority(roleName));
		// Khoi tao doi tuong UserDetails, CustomUserDetails
		CustomUserDetails customUserDetails = new CustomUserDetails(user.getEmail(), user.getPassword(), authorities);
		// Them thuoc tinh Fullname vao doi tuong UserDetails, CustomUserDetails
		customUserDetails.setFullName(user.getFullname());
		customUserDetails.setId(user.getId());
		customUserDetails.setRoleName(user.getRole().getName());
		return customUserDetails;
	}

}
