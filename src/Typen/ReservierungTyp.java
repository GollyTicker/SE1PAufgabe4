package Typen;

import java.util.List;

public class ReservierungTyp {

	private Integer nr;
	private Integer zimmerNr;
	private List<ZusatzleistungTyp> zLeistungen;

	private ReservierungTyp(Integer nr, Integer zimmerNr) {
		this.nr = nr;
		this.zimmerNr = zimmerNr;
	}
	
	public static ReservierungTyp reservierung(Integer nr, Integer zimmerNr) {
		return new ReservierungTyp(nr, zimmerNr);
	}

	@Override
	public String toString() {
		return "Reservierung{nr=" + nr + ", zimmerNr=" + zimmerNr + "}";
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public Integer getZimmerNr() {
		return zimmerNr;
	}

	public void setZimmerNr(Integer zimmerNr) {
		this.zimmerNr = zimmerNr;
	}

	public List<ZusatzleistungTyp> getzLeistungen() {
		return zLeistungen;
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
		return r.getNr().compareTo(this.getNr()) == 0
				&& r.getZimmerNr().compareTo(this.getZimmerNr()) == 0;
	}

}
