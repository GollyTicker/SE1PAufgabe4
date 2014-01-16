package Gastkomponente;

import Persistenz.IPersistenzService;
import Services.IGastServices;
import Services.IGastServicesFuerReservierung;
import Utilities.TechnicalException;

public class GastverwaltungKomponente implements IGastServices,
		IGastServicesFuerReservierung {

	private Gastverwalter gVerw = null;
	private GastAnwendungsfall gastAnwf = null;

	public GastverwaltungKomponente(IPersistenzService pServ) {
		gVerw = new Gastverwalter(pServ);
		gastAnwf = new GastAnwendungsfall(gVerw);
	}

	@Override
	public GastTyp erzeugeGast(Integer nr, String name, EmailTyp email)
			throws TechnicalException {
		return this.gastAnwf.erzeugeGast(nr, name, email);
	}

	@Override
	public GastTyp sucheGastNachName(String name) throws TechnicalException {
		return this.gastAnwf.sucheGastNachName(name);
	}

	@Override
	public void markiereGastAlsStammkunden(Integer nr)
			throws TechnicalException {
		this.gastAnwf.markiereGastAlsStammkunden(nr);
	}
}
