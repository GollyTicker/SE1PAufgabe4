package Gastkomponente;

import Precondition.Precondition;
import Services.IGastServices;
import Services.IGastServicesFuerReservierung;

public class GastAnwendungsfall implements IGastServices,
		IGastServicesFuerReservierung {

	private Gastverwalter gastverwalter = null;

	public GastAnwendungsfall(Gastverwalter gastverwalter) {
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
		this.gastverwalter.markiereGastStammkundeFallsBedingungenErfuellt(nr);
	}
}
