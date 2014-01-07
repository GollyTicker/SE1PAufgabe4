package a10.reservierungskomponente.verwalter;

import persistenz.IPersistenzService;
import a10.gastkomponente.IGastServicesFuerReservierung;
import a10.reservierungskomponente.IReservierungServices;
import a10.reservierungskomponente.Reservierung;
import a10.reservierungskomponente.Zusatzleistung;

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
	public Zusatzleistung erzeugeZusatzleistung(String name) {
		return this.resVerwaltungAnwendungsfall.erzeugeZusatzleistung(name);
	}

	@Override
	public Reservierung reserviereZimmer(Integer gastNr, Integer zimmerNr) {
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
