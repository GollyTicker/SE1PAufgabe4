// credits to http://www.vogella.com/articles/MySQLJava/article.html
package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import a10.gastkomponente.Email;

public class SqlConnecter implements IPersistenzService {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	public SqlConnecter() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connect = DriverManager
					.getConnection("jdbc:mysql://localhost/test?"
							+ "user=myuser&password=");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet readPlainSql(String query) {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void writePlainSql(String query) {
		try {
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ResultSet read(String name, String table, String identifier) {
		try {
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			// query builder
			String query = "select * from " + table + " where " + identifier
					+ " = ".concat("'" + name + "'");
			// print query for debugging
			System.out.println(query);

			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Email emailConvertFromString(String plain) {
		String[] s = plain.split("(@|\\.)");
		String name = s[0];
		String server = s[1];
		String domain = s[2];
		return Email.email(name, server, domain);
	}

	public void create(String query) {
		try {

			preparedStatement = connect.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}