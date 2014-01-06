package a10.reservierungskomponente;

import java.util.List;

public class Reservierung {

	private Integer nr;
	private Integer zimmerNr;
	private List<Zusatzleistung> zLeistungen;

	public Reservierung(Integer nr, Integer zimmerNr) {
		this.nr = nr;
		this.zimmerNr = zimmerNr;
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

	public List<Zusatzleistung> getzLeistungen() {
		return zLeistungen;
	}

	public boolean addLeistungen(Zusatzleistung leistung) {
		return this.zLeistungen.add(leistung);
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
		if (!(obj instanceof Reservierung))
			return false;
		Reservierung r = (Reservierung) obj;
		return r.getNr().compareTo(this.getNr()) == 0
				&& r.getZimmerNr().compareTo(this.getZimmerNr()) == 0;
	}

}
