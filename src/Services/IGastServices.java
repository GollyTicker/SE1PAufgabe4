package Services;

import Gastkomponente.EmailTyp;
import Gastkomponente.GastTyp;

public interface IGastServices {

	GastTyp erzeugeGast(Integer nr, String name, EmailTyp email);

	GastTyp sucheGastNachName(String name);
}
