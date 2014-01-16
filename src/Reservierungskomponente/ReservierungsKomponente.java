package Reservierungskomponente;

import Gastkomponente.IGastServicesFuerReservierung;
import Persistenz.IPersistenzService;
import Utilities.TechnicalException;

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
	public ZusatzleistungTyp erzeugeZusatzleistung(String name)
			throws TechnicalException {
		return this.resVerwAf.erzeugeZusatzleistung(name);
	}

	@Override
	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr)
			throws TechnicalException {
		return this.resVerwAf.reserviereZimmer(gastNr, zimmerNr);
	}

	@Override
	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) throws TechnicalException {
		this.resVerwAf.bucheZusatzleistung(reservierungNr, zusatzleistungNr);
	}

	@Override
	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr)
			throws TechnicalException {
		return this.resVerwAf.sucheGastNrNachReservierungNr(reservierungNr);
	}

}
