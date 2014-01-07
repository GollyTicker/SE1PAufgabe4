package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Gastkomponente.GastTyp;
import Gastkomponente.GastverwaltungKomponente;
import Gastkomponente.IGastServices;
import Gastkomponente.IGastServicesFuerReservierung;
import Persistenz.DatabaseConnection;
import Persistenz.IPersistenzService;
import Reservierungskomponente.IReservierungServices;
import Reservierungskomponente.ReservierungTyp;
import Reservierungskomponente.ReservierungverwaltungKomponente;
import Reservierungskomponente.ZusatzleistungTyp;
import Typen.EmailTyp;

public class ReservierungsKomponenteTest {

	private IPersistenzService persistenceService = new DatabaseConnection();
	private IGastServices gastService;
	private IReservierungServices reservierungService;
	private IGastServicesFuerReservierung gastServiceFuerReservierung;
	private GastTyp matze, kai;
	private ZusatzleistungTyp sauna, vollpension, wlan;

	@Before
	public void setUp() {
		this.gastService = new GastverwaltungKomponente(persistenceService);
		this.gastServiceFuerReservierung = new GastverwaltungKomponente(
				persistenceService);
		this.reservierungService = new ReservierungverwaltungKomponente(
				persistenceService, this.gastServiceFuerReservierung);

		this.matze = gastService.erzeugeGast(1, "matthias",
				EmailTyp.email("matthias", "gmail", "de"));
		this.kai = gastService.erzeugeGast(2, "kai",
				EmailTyp.email("kai", "gmail", "de"));

		this.sauna = reservierungService.erzeugeZusatzleistung("Sauna");
		this.vollpension = reservierungService
				.erzeugeZusatzleistung("Vollpension");
		this.wlan = reservierungService.erzeugeZusatzleistung("WLAN");
	}

	
	@Test
	public void testReservierung() {

		matze = gastService.sucheGastNachName("matthias");
		kai = gastService.sucheGastNachName("kai");

		assertFalse(matze.istStammkunde());
		assertFalse(kai.istStammkunde());

		for (int i = 1; i < 10; i++) {
			ReservierungTyp res = reservierungService.reserviereZimmer(
					matze.getNr(), i);
			reservierungService.bucheZusatzleistung(res.getNr(), sauna.getNr());
			reservierungService.bucheZusatzleistung(res.getNr(),
					vollpension.getNr());
			reservierungService.bucheZusatzleistung(res.getNr(), wlan.getNr());
		}

		ReservierungTyp res = reservierungService
				.reserviereZimmer(kai.getNr(), 40);
		reservierungService.bucheZusatzleistung(res.getNr(), sauna.getNr());

		gastServiceFuerReservierung.markiereGastAlsStammkunden(matze.getNr());
		gastServiceFuerReservierung.markiereGastAlsStammkunden(kai.getNr());
		matze = gastService.sucheGastNachName("matthias");
		kai = gastService.sucheGastNachName("kai");

		assertTrue(matze.istStammkunde());
		assertFalse(kai.istStammkunde());
	}

	@After
	public void tearDown() {
		this.persistenceService = null;
		this.gastService = null;
		this.reservierungService = null;
	}

}
