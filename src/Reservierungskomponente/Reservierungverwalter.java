package Reservierungskomponente;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Reservierungskomponente.ReservierungTyp.reservierung;
import static Reservierungskomponente.ZusatzleistungTyp.zusatzleistung;
import Persistenz.IPersistenzService;

public class Reservierungverwalter {

	private IPersistenzService persServ;

	public Reservierungverwalter(IPersistenzService persServ) {
		
		this.persServ = persServ;
	}

	public ZusatzleistungTyp erzeugeZusatzleistung(String name) {

		String query = "insert into Zusatzleistung values(default,'" + name
				+ "')";
		persServ.create(query);
		ZusatzleistungTyp z = sucheZusatzleistung(name);
		return z;
	};

	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr) {
		
		String query = "insert into Reservierung values(default," + zimmerNr
				+ "," + gastNr + ");";
		persServ.create(query);
		ReservierungTyp r = sucheReservierung(gastNr, zimmerNr);
		return r;
	};

	public ReservierungTyp sucheReservierung(Integer gastNr, Integer zimmerNr) {
		ResultSet rs = persServ.readByStrAttribute(gastNr.toString(),
				"Reservierung", "gast_id");
		Integer nr = 0;
		Integer zimmernr = 0;
		try {
			while (rs.next()) {
				nr = (rs.getInt("nr"));
				zimmernr = (rs.getInt("zimmerNr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReservierungTyp r = reservierung(nr, zimmernr);
		System.out.println(r);
		return r;
	}

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) {
		
		String query = "insert into umfasst values(default," + reservierungNr + ","
				+ zusatzleistungNr + ")";
		persServ.create(query);
	};

	public ZusatzleistungTyp sucheZusatzleistung(String name) {
		ResultSet rs = persServ.readByStrAttribute(name, "zusatzleistung",
				"LeistungsArt");
		Integer nr = 0;
		String leistung = "";
		try {
			while (rs.next()) {
				nr = (rs.getInt("nr"));
				leistung = rs.getString("Leistungsart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ZusatzleistungTyp z = zusatzleistung(nr, leistung);
		System.out.println(z);
		return z;
	}

	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr) {
		
		String searchQuery = "select gast_id from reservierung where nr="
				+ reservierungNr;
		ResultSet rs = persServ.readPlainSql(searchQuery);
		Integer gastnr = -1;
		try {
			while (rs.next()) {
				gastnr = rs.getInt("gast_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gastnr;
	}
}