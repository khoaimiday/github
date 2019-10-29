package com.myclass.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.myclass.connection.JDBCConnection;
import com.myclass.dto.UserDto;

public class LoginDAO {
	
	

	String fIND_BY_EMAIL = "SELECT u.id, u.email, u.password, u.fullname, u.avatar, r.name  "
			+ "FROM users u "
			+ "JOIN roles r "
			+ "ON u.role_id = r.id "
			+ "WHERE email = ? ";
	
	
	public UserDto findByEmail(String email) {
		UserDto user = null;
		try (Connection conn = JDBCConnection.getConnection()){
			
			PreparedStatement statement = conn.prepareStatement(fIND_BY_EMAIL);
			statement.setString(1, email);
			
			// Thuc thi truy van lay du lieu
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				user = new UserDto();
				user.setId(result.getInt("id"));
				user.setEmail(email);
				user.setFullname(result.getString("fullname"));
				user.setPassword(result.getString("password"));
				user.setAvatar(result.getString("avatar"));
				user.setRoleName(result.getString("name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
