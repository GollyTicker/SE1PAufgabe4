package Anwendungkernfassade;

import Gastkomponente.GastTyp;
import Reservierungskomponente.ReservierungTyp;
import Reservierungskomponente.ZusatzleistungTyp;
import Typen.EmailTyp;

public interface IHotelFassade {
	
	public ZusatzleistungTyp erzeugeZusatzleistung(String name);

	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr);

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr);

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email);

	public GastTyp sucheGastNachName(String name);

}
