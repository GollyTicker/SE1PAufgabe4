package a10.reservierungskomponente;

public class Zusatzleistung {

	private Integer nr;
	private String leistungsart;

	private Zusatzleistung(Integer nr, String leistungsart) {
		this.nr = nr;
		this.leistungsart = leistungsart;
	}

	public static Zusatzleistung zusatzleistung(Integer nr, String leistungsart) {
		return new Zusatzleistung(nr, leistungsart);
	}

	@Override
	public String toString() {
		return "Zusatzleistung{nr=" + nr + ", leistungsart=" + leistungsart
				+ "}";
	}

	public Integer getNr() {
		return nr;
	}

	public String getLeistungsart() {
		return leistungsart;
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
		if (!(obj instanceof Zusatzleistung))
			return false;
		Zusatzleistung z = (Zusatzleistung) obj;
		return z.getNr().compareTo(this.getNr()) == 0;
	}
}
