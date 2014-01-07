package Gastkomponente;

import static Precondition.Precondition.assertArgument;
import Services.IGastServices;
import Services.IGastServicesFuerReservierung;

public class GastAnwendungsfall implements IGastServices,
		IGastServicesFuerReservierung {

	private Gastverwalter gVerw = null;

	public GastAnwendungsfall(Gastverwalter gVerw) {
		this.gVerw = gVerw;
	}

	@Override
	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {
		assertArgument(email != null);
		assertArgument(name != null && name.length() <= 30 && name.length() > 0);
		assertArgument(nr != null && nr >= 0);
		return gVerw.erzeugeGast(nr, name, email);
	}

	@Override
	public GastTyp sucheGastNachName(String name) {
		assertArgument(name != null && name.length() > 0);
		return gVerw.sucheGastNachName(name);
	}

	@Override
	public void markiereGastAlsStammkunden(Integer nr) {
		gVerw.markiereGastStammkundeFallsBedingungenErfuellt(nr);
	}
}
