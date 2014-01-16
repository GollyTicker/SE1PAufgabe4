package Tests;

import static org.junit.Assert.*;

import org.junit.*;

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
	public void testBlackBoxHotel() {

		GastTyp steffen = hotelFassade.sucheGastNachName("Steffen");
		GastTyp swaneet = hotelFassade.sucheGastNachName("Swaneet");
		GastTyp flasche = hotelFassade.sucheGastNachName("Flasche");

		assertNull(hotelFassade.sucheGastNachName("cant Touch Me"));
		assertNotNull(steffen);
		assertNotNull(swaneet);
		assertNotNull(flasche);
		assertFalse(steffen.istStammkunde());
		assertFalse(swaneet.istStammkunde());
		assertFalse(flasche.istStammkunde());
		
		// steffen macht nicht gen�gend reservierungen mit zusatzleistungen
		for (int i = 1; i <= 2; i++) {
			ReservierungTyp res = hotelFassade.reserviereZimmer(
					steffen.nr(), zimmerNr());
			hotelFassade.bucheZusatzleistung(res.nr(), wlan.nr());
		}
		// swaneet macht 3 reservierungen mit zusatzleistungen
		for (int i = 1; i <= 3; i++) {
			ReservierungTyp res = hotelFassade.reserviereZimmer(
					swaneet.nr(), zimmerNr());
			hotelFassade.bucheZusatzleistung(res.nr(), wlan.nr());
		}
		// flasche macht eine reservierung. zu wenig um stammkunde zu sein
		for (int i = 1; i <= 5; i++) {
			hotelFassade.reserviereZimmer(flasche.nr(), zimmerNr());
		}

		steffen = hotelFassade.sucheGastNachName("Steffen");
		swaneet = hotelFassade.sucheGastNachName("Swaneet");
		flasche = hotelFassade.sucheGastNachName("Flasche");
		
		assertFalse(steffen.istStammkunde());
		assertTrue(swaneet.istStammkunde());
		assertFalse(flasche.istStammkunde());
		
		// jetzt hat flasch gen�gend reservierungen
		hotelFassade.reserviereZimmer(flasche.nr(), zimmerNr());
		flasche = hotelFassade.sucheGastNachName("Flasche");
		assertTrue(flasche.istStammkunde());
	}
	
	// gibt immer eine neue zimmerNr zur�ck
	private int zimmerNr() {
		zNr += 1;
		return zNr;
	}
}
