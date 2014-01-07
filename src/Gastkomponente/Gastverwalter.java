package Gastkomponente;

import java.sql.ResultSet;
import static Gastkomponente.GastTyp.gast;
import Persistenz.IPersistenzService;

public class Gastverwalter {

	private IPersistenzService persistenzService;

	public Gastverwalter(IPersistenzService persistenzService) {
		this.persistenzService = persistenzService;
	}

	public GastTyp sucheGastNachName(String name) {
		// look by the name
		ResultSet rs = persistenzService.read(name, "gast", "name");

		String email = "";
		Integer nr = -1;
		String name_ = "";
		boolean stamm = false;
		try {
			while (rs.next()) {
				email = (rs.getString("Email"));
				name_ = rs.getString("name");
				nr = (rs.getInt(("Nr")));
				stamm = (rs.getBoolean("IstStammkunde"));
				return gast(nr, name_, emailConvertFromString(email), stamm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {

		try {
			String query = "insert into gast values(" + nr + ", '" + name
					+ "', '" + email.toString() + "', false)";
			System.out.println(query);

			persistenzService.create(query);
			GastTyp gast = sucheGastNachName(name);
			return gast;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public EmailTyp emailConvertFromString(String plain) {
		String[] s = plain.split("(@|\\.)");
		String name = s[0];
		String server = s[1];
		String domain = s[2];
		return EmailTyp.email(name, server, domain);
	}

	public void markiereGastStammkundeFallsBedingungenErfuellt(int gastNr) {

		String searchQuery = "select gast_id, count(distinct q.nr) as reservierung, count(distinct z.r_id) as zusatzreservierung "
				+ "from (select r.gast_id, r.nr from reservierung r "
				+ "where gast_id= "
				+ gastNr
				+ " "
				+ "group by nr ) q "
				+ "inner join z2r z on z.r_id = q.nr";
		
		String reservierungenQuery = "select count(distinct res.nr) as reservierung"
				+ " from ( select gast_id, nr from reservierung where gast_id="+ gastNr
				+ " ) res;";
		

		String reservierungenZusatzQuery = "select count(distinct res.nr) as zusatzreservierung "
				+ "from (select nr from z2r,"
				+ "reservierung r where gast_id="+ gastNr
				+ " and z2r.r_id=r.nr group by nr) res;";

		String updateQuery = "update gast set IstStammkunde = true where nr = "
				+ gastNr + ";";

		ResultSet rs = persistenzService.readPlainSql(reservierungenQuery);
		ResultSet rs2 = persistenzService.readPlainSql(reservierungenZusatzQuery);

		try {
			while (rs.next()) {
				
				if (rs.getInt("reservierung") >= 6) {
					persistenzService.writePlainSql(updateQuery);
					System.out.println("That dude with id " + gastNr + " is eligible!");
				}
			}
			while (rs2.next()) {
				
				if (rs2.getInt("zusatzreservierung") >= 3) {
					persistenzService.writePlainSql(updateQuery);
					System.out.println("That dude with id " + gastNr + " is eligible!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
