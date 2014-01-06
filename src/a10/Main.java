package a10;

import persistenz.IPersistenzService;
import persistenz.SqlConnecter;
import a10.gastkomponente.Email;
import a10.gastkomponente.Gast;
import a10.reservierungskomponente.Reservierung;
import a10.reservierungskomponente.Zusatzleistung;

public class Main {

	public static void main(String[] args) {

		// Gast 1
		Integer nr = 1;
		String name = "matthias";
		Email email = Email.email(name, "gmail", "de");
		// Gast 2
		Integer nr2 = 2;
		String name2 = "kai";
		Email email2 = Email.email(name2, "gmail", "org");
		// Gast 3
		Integer nr3 = 3;
		String name3 = "tree";
		Email email3 = Email.email(name3, "gmail", "org");

		IPersistenzService persistenceService = new SqlConnecter();
		
		BuchungsFassade bf = new BuchungsFassade(persistenceService);
		bf.erzeugeGast(nr, name, email);
		bf.erzeugeGast(nr2, name2, email2);
		bf.erzeugeGast(nr3, name3, email3);

		Gast matze = bf.sucheGastNachName("matthias");
		Gast kai = bf.sucheGastNachName("kai");
		Gast tree = bf.sucheGastNachName("tree");

		Zusatzleistung sauna = bf.erzeugeZusatzleistung("Sauna");
		Zusatzleistung vollpension = bf.erzeugeZusatzleistung("Vollpension");
		Zusatzleistung wlan = bf.erzeugeZusatzleistung("WLAN");

		for (int i = 1; i < 10; i++) {
			Reservierung res = bf.reserviereZimmer(matze.getNr(), i);
			bf.bucheZusatzleistung(res.getNr(), sauna.getNr());
			bf.bucheZusatzleistung(res.getNr(), vollpension.getNr());
			bf.bucheZusatzleistung(res.getNr(), wlan.getNr());
		}

		for (int i = 11; i < 15; i++) {
			Reservierung res = bf.reserviereZimmer(kai.getNr(), i);
			bf.bucheZusatzleistung(res.getNr(), sauna.getNr());
		}

		bf.reserviereZimmer(tree.getNr(), 20);
		
		matze = bf.sucheGastNachName("matthias");
		kai = bf.sucheGastNachName("kai");
		tree = bf.sucheGastNachName("tree");
		
	}
}
