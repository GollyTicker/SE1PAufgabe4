package Tests;

import static org.junit.Assert.*;

import org.junit.*;

import static Gastkomponente.EmailTyp.email;
import Gastkomponente.GastTyp;
import Gastkomponente.GastverwaltungKomponente;
import Persistenz.DatabaseConnection;
import Persistenz.IPersistenzService;
import Reservierungskomponente.ReservierungTyp;
import Reservierungskomponente.ReservierungsKomponente;
import Reservierungskomponente.ZusatzleistungTyp;
import Services.IGastServices;
import Services.IGastServicesFuerReservierung;
import Services.IReservierungServices;

public class ReservierungsTest {

	private IPersistenzService persistenceService;
	private IGastServices gastService;
	private IReservierungServices reservierungService;
	private IGastServicesFuerReservierung gastServiceFuerReservierung;
	private GastTyp swaneet, steffen;
	private ZusatzleistungTyp wlan, balkon;

	@Before
	public void setUp() {
		this.persistenceService = new DatabaseConnection();
		this.gastService = new GastverwaltungKomponente(persistenceService);
		this.gastServiceFuerReservierung = new GastverwaltungKomponente(
				persistenceService);
		this.reservierungService = new ReservierungsKomponente(
				persistenceService, this.gastServiceFuerReservierung);

		this.swaneet = gastService.erzeugeGast(1, "Swaneet",
					email("swaneet", "hawhamburg", "de"));
		this.steffen = gastService.erzeugeGast(2, "Steffen",
				email("steffer", "hawhamburg", "de"));
		
		this.wlan = reservierungService.erzeugeZusatzleistung("WLAN");
		this.balkon = reservierungService
				.erzeugeZusatzleistung("balkon");
	}

	
	@Test
	public void testReservierung() {

		swaneet = gastService.sucheGastNachName("Swaneet");
		steffen = gastService.sucheGastNachName("Steffen");

		assertFalse(swaneet.istStammkunde());
		assertFalse(steffen.istStammkunde());

		for (int i = 1; i <= 3; i++) {
			ReservierungTyp res = reservierungService.reserviereZimmer(
					swaneet.getNr(), i);
			reservierungService.bucheZusatzleistung(res.getNr(),
					balkon.getNr());
			reservierungService.bucheZusatzleistung(res.getNr(), wlan.getNr());
		}

		ReservierungTyp res = reservierungService
				.reserviereZimmer(steffen.getNr(), 0);
		reservierungService.bucheZusatzleistung(res.getNr(), wlan.getNr());
		
		swaneet = gastService.sucheGastNachName("Swaneet");
		steffen = gastService.sucheGastNachName("Steffen");

		assertTrue(swaneet.istStammkunde());
		assertFalse(steffen.istStammkunde());
	}

	@After
	public void tearDown() {
		this.persistenceService = null;
		this.gastService = null;
		this.reservierungService = null;
	}

}
