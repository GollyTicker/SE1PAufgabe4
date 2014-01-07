package Services;

import Typen.EmailTyp;
import Typen.GastTyp;

public interface IGastServices {

	GastTyp erzeugeGast(Integer nr, String name, EmailTyp email);

	GastTyp sucheGastNachName(String name);
}
