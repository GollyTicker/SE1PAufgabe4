package Reservierungskomponente;

public class ZusatzleistungTyp {

	private Integer nr;
	private String leistungsart;

	private ZusatzleistungTyp(Integer nr, String leistungsart) {
		this.nr = nr;
		this.leistungsart = leistungsart;
	}

	public static ZusatzleistungTyp zusatzleistung(Integer nr, String leistungsart) {
		return new ZusatzleistungTyp(nr, leistungsart);
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
		if (!(obj instanceof ZusatzleistungTyp))
			return false;
		ZusatzleistungTyp z = (ZusatzleistungTyp) obj;
		return z.getNr().compareTo(this.getNr()) == 0;
	}
}
