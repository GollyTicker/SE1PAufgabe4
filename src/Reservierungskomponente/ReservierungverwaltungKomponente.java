package Reservierungskomponente;

import Persistenz.IPersistenzService;
import Services.IGastServicesFuerReservierung;
import Services.IReservierungServices;

public class ReservierungverwaltungKomponente implements IReservierungServices {

	private Reservierungverwalter resVerwalter = null;
	private ReservierungsAnwendungsfall resVerwaltungAnwendungsfall = null;

	public ReservierungverwaltungKomponente(
			IPersistenzService persistenceManager,
			IGastServicesFuerReservierung gastServicesFuerReservierung) {
		resVerwalter = new Reservierungverwalter(persistenceManager);
		resVerwaltungAnwendungsfall = new ReservierungsAnwendungsfall(
				resVerwalter, gastServicesFuerReservierung);
	}

	@Override
	public ZusatzleistungTyp erzeugeZusatzleistung(String name) {
		return this.resVerwaltungAnwendungsfall.erzeugeZusatzleistung(name);
	}

	@Override
	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr) {
		return this.resVerwaltungAnwendungsfall.reserviereZimmer(gastNr,
				zimmerNr);
	}

	@Override
	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) {
		this.resVerwaltungAnwendungsfall.bucheZusatzleistung(reservierungNr,
				zusatzleistungNr);
	}
	
	@Override
	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr) {
		return this.resVerwaltungAnwendungsfall
				.sucheGastNrNachReservierungNr(reservierungNr);
	}

}
