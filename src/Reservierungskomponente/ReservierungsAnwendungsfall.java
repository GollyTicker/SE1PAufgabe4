package Reservierungskomponente;

import Precondition.Precondition;
import Services.IGastServicesFuerReservierung;
import Services.IReservierungServices;

public class ReservierungsAnwendungsfall implements
		IReservierungServices {

	private Reservierungverwalter reservierungverwalter = null;
	private IGastServicesFuerReservierung gastServicesFuerReservierung;

	public ReservierungsAnwendungsfall(
			Reservierungverwalter reservierungverwalter,
			IGastServicesFuerReservierung gastServicesFuerReservierung) {
		this.reservierungverwalter = reservierungverwalter;
		this.gastServicesFuerReservierung = gastServicesFuerReservierung;
	}

	@Override
	public ZusatzleistungTyp erzeugeZusatzleistung(String name) {
		Precondition.requires(name != null && name.length() > 0);
		return this.reservierungverwalter.erzeugeZusatzleistung(name);
	}

	@Override
	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr) {
		Precondition.requires(gastNr != null && gastNr > 0);
		Precondition.requires(zimmerNr != null);
		ReservierungTyp result = reservierungverwalter.reserviereZimmer(gastNr, zimmerNr);
		gastServicesFuerReservierung.markiereGastAlsStammkunden(gastNr);
		return result;
	}

	@Override
	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) {
		Precondition.requires(zusatzleistungNr != null && zusatzleistungNr > 0);
		Precondition.requires(reservierungNr != null && reservierungNr > 0);
		Integer gastNr = reservierungverwalter
				.sucheGastNrNachReservierungNr(reservierungNr);
		this.reservierungverwalter.bucheZusatzleistung(reservierungNr,
				zusatzleistungNr);
		gastServicesFuerReservierung.markiereGastAlsStammkunden(gastNr);
	}

	@Override
	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr) {
		return this.reservierungverwalter
				.sucheGastNrNachReservierungNr(reservierungNr);
	}

}
