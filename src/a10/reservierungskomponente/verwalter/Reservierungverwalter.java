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

	public void markiereGastAlsStammkunden(int nr) {

		String searchQuery = "select gast_id, count(distinct q.nr) as reservierung, count(distinct z.r_id) as zusatzreservierung "
				+ "from (select r.gast_id, r.nr from reservierung r "
				+ "where gast_id= "
				+ nr
				+ " "
				+ "group by nr )q "
				+ "inner join z2r z on z.r_id = q.nr";

		String updateQuery = "update gast set IstStammkunde = true where nr = "
				+ nr + ";";

		ResultSet rs = persistenzService.readPlainSql(searchQuery);
		int zusatzreservierung = 0;
		int reservierung = 0;

		try {
			while (rs.next()) {
				zusatzreservierung = (rs.getInt("zusatzreservierung"));
				reservierung = rs.getInt("reservierung");
				System.out.println(rs.getInt("reservierung"));
				System.out.println(rs.getInt("zusatzreservierung"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (zusatzreservierung > 2 || reservierung > 4) {
			persistenzService.writePlainSql(updateQuery);
			System.out.println("That dude with id " + nr + " is eligible!");
		} else {
			System.out.println("That dude with id " + nr + " is not eligibe!");
		}
	}

}