package Reservierungskomponente;

import Gastkomponente.IGastServicesFuerReservierung;
import Persistenz.IPersistenzService;

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
