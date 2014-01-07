package a10.gastkomponente;

import Typen.EmailTyp;

public interface IGastServices {

	Gast erzeugeGast(Integer nr, String name, EmailTyp email);

	Gast sucheGastNachName(String name);
}
