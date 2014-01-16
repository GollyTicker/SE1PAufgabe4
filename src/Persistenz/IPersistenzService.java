package Persistenz;

import java.sql.ResultSet;

import Utilities.TechnicalException;

public interface IPersistenzService {

	void create(String query) throws TechnicalException;

	ResultSet readByStrAttribute(String name, String table, String identifier)
			throws TechnicalException;

	ResultSet readByRawQuery(String query) throws TechnicalException;

	void updateByRawQuery(String query) throws TechnicalException;
}
