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

	private IPersistenzService pServ;
	private IGastServices gastServ;
	private IReservierungServices resvServ;
	private IGastServicesFuerReservierung gastServFuerResrv;
	private GastTyp swaneet, steffen;
	private ZusatzleistungTyp wlan, balkon;
	private int zNr;

	@Before
	public void setUp() {
		this.pServ = new DatabaseConnection();
		this.gastServ = new GastverwaltungKomponente(pServ);
		this.gastServFuerResrv = new GastverwaltungKomponenteDummy(pServ);
		this.resvServ = new ReservierungsKomponente(pServ,
				this.gastServFuerResrv);

		// create guests and zusatzleistungen for testing
		this.swaneet = gastServ.erzeugeGast(1, "Swaneet",
				email("swaneet", "hawhamburg", "de"));
		this.steffen = gastServ.erzeugeGast(2, "Steffen",
				email("steffer", "hawhamburg", "de"));

		this.wlan = resvServ.erzeugeZusatzleistung("WLAN");
		this.balkon = resvServ.erzeugeZusatzleistung("balkon");
		zNr = 0;
	}

	@Test
	public void testReservierungIsolated() {

		swaneet = gastServ.sucheGastNachName("Swaneet");
		steffen = gastServ.sucheGastNachName("Steffen");

		assertFalse(swaneet.istStammkunde());
		assertFalse(steffen.istStammkunde());

		// swaneet macht drei reservierungen mit zusatzleistung
		for (int i = 1; i <= 3; i++) {
			ReservierungTyp res = resvServ.reserviereZimmer(swaneet.nr(),
					zimmerNr());
			resvServ.bucheZusatzleistung(res.nr(), balkon.nr());
			resvServ.bucheZusatzleistung(res.nr(), wlan.nr());
		}

		ReservierungTyp res = resvServ.reserviereZimmer(steffen.nr(),
				zimmerNr());
		resvServ.bucheZusatzleistung(res.nr(), wlan.nr());

		swaneet = gastServ.sucheGastNachName("Swaneet");
		steffen = gastServ.sucheGastNachName("Steffen");

		assertTrue(swaneet.istStammkunde());
		assertFalse(steffen.istStammkunde());
	}

	// gibt immer eine neue zimmerNr zur�ck
	private int zimmerNr() {
		zNr += 1;
		return zNr;
	}
}
