package Anwendungkernfassade;

import Gastkomponente.EmailTyp;
import Gastkomponente.GastTyp;
import Gastkomponente.GastverwaltungKomponente;
import Gastkomponente.IGastServices;
import Gastkomponente.IGastServicesFuerReservierung;
import Persistenz.DatabaseConnection;
import Persistenz.IPersistenzService;
import Reservierungskomponente.IReservierungServices;
import Reservierungskomponente.ReservierungTyp;
import Reservierungskomponente.ReservierungsKomponente;
import Reservierungskomponente.ZusatzleistungTyp;
import Utilities.InvalidEmailException;
import Utilities.TechnicalException;

public class HotelFassade implements IHotelFassade {

	private IPersistenzService perServ;
	private IGastServices gastServ;
	private IGastServicesFuerReservierung gServFuerReserv;
	private IReservierungServices reservServ;

	public HotelFassade() throws TechnicalException {
		this.perServ = new DatabaseConnection();
		this.gastServ = new GastverwaltungKomponente(perServ);
		this.gServFuerReserv = new GastverwaltungKomponente(perServ);
		this.reservServ = new ReservierungsKomponente(perServ,
				this.gServFuerReserv);
	}

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email)
			throws TechnicalException, InvalidEmailException {
		return gastServ.erzeugeGast(nr, name, email);
	}

	public GastTyp sucheGastNachName(String name) throws TechnicalException,
			InvalidEmailException {
		return gastServ.sucheGastNachName(name);
	}

	public ZusatzleistungTyp erzeugeZusatzleistung(String zusatzleistungName)
			throws TechnicalException {
		return reservServ.erzeugeZusatzleistung(zusatzleistungName);
	}

	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zNr)
			throws TechnicalException {
		return reservServ.reserviereZimmer(gastNr, zNr);
	}

	public void bucheZusatzleistung(Integer reservNr, Integer zlNr)
			throws TechnicalException {
		reservServ.bucheZusatzleistung(reservNr, zlNr);
	}
}
