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

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {
		try {
			String selectQuery = "insert into gast values(" + nr + ", '" + name
					+ "', '" + email.toString() + "', false)";
			persServ.create(selectQuery);
			return sucheGastNachName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public GastTyp sucheGastNachName(String name) {
		ResultSet res = persServ.readByStrAttribute(name, "gast", "name");
		try {
			while (res.next()) {
				String email = res.getString("Email");
				String name2 = res.getString("name");
				Integer nr = res.getInt("Nr");
				Boolean istStammK = res.getBoolean("IstStammkunde");
				return gast(nr, name2, emailConvertFromString(email), istStammK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void markiereGastStammkundeFallsBedingungenErfuellt(int gastNr) {

		String reservierungenQuery = "select count(distinct res.Nr) as reservierung"
				+ " from ( select gastID, Nr from reservierung where gastID="
				+ gastNr + " ) res;";

		String reservierungenZusatzQuery = "select count(distinct res.Nr) as zusatzreservierung "
				+ "from (select Nr from umfasst,"
				+ "reservierung r where gastID="
				+ gastNr
				+ " and umfasst.rID=r.Nr group by Nr) res;";

		String queryIfStammkunde = "update gast set IstStammkunde = true where Nr = "
				+ gastNr + ";";

		ResultSet res = persServ.readByRawQuery(reservierungenQuery);
		ResultSet res2 = persServ.readByRawQuery(reservierungenZusatzQuery);

		try {
			while (res.next()) {
				if (res.getInt("reservierung") >= 6) {
					persServ.updateByRawQuery(queryIfStammkunde);
				}
			}
			while (res2.next()) {
				if (res2.getInt("zusatzreservierung") >= 3) {
					persServ.updateByRawQuery(queryIfStammkunde);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
