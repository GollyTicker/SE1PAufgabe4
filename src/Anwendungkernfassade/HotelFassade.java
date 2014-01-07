package Anwendungkernfassade;

import Gastkomponente.GastverwaltungKomponente;
import Persistenz.DatabaseConnection;
import Persistenz.IPersistenzService;
import Reservierungskomponente.ReservierungverwaltungKomponente;
import Services.IGastServices;
import Services.IGastServicesFuerReservierung;
import Services.IReservierungServices;
import Typen.EmailTyp;
import Typen.GastTyp;
import Typen.ReservierungTyp;
import Typen.ZusatzleistungTyp;

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

	public ZusatzleistungTyp erzeugeZusatzleistung(String name) {
		return reservierungService.erzeugeZusatzleistung(name);
	}

	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr) {
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

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {
		return gastService.erzeugeGast(nr, name, email);
	}

	public GastTyp sucheGastNachName(String name) {
		return gastService.sucheGastNachName(name);
	}

}
