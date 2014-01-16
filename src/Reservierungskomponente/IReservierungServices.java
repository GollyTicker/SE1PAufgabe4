package Reservierungskomponente;

import Utilities.TechnicalException;

public interface IReservierungServices {

	ZusatzleistungTyp erzeugeZusatzleistung(String name)
			throws TechnicalException;

	ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr)
			throws TechnicalException;

	void bucheZusatzleistung(Integer reservierungNr, Integer zusatzleistungNr)
			throws TechnicalException;

	Integer sucheGastNrNachReservierungNr(Integer reservierungNr)
			throws TechnicalException;
}
