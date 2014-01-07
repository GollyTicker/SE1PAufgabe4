package Anwendungkernfassade;

import Gastkomponente.EmailTyp;
import Gastkomponente.GastTyp;
import Gastkomponente.GastverwaltungKomponente;
import Persistenz.DatabaseConnection;
import Persistenz.IPersistenzService;
import Services.IGastServices;
import Services.IGastServicesFuerReservierung;
import Services.IReservierungServices;
import Reservierungskomponente.ReservierungTyp;
import Reservierungskomponente.ReservierungsKomponente;
import Reservierungskomponente.ZusatzleistungTyp;

public class HotelFassade implements IHotelFassade {

	private IPersistenzService perServ;
	private IGastServices gastServ;
	private IGastServicesFuerReservierung gServFuerReserv;
	private IReservierungServices reservServ;

	public HotelFassade() {
		this.perServ = new DatabaseConnection();
		this.gastServ = new GastverwaltungKomponente(perServ);
		this.gServFuerReserv = new GastverwaltungKomponente(perServ);
		this.reservServ = new ReservierungsKomponente(perServ,
				this.gServFuerReserv);
	}

	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {
		return gastServ.erzeugeGast(nr, name, email);
	}

	public GastTyp sucheGastNachName(String name) {
		return gastServ.sucheGastNachName(name);
	}

	public ZusatzleistungTyp erzeugeZusatzleistung(String zusatzleistungName) {
		return reservServ.erzeugeZusatzleistung(zusatzleistungName);
	}

	public ReservierungTyp reserviereZimmer(Integer gastNr, Integer zNr) {
		return reservServ.reserviereZimmer(gastNr, zNr);
	}

	public void bucheZusatzleistung(Integer reservNr, Integer zlNr) {
		reservServ.bucheZusatzleistung(reservNr, zlNr);
	}
}
