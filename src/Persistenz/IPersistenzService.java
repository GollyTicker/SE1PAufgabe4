package Persistenz;

import java.sql.ResultSet;

public interface IPersistenzService {

	void create(String query);

	ResultSet readByStrAttribute(String name, String table, String identifier);

	ResultSet readPlainSql(String query);

	void writePlainSql(String query);
}
