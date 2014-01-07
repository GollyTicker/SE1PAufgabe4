package Reservierungskomponente;

import Persistenz.IPersistenzService;
import Services.IGastServicesFuerReservierung;
import Services.IReservierungServices;

public class ReservierungsKomponente implements IReservierungServices {

	private Reservierungverwalter resVerw = null;
	private ReservierungsAnwendungsfall resVerwAf = null;

	public ReservierungsKomponente(IPersistenzService persistenceManager,
			IGastServicesFuerReservierung gastServicesFuerReservierung) {
		
		resVerw = new Reservierungverwalter(persistenceManager);
		resVerwAf = new ReservierungsAnwendungsfall(resVerw,
				gastServicesFuerReservierung);
	}

	@Override
	public ZusatzleistungTyp erzeugeZusatzleistung(String name) {
		
		return this.resVerwAf.erzeugeZusatzleistung(name);
	}

	@Override
	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr) {
		
		return this.resVerwAf.reserviereZimmer(gastNr, zimmerNr);
	}

	@Override
	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) {
		
		this.resVerwAf.bucheZusatzleistung(reservierungNr, zusatzleistungNr);
	}

	@Override
	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr) {
		
		return this.resVerwAf.sucheGastNrNachReservierungNr(reservierungNr);
	}

}
