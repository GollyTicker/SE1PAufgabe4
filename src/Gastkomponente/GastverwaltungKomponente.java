package Gastkomponente;

import Persistenz.IPersistenzService;
import Services.IGastServices;
import Services.IGastServicesFuerReservierung;

public class GastverwaltungKomponente implements IGastServices,
		IGastServicesFuerReservierung {

	private Gastverwalter gastVerwalter = null;
	private GastAnwendungsfall gastVerwaltungAnwendungsfall = null;

	public GastverwaltungKomponente(IPersistenzService persistenceManager) {
		gastVerwalter = new Gastverwalter(persistenceManager);
		gastVerwaltungAnwendungsfall = new GastAnwendungsfall(
				gastVerwalter);
	}

	@Override
	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {
		return this.gastVerwaltungAnwendungsfall.erzeugeGast(nr, name, email);
	}

	@Override
	public GastTyp sucheGastNachName(String name) {
		return this.gastVerwaltungAnwendungsfall.sucheGastNachName(name);
	}

	@Override
	public void markiereGastAlsStammkunden(Integer nr) {
		this.gastVerwaltungAnwendungsfall.markiereGastAlsStammkunden(nr);
	}
}
