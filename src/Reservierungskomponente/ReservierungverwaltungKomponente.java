package Reservierungskomponente;

import Persistenz.IPersistenzService;
import Services.IGastServicesFuerReservierung;
import Services.IReservierungServices;
import Typen.ReservierungTyp;
import Typen.ZusatzleistungTyp;

public class ReservierungverwaltungKomponente implements IReservierungServices {

	private Reservierungverwalter resVerwalter = null;
	private ReservierungverwaltungAnwendungsfall resVerwaltungAnwendungsfall = null;

	public ReservierungverwaltungKomponente(
			IPersistenzService persistenceManager,
			IGastServicesFuerReservierung gastServicesFuerReservierung) {
		resVerwalter = new Reservierungverwalter(persistenceManager);
		resVerwaltungAnwendungsfall = new ReservierungverwaltungAnwendungsfall(
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
