package Tests;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Anwendungkernfassade.HotelFassade;
import Anwendungkernfassade.IHotelFassade;
import Typen.EmailTyp;
import Typen.GastTyp;
import Typen.ReservierungTyp;
import Typen.ZusatzleistungTyp;

public class WorkflowIntegrationTest {

	private IHotelFassade hotelFassade;

	@Before
	public void setUp() {
		this.hotelFassade = new HotelFassade();
		createGuests();
	}

	
	@Test
	public void testIntegration() {

		GastTyp matze = hotelFassade.sucheGastNachName("matthias");
		GastTyp kai = hotelFassade.sucheGastNachName("kai");
		GastTyp tree = hotelFassade.sucheGastNachName("tree");

		assertTrue(matze != null);
		assertTrue(kai != null);
		assertTrue(tree != null);
		assertFalse(matze.istStammkunde());
		assertFalse(matze.istStammkunde());
		assertFalse(matze.istStammkunde());

		ZusatzleistungTyp sauna = hotelFassade.erzeugeZusatzleistung("Sauna");
		ZusatzleistungTyp vollpension = hotelFassade
				.erzeugeZusatzleistung("Vollpension");
		ZusatzleistungTyp wlan = hotelFassade.erzeugeZusatzleistung("WLAN");

		for (int zimmerNr = 1; zimmerNr < 10; zimmerNr++) {
			ReservierungTyp res = hotelFassade.reserviereZimmer(matze.getNr(),
					zimmerNr);
			hotelFassade.bucheZusatzleistung(res.getNr(), sauna.getNr());
			hotelFassade.bucheZusatzleistung(res.getNr(),
					vollpension.getNr());
			hotelFassade.bucheZusatzleistung(res.getNr(), wlan.getNr());
		}

		for (int zimmerNr = 11; zimmerNr < 15; zimmerNr++) {
			ReservierungTyp res = hotelFassade.reserviereZimmer(kai.getNr(),
					zimmerNr);
			hotelFassade.bucheZusatzleistung(res.getNr(), sauna.getNr());
		}

		hotelFassade.reserviereZimmer(tree.getNr(), 20);

		matze = hotelFassade.sucheGastNachName("matthias");
		kai = hotelFassade.sucheGastNachName("kai");
		tree = hotelFassade.sucheGastNachName("tree");

		assertTrue(matze != null);
		assertTrue(kai != null);
		assertTrue(tree != null);
		assertTrue(matze.istStammkunde());
		assertTrue(kai.istStammkunde());
		assertFalse(tree.istStammkunde());
	}

	@After
	public void tearDown() {
		hotelFassade = null;
	}

	private void createGuests() {
		ArrayList<ArrayList<Object>> guests = new ArrayList<ArrayList<Object>>(
				Arrays.asList(createList(1, "matthias"), createList(2, "kai"),
						createList(3, "tree")));
		for (ArrayList<Object> g : guests) {
			Integer nr = (int) g.get(0);
			String name = String.valueOf(g.get(1));
			EmailTyp email = (EmailTyp) g.get(2);
			hotelFassade.erzeugeGast(nr, name, email);
		}
	}

	private ArrayList<Object> createList(Integer nr, String name) {
		return new ArrayList<Object>(Arrays.asList(nr, name,
				EmailTyp.email(name, "gmail", "com")));
	}
}
