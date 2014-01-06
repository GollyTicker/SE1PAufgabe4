// credits to http://www.vogella.com/articles/MySQLJava/article.html
package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
							+ "user=kbrusch&password=");
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

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		System.out.println("The columns in the table are: ");
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " "
					+ resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			String nr = resultSet.getString("Nr");
			String name = resultSet.getString("Name");
			String email = resultSet.getString("Email");
			System.out.println("Nummer: " + nr + " name: " + name + " email: "
					+ email);
		}
	}

//	@Override
//	public void clearDB() {
//		try {
//			String[] query = { "DROP TABLE IF EXISTS z2r;",
//					" DROP TABLE IF EXISTS Reservierung;",
//					" DROP TABLE IF EXISTS Zusatzleistung;",
//					" DROP TABLE IF EXISTS Gast;",
//					" DROP TABLE IF EXISTS user_summary;" };
//			for (int i = 0; i < query.length; i++) {
//				preparedStatement = connect.prepareStatement(query[i]);
//				preparedStatement.executeUpdate();
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		}
//	}

//	@Override
//	public void buildDB() {
//		try {
//			clearDB();
//			String[] query = {
//					"CREATE TABLE Gast(Nr INT(2) NOT NULL AUTO_INCREMENT, Name VARCHAR(20), Email VARCHAR(30), IstStammKunde BOOLEAN,"
//							+ " PRIMARY KEY(Nr));",
//					"CREATE TABLE Reservierung(nr INT(2) NOT NULL AUTO_INCREMENT,PRIMARY KEY(nr), ZimmerNr INT(2), gast_id int(2), FOREIGN KEY (gast_id) REFERENCES gast(nr));",
//					"CREATE TABLE Zusatzleistung(Nr INT(2) NOT NULL AUTO_INCREMENT,LeistungsArt VARCHAR(30),PRIMARY KEY (Nr)); ",
//					"ALTER TABLE Zusatzleistung ADD UNIQUE (LeistungsArt)",
//					"create table z2r(id int(2) NOT NULL AUTO_INCREMENT,r_id int(2),FOREIGN KEY (r_id) REFERENCES reservierung(nr),z_id int(2),FOREIGN KEY (z_id) REFERENCES zusatzleistung(nr),primary key(id));" };
//			for (int i = 0; i < query.length; i++) {
//				preparedStatement = connect.prepareStatement(query[i]);
//				preparedStatement.executeUpdate();
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		}
//	}

}