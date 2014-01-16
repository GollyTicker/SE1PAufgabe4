package Reservierungskomponente;

import static Utilities.Precondition.assertArgument;
import Utilities.TechnicalException;
import Gastkomponente.IGastServicesFuerReservierung;

public class ReservierungsAnwendungsfall implements IReservierungServices {

	private Reservierungverwalter resVerw = null;
	private IGastServicesFuerReservierung gServFuerRes;

	public ReservierungsAnwendungsfall(Reservierungverwalter resVerw,
			IGastServicesFuerReservierung gServFuerRes) {
		this.resVerw = resVerw;
		this.gServFuerRes = gServFuerRes;
	}

	@Override
	public ZusatzleistungTyp erzeugeZusatzleistung(String name)
			throws TechnicalException {
		assertArgument(name != null && name.length() > 0);
		return this.resVerw.erzeugeZusatzleistung(name);
	}

	@Override
	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr)
			throws TechnicalException {
		assertArgument(gastNr != null && gastNr > 0);
		assertArgument(zimmerNr != null);
		ReservierungTyp result = resVerw.reserviereZimmer(gastNr, zimmerNr);
		gServFuerRes.markiereGastAlsStammkunden(gastNr);
		return result;
	}

	@Override
	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) throws TechnicalException {
		assertArgument(zusatzleistungNr != null && zusatzleistungNr > 0);
		assertArgument(reservierungNr != null && reservierungNr > 0);
		Integer gastNr = resVerw.sucheGastNrNachReservierungNr(reservierungNr);
		resVerw.bucheZusatzleistung(reservierungNr, zusatzleistungNr);
		gServFuerRes.markiereGastAlsStammkunden(gastNr);
	}

	@Override
	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr)
			throws TechnicalException {
		return this.resVerw.sucheGastNrNachReservierungNr(reservierungNr);
	}

}
