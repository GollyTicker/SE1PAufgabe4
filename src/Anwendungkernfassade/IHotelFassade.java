package Anwendungkernfassade;

import Gastkomponente.Gast;
import Reservierungskomponente.Reservierung;
import Reservierungskomponente.Zusatzleistung;
import Typen.EmailTyp;

public interface IHotelFassade {
	
	public Zusatzleistung erzeugeZusatzleistung(String name);

	public Reservierung reserviereZimmer(Integer gastNr, Integer zimmerNr);

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr);

	public Gast erzeugeGast(Integer nr, String name, EmailTyp email);

	public Gast sucheGastNachName(String name);

}
