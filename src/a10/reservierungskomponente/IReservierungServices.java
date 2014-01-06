package a10.reservierungskomponente;

import a10.reservierungskomponente.Zusatzleistung;

public interface IReservierungServices {

	Zusatzleistung erzeugeZusatzleistung(String name);

	Reservierung reserviereZimmer(Integer gastNr, Integer zimmerNr);

	void bucheZusatzleistung(Integer reservierungNr, Integer zusatzleistungNr);
	
	Integer sucheGastNrNachReservierungNr(Integer reservierungNr);
}
