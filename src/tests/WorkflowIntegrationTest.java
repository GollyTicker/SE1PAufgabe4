package tests;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a10.BuchungsFassade;
import a10.gastkomponente.Email;
import a10.gastkomponente.Gast;
import a10.reservierungskomponente.Reservierung;
import a10.reservierungskomponente.Zusatzleistung;

public class WorkflowIntegrationTest {

	private BuchungsFassade buchungsFassade;

	@Before
	public void setUp() {
		this.buchungsFassade = new BuchungsFassade();
		createGuests();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testIntegration() {

		Gast matze = buchungsFassade.sucheGastNachName("matthias");
		Gast kai = buchungsFassade.sucheGastNachName("kai");
		Gast tree = buchungsFassade.sucheGastNachName("tree");

		assertTrue(matze != null);
		assertTrue(kai != null);
		assertTrue(tree != null);
		assertFalse(matze.istStammkunde());
		assertFalse(matze.istStammkunde());
		assertFalse(matze.istStammkunde());

		Zusatzleistung sauna = buchungsFassade.erzeugeZusatzleistung("Sauna");
		Zusatzleistung vollpension = buchungsFassade
				.erzeugeZusatzleistung("Vollpension");
		Zusatzleistung wlan = buchungsFassade.erzeugeZusatzleistung("WLAN");

		for (int zimmerNr = 1; zimmerNr < 10; zimmerNr++) {
			Reservierung res = buchungsFassade.reserviereZimmer(matze.getNr(),
					zimmerNr);
			buchungsFassade.bucheZusatzleistung(res.getNr(), sauna.getNr());
			buchungsFassade.bucheZusatzleistung(res.getNr(),
					vollpension.getNr());
			buchungsFassade.bucheZusatzleistung(res.getNr(), wlan.getNr());
		}

		for (int zimmerNr = 11; zimmerNr < 15; zimmerNr++) {
			Reservierung res = buchungsFassade.reserviereZimmer(kai.getNr(),
					zimmerNr);
			buchungsFassade.bucheZusatzleistung(res.getNr(), sauna.getNr());
		}

		buchungsFassade.reserviereZimmer(tree.getNr(), 20);

		matze = buchungsFassade.sucheGastNachName("matthias");
		kai = buchungsFassade.sucheGastNachName("kai");
		tree = buchungsFassade.sucheGastNachName("tree");

		assertTrue(matze != null);
		assertTrue(kai != null);
		assertTrue(tree != null);
		assertTrue(matze.istStammkunde());
		assertTrue(kai.istStammkunde());
		assertFalse(tree.istStammkunde());
	}

	@After
	public void tearDown() {
		buchungsFassade = null;
	}

	private void createGuests() {
		ArrayList<ArrayList<Object>> guests = new ArrayList<ArrayList<Object>>(
				Arrays.asList(createList(1, "matthias"), createList(2, "kai"),
						createList(3, "tree")));
		for (ArrayList<Object> g : guests) {
			Integer nr = (int) g.get(0);
			String name = String.valueOf(g.get(1));
			Email email = (Email) g.get(2);
			buchungsFassade.erzeugeGast(nr, name, email);
		}
	}

	private ArrayList<Object> createList(Integer nr, String name) {
		return new ArrayList<Object>(Arrays.asList(nr, name,
				Email.email(name, "gmail", "com")));
	}
}
