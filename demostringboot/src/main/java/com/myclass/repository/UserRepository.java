package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query ("SELECT u FROM users u WHERE u.email = ?1")
	User findByEmail(String email);
	
}
