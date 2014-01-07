package Gastkomponente;

import java.sql.ResultSet;
import static Gastkomponente.GastTyp.gast;
import Persistenz.IPersistenzService;
import static Gastkomponente.EmailTyp.emailConvertFromString;

public class Gastverwalter {

	private IPersistenzService persServ;

	public Gastverwalter(IPersistenzService persServ) {
		this.persServ = persServ;
	}

	public GastTyp sucheGastNachName(String name) {
		ResultSet rs = persServ.readByStrAttribute(name, "gast", "name");
		try {
			while (rs.next()) {
				String email = rs.getString("Email");
				String name2 = rs.getString("name");
				Integer nr = rs.getInt("Nr");
				Boolean istStammK = rs.getBoolean("IstStammkunde");
				return gast(nr, name2, emailConvertFromString(email), istStammK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// if not found
		return null;
	}

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {
		try {
			String query = "insert into gast values(" + nr
					+ ", '" + name
					+ "', '" + email.toString()
					+ "', false)";
			persServ.create(query);
			return sucheGastNachName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void markiereGastStammkundeFallsBedingungenErfuellt(int gastNr) {

		String reservierungenQuery = "select count(distinct res.nr) as reservierung"
				+ " from ( select gast_id, nr from reservierung where gast_id="
				+ gastNr + " ) res;";

		String reservierungenZusatzQuery = "select count(distinct res.nr) as zusatzreservierung "
				+ "from (select nr from z2r,"
				+ "reservierung r where gast_id="
				+ gastNr + " and z2r.r_id=r.nr group by nr) res;";

		String queryIfStammkunde = "update gast set IstStammkunde = true where nr = "
				+ gastNr + ";";

		ResultSet rs = persServ.readPlainSql(reservierungenQuery);
		ResultSet rs2 = persServ.readPlainSql(reservierungenZusatzQuery);

		try {
			while (rs.next()) {

				if (rs.getInt("reservierung") >= 6) {
					persServ.writePlainSql(queryIfStammkunde);
				}
			}
			while (rs2.next()) {

				if (rs2.getInt("zusatzreservierung") >= 3) {
					persServ.writePlainSql(queryIfStammkunde);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
