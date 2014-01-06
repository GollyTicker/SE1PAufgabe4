package a10;

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

public class BuchungsFassade {

	private IPersistenzService persistenceService;
	private IGastServices gastService;
	private IGastServicesFuerReservierung gastServiceFuerReservierung;
	private IReservierungServices reservierungService;

	public BuchungsFassade(IPersistenzService persistenceService) {
		this.persistenceService = persistenceService;
		this.gastService = new GastverwaltungKomponente(persistenceService);
		this.reservierungService = new ReservierungverwaltungKomponente(
				persistenceService);
		this.gastServiceFuerReservierung = new ReservierungverwaltungKomponente(
				persistenceService);
	}

	public Zusatzleistung erzeugeZusatzleistung(String name) {
		return reservierungService.erzeugeZusatzleistung(name);
	}

	public Reservierung reserviereZimmer(Integer gastNr, Integer zimmerNr) {
		gastServiceFuerReservierung.markiereGastAlsStammkunden(gastNr);
		return reservierungService.reserviereZimmer(gastNr, zimmerNr);
	}

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) {
		Integer gastNr = reservierungService
				.sucheGastNrNachReservierungNr(reservierungNr);
		gastServiceFuerReservierung.markiereGastAlsStammkunden(gastNr);
		reservierungService.bucheZusatzleistung(reservierungNr,
				zusatzleistungNr);
	}

	public Gast erzeugeGast(Integer nr, String name, Email email) {
		return gastService.erzeugeGast(nr, name, email);
	}

	public Gast sucheGastNachName(String name) {
		return gastService.sucheGastNachName(name);
	}

}
