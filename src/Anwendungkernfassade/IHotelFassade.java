package Anwendungkernfassade;

import Gastkomponente.EmailTyp;
import Gastkomponente.GastTyp;
import Reservierungskomponente.ReservierungTyp;
import Reservierungskomponente.ZusatzleistungTyp;
import Utilities.InvalidEmailException;
import Utilities.TechnicalException;

public interface IHotelFassade {

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email)
			throws TechnicalException, InvalidEmailException;

	public GastTyp sucheGastNachName(String name) throws TechnicalException,
			InvalidEmailException;

	public ZusatzleistungTyp erzeugeZusatzleistung(String name)
			throws TechnicalException;

	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zimmerNr)
			throws TechnicalException;

	public void bucheZusatzleistung(Integer reservierungNr,
			Integer zusatzleistungNr) throws TechnicalException;

}
