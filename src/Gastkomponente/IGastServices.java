package Gastkomponente;

import Typen.EmailTyp;

public interface IGastServices {

	Gast erzeugeGast(Integer nr, String name, EmailTyp email);

	Gast sucheGastNachName(String name);
}
