package com.myclass.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.entity.Job;

public class JobDAO {

	public List<Job> findAll() {

		List<Job> jobs = new ArrayList<Job>();

		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement("Select * FROM jobs");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Job job = new Job();
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				job.setStartDate(result.getDate("start_date"));
				job.setEndDate(result.getDate("end_date"));
				jobs.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jobs;
	}

	public int add(Job job) {
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO jobs(name, start_date, end_date) VALUES (?,?,?)");
			statement.setString(1, job.getName());
			statement.setDate(2, new java.sql.Date(job.getStartDate().getTime()));
			statement.setDate(3, new java.sql.Date(job.getEndDate().getTime()));
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public Job findById(int id) {
		Job job = new Job();
		String query = "Select * FROM jobs WHERE id = ? ";
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				job.setId(id);
				job.setName(result.getString("name"));
				job.setStartDate(result.getDate("start_date"));
				job.setEndDate(result.getDate("end_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return job;
	}

	public int update(Job job) {
		String query = "UPDATE jobs SET name = ? , start_date = ? , end_date = ? WHERE id = ? ";
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, job.getName());
			statement.setDate(2, new java.sql.Date(job.getStartDate().getTime()));
			statement.setDate(3, new java.sql.Date(job.getEndDate().getTime()));
			statement.setInt(4, job.getId());
			int result = statement.executeUpdate();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int delete(int id) {
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement("DELETE FROM jobs WHERE id = ? ");
			statement.setInt(1, id);
			int result = statement.executeUpdate();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
}
