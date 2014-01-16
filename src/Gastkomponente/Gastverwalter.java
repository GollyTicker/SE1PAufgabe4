package Gastkomponente;

import java.sql.ResultSet;
import static Gastkomponente.GastTyp.gast;
import Persistenz.IPersistenzService;
import static Gastkomponente.EmailTyp.emailConvertFromString;

public class Gastverwalter {

	private IPersistenzService pServ;

	public Gastverwalter(IPersistenzService persServ) {
		this.pServ = persServ;
	}

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {
		try {
			String selectQuery = "insert into gast values(" + nr + ", '" + name
					+ "', '" + email.toString() + "', false)";
			pServ.create(selectQuery);
			return sucheGastNachName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public GastTyp sucheGastNachName(String name) {
		ResultSet res = pServ.readByStrAttribute(name, "gast", "name");
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

		ResultSet res = pServ.readByRawQuery(reservierungenQuery);
		ResultSet res2 = pServ.readByRawQuery(reservierungenZusatzQuery);

		try {
			while (res.next()) {
				if (res.getInt("reservierung") >= 6) {
					pServ.updateByRawQuery(queryIfStammkunde);
				}
			}
			while (res2.next()) {
				if (res2.getInt("zusatzreservierung") >= 3) {
					pServ.updateByRawQuery(queryIfStammkunde);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
