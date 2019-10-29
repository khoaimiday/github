package com.myclass.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{
	
	Optional<Role> findById(String id);
	Role findByName(String roleName);
	@Query ("SELECT r.id FROM roles r WHERE r.name = ?1")
	String findIdByName(String name);
}
