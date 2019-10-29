package com.myclass.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;

public class TaskDAO {

	public List<Task> findAll() {

		List<Task> tasks = new ArrayList<Task>();

		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement("Select * FROM tasks");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Task task = new Task();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setStartDate(result.getDate("start_date"));
				task.setEndDate(result.getDate("end_date"));
				task.setUserId(result.getInt("user_id"));
				task.setJobId(result.getInt("job_id"));
				task.setStatusId(result.getInt("status_id"));

				tasks.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public List<TaskDto> findAllDtoList() {
		List<TaskDto> tasks = new ArrayList<TaskDto>();
		String query = "select t.id, t.name, t.start_date, t.end_date, u.fullname , j.name , st.name "
				+ "from tasks t join users u on t.user_id = u.id " + "join jobs j on t.job_id = j.id "
				+ "join status st on t.status_id = st.id ";

		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TaskDto task = new TaskDto();
				task.setId(result.getInt(1));
				task.setTaskName(result.getString(2));
				task.setStartDate(result.getDate(3));
				task.setEndDate(result.getDate(4));
				task.setUserName(result.getString(5));
				task.setJobName(result.getString(6));
				task.setStatusName(result.getString(7));
				tasks.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tasks;
	}

	public int add(Task task) {
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO tasks ( name, start_date, end_date, user_id, job_id, status_id ) VALUES ( ?, ?, ?, ?, ?, ? )");
			statement.setString(1, task.getName());
			statement.setDate(2, new java.sql.Date(task.getStartDate().getTime()));
			statement.setDate(3, new java.sql.Date(task.getEndDate().getTime()));
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, task.getStatusId());
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public Task findById(int id) {
		Task task = new Task();
		String query = "Select * FROM tasks WHERE id = ? ";
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				task.setId(id);
				task.setName(result.getString("name"));
				task.setStartDate(result.getDate("start_date"));
				task.setEndDate(result.getDate("end_date"));
				task.setUserId(result.getInt("user_id"));
				task.setJobId(result.getInt("job_id"));
				task.setStatusId(result.getInt("status_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return task;
	}

	public int update(Task task) {
		String query = "UPDATE tasks SET name = ? , start_date = ? , end_date = ? , user_id = ? , job_id = ? , status_id = ? WHERE id = ? ";
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, task.getName());
			statement.setDate(2, new java.sql.Date(task.getStartDate().getTime()));
			statement.setDate(3, new java.sql.Date(task.getEndDate().getTime()));
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, task.getStatusId());
			statement.setInt(7, task.getId());
			int result = statement.executeUpdate();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int delete(int id) {
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement("DELETE FROM tasks WHERE id = ? ");
			statement.setInt(1, id);
			int result = statement.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
