package Reservierungskomponente;

import Gastkomponente.IGastServicesFuerReservierung;
import Typen.Precondition;

public class ReservierungverwaltungAnwendungsfall implements
		IReservierungServices {

	private Reservierungverwalter reservierungverwalter = null;
	private IGastServicesFuerReservierung gastServicesFuerReservierung;

	public ReservierungverwaltungAnwendungsfall(
			Reservierungverwalter reservierungverwalter,
			IGastServicesFuerReservierung gastServicesFuerReservierung) {
		this.reservierungverwalter = reservierungverwalter;
		this.gastServicesFuerReservierung = gastServicesFuerReservierung;
	}

	@Override
	public Zusatzleistung erzeugeZusatzleistung(String name) {
		Precondition.requires(name != null && name.length() > 0);
		return this.reservierungverwalter.erzeugeZusatzleistung(name);
	}

	@Override
	public Reservierung reserviereZimmer(Integer gastNr, Integer zimmerNr) {
		Precondition.requires(gastNr != null && gastNr > 0);
		Precondition.requires(zimmerNr != null);
		Reservierung result = reservierungverwalter.reserviereZimmer(gastNr, zimmerNr);
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
		gastServicesFuerReservierung.markiereGastAlsStammkunden(gastNr);
		this.reservierungverwalter.bucheZusatzleistung(reservierungNr,
				zusatzleistungNr);
	}

	@Override
	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr) {
		return this.reservierungverwalter
				.sucheGastNrNachReservierungNr(reservierungNr);
	}

}
