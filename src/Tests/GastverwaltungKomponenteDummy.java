package Tests;

import java.sql.ResultSet;

import Persistenz.IPersistenzService;
import Services.IGastServicesFuerReservierung;

public class GastverwaltungKomponenteDummy implements
		IGastServicesFuerReservierung {

	IPersistenzService pServ;
	
	public GastverwaltungKomponenteDummy(IPersistenzService pServ) {
		this.pServ = pServ;
	}

	@Override
	public void markiereGastAlsStammkunden(Integer gastNr) {
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
