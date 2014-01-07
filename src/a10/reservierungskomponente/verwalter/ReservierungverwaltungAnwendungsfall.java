package a10.reservierungskomponente.verwalter;

import a10.gastkomponente.IGastServicesFuerReservierung;
import a10.reservierungskomponente.IReservierungServices;
import a10.reservierungskomponente.Reservierung;
import a10.reservierungskomponente.Zusatzleistung;
import a10.util.Contract;

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
		Contract.requires(name != null && name.length() > 0);
		return this.reservierungverwalter.erzeugeZusatzleistung(name);
	}

	@Override
	public Reservierung reserviereZimmer(Integer gastNr, Integer zimmerNr) {
		Contract.requires(gastNr != null && gastNr > 0);
		Contract.requires(zimmerNr != null);
		gastServicesFuerReservierung.markiereGastAlsStammkunden(gastNr);
		return this.reservierungverwalter.reserviereZimmer(gastNr, zimmerNr);
	}

	@Override
	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) {
		Contract.requires(zusatzleistungNr != null && zusatzleistungNr > 0);
		Contract.requires(reservierungNr != null && reservierungNr > 0);
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
