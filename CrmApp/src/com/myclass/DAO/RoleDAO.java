package com.myclass.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.entity.Role;

public class RoleDAO {

	public List<Role> findAll() {

		List<Role> roles = new ArrayList<Role>();

		try (Connection conn = JDBCConnection.getConnection()) {
			// Buoc 1: Ket noi db

			// Buoc 2: Gui cau truy van db
			// Tao ra cau truy van phu hop voi he quan tri CSDL mySql
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM roles");
			// Thuc thi cau truy van lay du lieu
			ResultSet rs = statement.executeQuery();

			// Buoc 3: Xu ly ket qua tra ve
			while (rs.next()) {
//				System.out.println(rs.getInt("id"));
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getString("description"));
//				System.out.println("---------");

				// Tao entity Role hung du lieu moi dong tra ve tu database
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDes(rs.getString("description"));
				roles.add(role);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}

	public Role findById(int id) {
		Role role = new Role();

		try (Connection conn = JDBCConnection.getConnection()) {

			// Tao cau truy van SQL gui den Database su dung PreparedStatement
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM roles WHERE id = ?");
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery(); // thuc thi cau truy van
			while (rs.next()) {
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDes(rs.getString("description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	public int addRole(Role role) {

		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement("INSERT INTO roles(name,description) VALUES (?,?)");
			// Set du lieu cho cau truy van
			statement.setString(1, role.getName());
			statement.setString(2, role.getDes());

			return statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int updateRole(Role role) {
		String query = "UPDATE roles SET name = ?, description = ? WHERE id = ? ";

		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			// Set du lieu cho cau truy van
			statement.setString(1, role.getName());
			statement.setString(2, role.getDes());
			statement.setInt(3, role.getId());
			
			int result = statement.executeUpdate();
			
			return result;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int delete(int id) {
		
		try (Connection conn = JDBCConnection.getConnection()) {
			String query = "DELETE FROM roles WHERE id = ? ";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			return statement.executeUpdate();

		}  catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}