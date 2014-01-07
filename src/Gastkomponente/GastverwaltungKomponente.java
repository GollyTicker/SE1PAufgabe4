package Gastkomponente;

import Persistenz.IPersistenzService;
import Typen.EmailTyp;

public class GastverwaltungKomponente implements IGastServices,
		IGastServicesFuerReservierung {

	private Gastverwalter gastVerwalter = null;
	private GastverwaltungAnwendungsfall gastVerwaltungAnwendungsfall = null;

	public GastverwaltungKomponente(IPersistenzService persistenceManager) {
		gastVerwalter = new Gastverwalter(persistenceManager);
		gastVerwaltungAnwendungsfall = new GastverwaltungAnwendungsfall(
				gastVerwalter);
	}

	@Override
	public Gast erzeugeGast(Integer nr, String name, EmailTyp email) {
		return this.gastVerwaltungAnwendungsfall.erzeugeGast(nr, name, email);
	}

	@Override
	public Gast sucheGastNachName(String name) {
		return this.gastVerwaltungAnwendungsfall.sucheGastNachName(name);
	}

	@Override
	public void markiereGastAlsStammkunden(Integer nr) {
		this.gastVerwaltungAnwendungsfall.markiereGastAlsStammkunden(nr);
	}
}