package Services;

import Utilities.TechnicalException;

public interface IGastServicesFuerReservierung {
	void markiereGastAlsStammkunden(Integer nr) throws TechnicalException;
}
