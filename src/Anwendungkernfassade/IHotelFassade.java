package Anwendungkernfassade;

import Gastkomponente.EmailTyp;
import Gastkomponente.GastTyp;
import Reservierungskomponente.ReservierungTyp;
import Reservierungskomponente.ZusatzleistungTyp;

public interface IHotelFassade {

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email);

	public GastTyp sucheGastNachName(String name);

	public ZusatzleistungTyp erzeugeZusatzleistung(String name);

	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr);

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr);

}
