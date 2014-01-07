package Gastkomponente;

import Typen.Precondition;
import Typen.EmailTyp;

public class GastverwaltungAnwendungsfall implements IGastServices,
		IGastServicesFuerReservierung {

	private Gastverwalter gastverwalter = null;

	public GastverwaltungAnwendungsfall(Gastverwalter gastverwalter) {
		this.gastverwalter = gastverwalter;
	}

	@Override
	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {
		Precondition.requires(email != null);
		Precondition.requires(name != null && name.length() <= 30
				&& name.length() > 0);
		Precondition.requires(nr != null && nr >= 0);
		return this.gastverwalter.erzeugeGast(nr, name, email);
	}

	@Override
	public GastTyp sucheGastNachName(String name) {
		Precondition.requires(name != null && name.length() > 0);
		return this.gastverwalter.sucheGastNachName(name);
	}

	@Override
	public void markiereGastAlsStammkunden(Integer nr) {
		this.gastverwalter.markiereGastStammkudnaFallsBedingungenErfuellt(nr);
	}
}
