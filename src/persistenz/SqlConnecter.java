// credits to http://www.vogella.com/articles/MySQLJava/article.html
package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
							+ "user=myuser&password=654321");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public void create(String query) {
		try {

			preparedStatement = connect.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertIntoDB(String table, ArrayList<String> list)
			throws Exception {
		try {

			if (table == "gast") {

				preparedStatement = connect
						.prepareStatement("insert into gast values(default, ?,?,?);");
				preparedStatement.setString(1, list.get(0));
				preparedStatement.setString(2, list.get(1));
				preparedStatement.setBoolean(3, false);

				preparedStatement.executeUpdate();
			}
			// construction going on here
			if (table == "reservierung") {

				preparedStatement = connect
						.prepareStatement("insert into gast values(default, ?;");
				preparedStatement.setString(1, list.get(0));

				preparedStatement.executeUpdate();
			}
			// construction going on here
			if (table == "zusastzleistung") {

				preparedStatement = connect
						.prepareStatement("insert into gast values(default, ?;");
				preparedStatement.setString(1, list.get(0));

				preparedStatement.executeUpdate();

			}
		} catch (Exception e) {
			throw e;
		} finally {
			close();

		}
	}

	
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}