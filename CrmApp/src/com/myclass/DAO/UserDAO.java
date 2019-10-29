package com.myclass.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public class UserDAO {

	public List<User> findAll() {
		List<User> users = new ArrayList<User>();

		try (Connection conn = JDBCConnection.getConnection()) {

			// Tao cau truy van toi CSDL
			String query = "SELECT * FROM users ";
			PreparedStatement statement = conn.prepareStatement(query);

			// Gui cau truy van toi CSDL va nhan lay bang ResultSet
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRole(rs.getInt("role_id"));
				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public int add(User user) {

		String query = "INSERT INTO users (email , password, fullname, avatar, role_id) VALUES (?, ?, ?, ?,?) ";
		try (Connection conn = JDBCConnection.getConnection();) {

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRole());

			return statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int delete(int id) {
		try (Connection conn = JDBCConnection.getConnection()) {
			String query = "DELETE FROM users WHERE id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			return statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<UserDto> findAllWithRole() {

		List<UserDto> users = new ArrayList<UserDto>();
		String query = "SELECT u.id, u.email, u.fullname, r.description FROM users u "
				+ "JOIN roles r ON u.role_id = r.id";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Bước 2: Gửi câu truy vấn
			// Tạo ra câu truy vấn phù hợp với hệ quản trị CSDL mysql
			PreparedStatement statement = conn.prepareStatement(query);
			// Thực thi truy vấn lấy dữu liệu
			ResultSet rs = statement.executeQuery();

			// Bước 3: Xử ký kết quả trả về
			while (rs.next()) {
				// Tạo User DTO hứng dữ liệu mỗi dòng trả về từ database
				UserDto user = new UserDto();
				// Set thuộc tính cho User DTO
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setRoleName(rs.getString("description"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public User findById(int id) {
		User user = new User();
		try (Connection conn = JDBCConnection.getConnection()) {
			String query = "SELECT * FROM users WHERE id=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				user.setId(id);
				user.setEmail(result.getString("email"));
				user.setFullname(result.getString("fullname"));
				user.setAvatar(result.getString("avatar"));
				user.setRole(result.getInt("role_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public int userUpdate(User user) {

		String query = "UPDATE users SET email = ?, fullname = ?, password=?, role_id=? WHERE id= ? ";
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getFullname());
			statement.setString(3, user.getPassword());
			statement.setInt(4, user.getRole());
			statement.setInt(5, user.getId());
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
