package Services;

import Utilities.TechnicalException;
import Gastkomponente.EmailTyp;
import Gastkomponente.GastTyp;

public interface IGastServices {

	GastTyp erzeugeGast(Integer nr, String name, EmailTyp email)
			throws TechnicalException;

	GastTyp sucheGastNachName(String name) throws TechnicalException;
}
