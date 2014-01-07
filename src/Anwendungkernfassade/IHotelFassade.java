package Anwendungkernfassade;

import Typen.EmailTyp;
import Typen.GastTyp;
import Typen.ReservierungTyp;
import Typen.ZusatzleistungTyp;

public interface IHotelFassade {
	
	public ZusatzleistungTyp erzeugeZusatzleistung(String name);

	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr);

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr);

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email);

	public GastTyp sucheGastNachName(String name);

}
