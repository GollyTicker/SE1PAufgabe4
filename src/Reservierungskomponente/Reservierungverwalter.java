package Reservierungskomponente;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Reservierungskomponente.ReservierungTyp.reservierung;
import static Reservierungskomponente.ZusatzleistungTyp.zusatzleistung;
import Persistenz.IPersistenzService;
import Utilities.TechnicalException;
import static Utilities.TechnicalException.throwNewTechnicalException;

public class Reservierungverwalter {

	private IPersistenzService persServ;

	public Reservierungverwalter(IPersistenzService persServ) {
		this.persServ = persServ;
	}

	public ZusatzleistungTyp erzeugeZusatzleistung(String name) throws TechnicalException {
		String query = "insert into Zusatzleistung values(default,'" + name
				+ "')";
		persServ.create(query);
		return sucheZusatzleistung(name); // nicht im interface. private methode
	}

	private ZusatzleistungTyp sucheZusatzleistung(String name) throws TechnicalException {
		ResultSet res = persServ.readByStrAttribute(name, "zusatzleistung",
				"LeistungsArt");
		try {
			while (res.next()) {
				Integer nr = res.getInt("nr");
				String leistung = res.getString("Leistungsart");
				return zusatzleistung(nr, leistung);
			}
		} catch (Exception e) {
			throwNewTechnicalException();
		}
		return null;
	}

	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr) throws TechnicalException {
		String query = "insert into Reservierung values(default," + zimmerNr
				+ "," + gastNr + ");";
		persServ.create(query);
		return sucheReservierung(gastNr, zimmerNr);		// auch eine private "getter" methode
	}

	public ReservierungTyp sucheReservierung(Integer gastNr, Integer zimmerNr) throws TechnicalException {
		ResultSet res = persServ.readByStrAttribute(gastNr.toString(),
				"Reservierung", "gastID");
		Integer nr = 0;
		Integer zimmernr = 0;
		try {
			while (res.next()) {
				nr = res.getInt("nr");
				zimmernr = res.getInt("zimmerNr");
			}
		} catch (Exception e) {
			throwNewTechnicalException();
		}
		ReservierungTyp r = reservierung(nr, zimmernr);
		return r;
	}

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) throws TechnicalException {

		String query = "insert into umfasst values(default," + reservierungNr
				+ "," + zusatzleistungNr + ")";
		persServ.create(query);
	}

	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr) throws TechnicalException {
		String selectQuery = "select gastID from reservierung where Nr="
				+ reservierungNr;
		ResultSet res = persServ.readByRawQuery(selectQuery);
		try {
			while (res.next()) {
				return res.getInt("gastID");
			}
		} catch (SQLException e) {
			throwNewTechnicalException();
		}
		return null;
	}
}