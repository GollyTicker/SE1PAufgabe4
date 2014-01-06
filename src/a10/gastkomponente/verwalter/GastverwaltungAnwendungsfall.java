package a10.gastkomponente.verwalter;

import a10.gastkomponente.Email;
import a10.gastkomponente.Gast;
import a10.gastkomponente.IGastServices;
import a10.util.Contract;

public class GastverwaltungAnwendungsfall implements IGastServices {

	private Gastverwalter gastverwalter = null;

	public GastverwaltungAnwendungsfall(Gastverwalter gastverwalter) {
		this.gastverwalter = gastverwalter;
	}

	@Override
	public Gast erzeugeGast(Integer nr, String name, Email email) {
		Contract.requires(email != null);
		Contract.requires(name != null && name.length() <= 30
				&& name.length() > 0);
		Contract.requires(nr != null && nr >= 0);
		return this.gastverwalter.erzeugeGast(nr, name, email);
	}

	@Override
	public Gast sucheGastNachName(String name) {
		Contract.requires(name != null && name.length() > 0);
		return this.gastverwalter.sucheGastNachName(name);
	}
}
