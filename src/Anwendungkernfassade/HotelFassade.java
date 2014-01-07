package Anwendungkernfassade;

import Gastkomponente.Gast;
import Gastkomponente.GastverwaltungKomponente;
import Gastkomponente.IGastServices;
import Gastkomponente.IGastServicesFuerReservierung;
import Persistenz.DatabaseConnection;
import Persistenz.IPersistenzService;
import Reservierungskomponente.IReservierungServices;
import Reservierungskomponente.Reservierung;
import Reservierungskomponente.ReservierungverwaltungKomponente;
import Reservierungskomponente.Zusatzleistung;
import Typen.EmailTyp;

public class HotelFassade implements IHotelFassade{

	private IPersistenzService persistenceService;
	private IGastServices gastService;
	private IGastServicesFuerReservierung gastServiceFuerReservierung;
	private IReservierungServices reservierungService;

	public HotelFassade() {
		this.persistenceService = new DatabaseConnection();
		this.gastService = new GastverwaltungKomponente(persistenceService);
		this.gastServiceFuerReservierung = new GastverwaltungKomponente(
				persistenceService);
		this.reservierungService = new ReservierungverwaltungKomponente(
				persistenceService, this.gastServiceFuerReservierung);
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

	public Gast erzeugeGast(Integer nr, String name, EmailTyp email) {
		return gastService.erzeugeGast(nr, name, email);
	}

	public Gast sucheGastNachName(String name) {
		return gastService.sucheGastNachName(name);
	}

}