package Gastkomponente;

import static Utilities.Precondition.assertArgument;
import Utilities.InvalidEmailException;
import Utilities.TechnicalException;

public class GastAnwendungsfall implements IGastServices,
		IGastServicesFuerReservierung {

	private Gastverwalter gVerw = null;

	public GastAnwendungsfall(Gastverwalter gVerw) {
		this.gVerw = gVerw;
	}

	@Override
	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email)
			throws TechnicalException, InvalidEmailException {
		assertArgument(email != null);
		assertArgument(name != null && name.length() <= 30 && name.length() > 0);
		assertArgument(nr != null && nr >= 0);
		return gVerw.erzeugeGast(nr, name, email);
	}

	@Override
	public GastTyp sucheGastNachName(String name) throws TechnicalException,
			InvalidEmailException {
		assertArgument(name != null && name.length() > 0);
		return gVerw.sucheGastNachName(name);
	}

	@Override
	public void markiereGastAlsStammkunden(Integer nr)
			throws TechnicalException {
		gVerw.markiereGastStammkundeFallsBedingungenErfuellt(nr);
	}
}
