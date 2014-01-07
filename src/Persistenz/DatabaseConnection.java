// credits to http://www.vogella.com/articles/MySQLJava/article.html
package Persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection implements IPersistenzService {

	private Connection connect = null;
	private String user = "myuser";

	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connect = DriverManager
					.getConnection("jdbc:mysql://localhost/test?"
							+ "user="+user+"&password=");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet readPlainSql(String query) {
		try {
			Statement statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateByRawQuery(String query) {
		try {
			PreparedStatement preparedStatement = connect
					.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ResultSet readByStrAttribute(String value, String table, String attr) {
		try {
			Statement statement = connect.createStatement();
			String query = "select * from " + table + " where " + attr + " = "
					+ "'" + value + "'";
			System.out.println(query);
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void create(String query) {
		try {
			PreparedStatement preparedStatement = connect
					.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}