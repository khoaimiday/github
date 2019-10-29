package com.myclass.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.entity.Status;

public class StatusDAO {

	public List<Status> findAll() {

		List<Status> status = new ArrayList<Status>();

		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement("Select * FROM status");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Status tut = new Status();
				tut.setId(result.getInt("id"));
				tut.setName(result.getString("name"));
				status.add(tut);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
}
