package a10;

import Typen.EmailTyp;
import a10.gastkomponente.Gast;
import a10.reservierungskomponente.Reservierung;
import a10.reservierungskomponente.Zusatzleistung;

public interface IBuchungsFassade {
	
	public Zusatzleistung erzeugeZusatzleistung(String name);

	public Reservierung reserviereZimmer(Integer gastNr, Integer zimmerNr);

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr);

	public Gast erzeugeGast(Integer nr, String name, EmailTyp email);

	public Gast sucheGastNachName(String name);

}
