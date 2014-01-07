package Gastkomponente;

import Typen.EmailTyp;

public interface IGastServices {

	GastTyp erzeugeGast(Integer nr, String name, EmailTyp email);

	GastTyp sucheGastNachName(String name);
}
