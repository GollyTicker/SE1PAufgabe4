package Services;

import Reservierungskomponente.ReservierungTyp;
import Reservierungskomponente.ZusatzleistungTyp;

public interface IReservierungServices {

	ZusatzleistungTyp erzeugeZusatzleistung(String name);

	ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr);

	void bucheZusatzleistung(Integer reservierungNr, Integer zusatzleistungNr);
	
	Integer sucheGastNrNachReservierungNr(Integer reservierungNr);
}
