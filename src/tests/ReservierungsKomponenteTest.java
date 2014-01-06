package tests;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistenz.IPersistenzService;
import persistenz.SqlConnecter;
import a10.gastkomponente.Email;
import a10.gastkomponente.Gast;
import a10.gastkomponente.IGastServices;
import a10.gastkomponente.IGastServicesFuerReservierung;
import a10.gastkomponente.verwalter.GastverwaltungKomponente;
import a10.reservierungskomponente.IReservierungServices;
import a10.reservierungskomponente.Reservierung;
import a10.reservierungskomponente.Zusatzleistung;
import a10.reservierungskomponente.verwalter.ReservierungverwaltungKomponente;

public class ReservierungsKomponenteTest {

	private IPersistenzService persistenceService = new SqlConnecter();
	private IGastServices gastService;
	private IReservierungServices reservierungService;
	private IGastServicesFuerReservierung gastServiceFuerReservierung;
	private Gast matze, kai;
	private Zusatzleistung sauna, vollpension, wlan;

	@Before
	public void setUp() {
		this.gastService = new GastverwaltungKomponente(persistenceService);
		this.reservierungService = new ReservierungverwaltungKomponente(
				persistenceService);
		this.gastServiceFuerReservierung = new ReservierungverwaltungKomponente(
				persistenceService);
		this.matze = gastService.erzeugeGast(1, "matthias",
				Email.email("matthias", "gmail", "de"));
		this.kai = gastService.erzeugeGast(2, "kai",
				Email.email("kai", "gmail", "de"));

		this.sauna = reservierungService.erzeugeZusatzleistung("Sauna");
		this.vollpension = reservierungService
				.erzeugeZusatzleistung("Vollpension");
		this.wlan = reservierungService.erzeugeZusatzleistung("WLAN");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReservierung() {

		matze = gastService.sucheGastNachName("matthias");
		kai = gastService.sucheGastNachName("kai");

		assertFalse(matze.istStammkunde());
		assertFalse(kai.istStammkunde());

		for (int i = 1; i < 10; i++) {
			Reservierung res = reservierungService.reserviereZimmer(
					matze.getNr(), i);
			reservierungService.bucheZusatzleistung(res.getNr(), sauna.getNr());
			reservierungService.bucheZusatzleistung(res.getNr(),
					vollpension.getNr());
			reservierungService.bucheZusatzleistung(res.getNr(), wlan.getNr());
		}

		Reservierung res = reservierungService
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
