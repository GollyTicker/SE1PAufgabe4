package a10.reservierungskomponente.verwalter;

import java.sql.ResultSet;
import java.sql.SQLException;

import a10.reservierungskomponente.Reservierung;
import a10.reservierungskomponente.Zusatzleistung;
import persistenz.IPersistenzService;

public class Reservierungverwalter {

	private IPersistenzService persistenzService;

	public Reservierungverwalter(IPersistenzService persistenzService) {
		this.persistenzService = persistenzService;
	}

	public Zusatzleistung erzeugeZusatzleistung(String name) {

		String query = "insert into Zusatzleistung values(default,'" + name
				+ "')";
		persistenzService.create(query);
		Zusatzleistung z = sucheZusatzleistung(name);
		return z;

	};

	public Reservierung reserviereZimmer(Integer gastNr, Integer zimmerNr) {
		String query = "insert into Reservierung values(default," + zimmerNr
				+ "," + gastNr + ");";
		persistenzService.create(query);
		Reservierung r = sucheReservierung(gastNr, zimmerNr);
		return r;
	};

	public Reservierung sucheReservierung(Integer gastNr, Integer zimmerNr) {

		ResultSet rs = persistenzService.read(gastNr.toString(),
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

		Reservierung r = new Reservierung(nr, zimmernr);
		System.out.println(r);
		return r;
	}

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) {

		String query = "insert into z2r values(default," + reservierungNr + ","
				+ zusatzleistungNr + ")";
		persistenzService.create(query);
	};

	public Zusatzleistung sucheZusatzleistung(String name) {

		ResultSet rs = persistenzService.read(name, "zusatzleistung",
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

		Zusatzleistung z = Zusatzleistung.zusatzleistung(nr, leistung);
		System.out.println(z);
		return z;

	}

	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr) {
		String searchQuery = "select gast_id from reservierung where nr="
				+ reservierungNr;
		ResultSet rs = persistenzService.readPlainSql(searchQuery);
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