package study.Dao;

import study.model.User;

import java.sql.*;

public class UserDao {
	private static final String URL = "jdbc:h2:mem:testdb";
	private static final String ID = "sa";
	private static final String PW = "";

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(URL, ID, PW);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public PreparedStatement setPreparedStatement(Connection conn, String sql, String stats) {
		try (
				PreparedStatement ps = conn.prepareStatement(sql)
		) {
			ps.setString(1, stats);
			return ps;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void add(User user) {
		String sql = "insert into USERS (id, name, password) values (?, ?, ?)";
		try (
				Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)
		) {
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User get(String id) {
		String sql = "select * from USERS where ID = ?";
		User user = null;
		try (
				Connection conn = getConnection();
				PreparedStatement ps = setPreparedStatement(conn, sql, id);
				ResultSet rs = ps.executeQuery()
		) {
			rs.next();
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
