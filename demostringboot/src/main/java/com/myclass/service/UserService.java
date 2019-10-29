package com.myclass.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.myclass.dto.ChangePasswod;
import com.myclass.dto.RegisterViewModel;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public boolean changePassword(ChangePasswod changePasswod) {
		try {
			User user = userRepository.findByEmail(changePasswod.getEmail());
			String hashed = BCrypt.hashpw(changePasswod.getPassword(), BCrypt.gensalt(12));
			user.setPassword(hashed);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insert(User entity) {
		try {
			String id = UUID.randomUUID().toString();
			entity.setId(id);
			String hashed = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(12));
			entity.setPassword(hashed);
			userRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean register(RegisterViewModel entity) {
		try {
			String roleId = roleRepository.findIdByName(entity.getTypeRole());
			User account = new User();
			// Set String id random cho User
			String id = UUID.randomUUID().toString();
			account.setId(id);
			// HashPassword cho User
			String hash = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(12));
			account.setPassword(hash);
			account.setEmail(entity.getEmail());
			account.setFullname(entity.getFullname());
			account.setRoleId(roleId);
			userRepository.save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(User entity) {
		try {
			userRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void delete(String id) {
		userRepository.deleteById(id);
	}
}
