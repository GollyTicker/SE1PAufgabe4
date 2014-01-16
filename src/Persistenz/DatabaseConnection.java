package Persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Utilities.TechnicalException;
import static Utilities.TechnicalException.throwNewTechnicalException;

public class DatabaseConnection implements IPersistenzService {

	private Connection connect = null;
	private String user = "myuser";
	private String dbName = "test";
	private String password = "";

	public DatabaseConnection() throws TechnicalException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connect = DriverManager
					.getConnection("jdbc:mysql://localhost/" + dbName + "?"
							+ "user=" + user + "&password=" + password);
		} catch (ClassNotFoundException e) {
			throwNewTechnicalException();
		} catch (SQLException e) {
			throwNewTechnicalException();
		}
	}

	public void create(String query) throws TechnicalException {
		try {
			PreparedStatement preparedStatement = connect
					.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throwNewTechnicalException();
		}
	}

	public ResultSet readByStrAttribute(String value, String table, String attr)
			throws TechnicalException {
		try {
			Statement statement = connect.createStatement();
			String query = "select * from " + table + " where " + attr + " = "
					+ "'" + value + "'";
			return statement.executeQuery(query);
		} catch (SQLException e) {
			throwNewTechnicalException();
		}
		return null;
	}

	public ResultSet readByRawQuery(String query) throws TechnicalException {
		try {
			Statement statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			throwNewTechnicalException();
		}
		return null;
	}

	public void updateByRawQuery(String query) throws TechnicalException {
		try {
			PreparedStatement preparedStatement = connect
					.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throwNewTechnicalException();
		}

	}

}