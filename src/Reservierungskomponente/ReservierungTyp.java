package Reservierungskomponente;

import java.util.List;

public class ReservierungTyp {

	private Integer nr;
	private Integer zimmerNr;
	private List<ZusatzleistungTyp> zuLstg;

	private ReservierungTyp(Integer nr, Integer zimmerNr) {
		this.nr = nr;
		this.zimmerNr = zimmerNr;
	}

	public static ReservierungTyp reservierung(Integer nr, Integer zimmerNr) {
		return new ReservierungTyp(nr, zimmerNr);
	}

	public List<ZusatzleistungTyp> getzLeistungen() {
		return zuLstg;
	}

	public Integer nr() {
		return nr;
	}

	public Integer zimmerNr() {
		return zimmerNr;
	}

	@Override
	public int hashCode() {
		long longBits = Double.doubleToLongBits(Double.valueOf(this.nr));
		return (int) (longBits ^ (longBits >>> 32));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ReservierungTyp))
			return false;
		ReservierungTyp r = (ReservierungTyp) obj;
		return r.nr().compareTo(this.nr()) == 0
				&& r.zimmerNr().compareTo(this.zimmerNr()) == 0;
	}

	@Override
	public String toString() {
		return "Reservierung(" + nr + ", " + zimmerNr + ")";
	}

}
