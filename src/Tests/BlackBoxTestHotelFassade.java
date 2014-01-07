package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Anwendungkernfassade.HotelFassade;
import Anwendungkernfassade.IHotelFassade;
import static Gastkomponente.EmailTyp.email;
import Gastkomponente.GastTyp;
import Reservierungskomponente.ReservierungTyp;
import Reservierungskomponente.ZusatzleistungTyp;

public class BlackBoxTestHotelFassade {

	private IHotelFassade hotelFassade;
	private int zNr;
	private ZusatzleistungTyp wlan;

	@Before
	public void setUp() {
		this.hotelFassade = new HotelFassade();

		hotelFassade.erzeugeGast(1, "Steffen", email("st", "hw", "de"));

		hotelFassade.erzeugeGast(2, "Swaneet", email("sw", "hw", "de"));

		hotelFassade.erzeugeGast(3, "Flasche", email("ab", "cd", "de"));

		zNr = 0;
		wlan = hotelFassade.erzeugeZusatzleistung("WLAN");
	}

	@Test
	public void testIntegration() {

		GastTyp steffen = hotelFassade.sucheGastNachName("Steffen");
		GastTyp swaneet = hotelFassade.sucheGastNachName("Swaneet");
		GastTyp flasche = hotelFassade.sucheGastNachName("Flasche");

		assertTrue(hotelFassade.sucheGastNachName("nichtDrinnen!!") == null);
		assertNotNull(steffen);
		assertNotNull(swaneet);
		assertNotNull(flasche);
		assertFalse(steffen.istStammkunde());
		assertFalse(steffen.istStammkunde());
		assertFalse(steffen.istStammkunde());

		for (int i = 1; i <= 2; i++) {
			ReservierungTyp res = hotelFassade.reserviereZimmer(
					steffen.nr(), zimmerNr());
			hotelFassade.bucheZusatzleistung(res.getNr(), wlan.getNr());
		}

		for (int i = 1; i <= 3; i++) {
			ReservierungTyp res = hotelFassade.reserviereZimmer(
					swaneet.nr(), zimmerNr());
			hotelFassade.bucheZusatzleistung(res.getNr(), wlan.getNr());
		}

		for (int i = 1; i <= 5; i++) {
			hotelFassade.reserviereZimmer(flasche.nr(), zimmerNr());
		}

		steffen = hotelFassade.sucheGastNachName("Steffen");
		swaneet = hotelFassade.sucheGastNachName("Swaneet");
		flasche = hotelFassade.sucheGastNachName("Flasche");
		
		assertFalse(steffen.istStammkunde());
		assertTrue(swaneet.istStammkunde());
		assertFalse(flasche.istStammkunde());
		

		hotelFassade.reserviereZimmer(flasche.nr(), zimmerNr());
		flasche = hotelFassade.sucheGastNachName("Flasche");
		assertTrue(flasche.istStammkunde());
	}

	@After
	public void tearDown() {
		hotelFassade = null;
	}

	private int zimmerNr() {
		zNr += 1;
		return zNr;
	}
}
