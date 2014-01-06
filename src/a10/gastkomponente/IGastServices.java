package a10.gastkomponente;

public interface IGastServices {

	Gast erzeugeGast(Integer nr, String name, Email email);

	Gast sucheGastNachName(String name);

}
