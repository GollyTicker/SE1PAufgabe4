package Reservierungskomponente;

import static Precondition.Precondition.requires;
import Services.IGastServicesFuerReservierung;
import Services.IReservierungServices;

public class ReservierungsAnwendungsfall implements IReservierungServices {

	private Reservierungverwalter resVerw = null;
	private IGastServicesFuerReservierung gServFuerRes;

	public ReservierungsAnwendungsfall(Reservierungverwalter resVerw,
			IGastServicesFuerReservierung gServFuerRes) {
		
		this.resVerw = resVerw;
		this.gServFuerRes = gServFuerRes;
	}

	@Override
	public ZusatzleistungTyp erzeugeZusatzleistung(String name) {
		
		requires(name != null && name.length() > 0);
		return this.resVerw.erzeugeZusatzleistung(name);
	}

	@Override
	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr) {
		
		requires(gastNr != null && gastNr > 0);
		requires(zimmerNr != null);
		ReservierungTyp result = resVerw.reserviereZimmer(gastNr, zimmerNr);
		gServFuerRes.markiereGastAlsStammkunden(gastNr);
		return result;
	}

	@Override
	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) {
		
		requires(zusatzleistungNr != null && zusatzleistungNr > 0);
		requires(reservierungNr != null && reservierungNr > 0);
		Integer gastNr = resVerw.sucheGastNrNachReservierungNr(reservierungNr);
		this.resVerw.bucheZusatzleistung(reservierungNr, zusatzleistungNr);
		gServFuerRes.markiereGastAlsStammkunden(gastNr);
	}

	@Override
	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr) {
		
		return this.resVerw.sucheGastNrNachReservierungNr(reservierungNr);
	}

}
