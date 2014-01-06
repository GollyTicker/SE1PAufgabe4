package a10.reservierungskomponente.verwalter;

import a10.gastkomponente.IGastServicesFuerReservierung;
import a10.reservierungskomponente.IReservierungServices;
import a10.reservierungskomponente.Reservierung;
import a10.reservierungskomponente.Zusatzleistung;
import a10.util.Contract;

public class ReservierungverwaltungAnwendungsfall implements
		IReservierungServices, IGastServicesFuerReservierung {

	private Reservierungverwalter reservierungverwalter = null;

	public ReservierungverwaltungAnwendungsfall(
			Reservierungverwalter reservierungverwalter) {
		this.reservierungverwalter = reservierungverwalter;
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
		return this.reservierungverwalter.reserviereZimmer(gastNr, zimmerNr);
	}

	@Override
	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) {
		Contract.requires(zusatzleistungNr != null && zusatzleistungNr > 0);
		Contract.requires(reservierungNr != null && reservierungNr > 0);
		this.reservierungverwalter.bucheZusatzleistung(reservierungNr,
				zusatzleistungNr);
	}

	@Override
	public void markiereGastAlsStammkunden(Integer nr) {
		this.reservierungverwalter.markiereGastAlsStammkunden(nr);
	}

	@Override
	public Integer sucheGastNrNachReservierungNr(Integer reservierungNr) {
		return this.reservierungverwalter
				.sucheGastNrNachReservierungNr(reservierungNr);
	}

}
