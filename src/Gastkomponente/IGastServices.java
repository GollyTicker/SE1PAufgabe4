package Gastkomponente;

import Utilities.InvalidEmailException;
import Utilities.TechnicalException;

public interface IGastServices {

	GastTyp erzeugeGast(Integer nr, String name, EmailTyp email)
			throws TechnicalException, InvalidEmailException;

	GastTyp sucheGastNachName(String name) throws TechnicalException,
			InvalidEmailException;
}
