package Gastkomponente;

import Persistenz.IPersistenzService;
import Services.IGastServices;
import Services.IGastServicesFuerReservierung;

public class GastverwaltungKomponente implements IGastServices,
		IGastServicesFuerReservierung {

	private Gastverwalter gVerw = null;
	private GastAnwendungsfall gastAnwf = null;

	public GastverwaltungKomponente(IPersistenzService persistenceManager) {
		gVerw = new Gastverwalter(persistenceManager);
		gastAnwf = new GastAnwendungsfall(gVerw);
	}

	@Override
	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email) {
		return this.gastAnwf.erzeugeGast(nr, name, email);
	}

	@Override
	public GastTyp sucheGastNachName(String name) {
		return this.gastAnwf.sucheGastNachName(name);
	}

	@Override
	public void markiereGastAlsStammkunden(Integer nr) {
		this.gastAnwf.markiereGastAlsStammkunden(nr);
	}
}
