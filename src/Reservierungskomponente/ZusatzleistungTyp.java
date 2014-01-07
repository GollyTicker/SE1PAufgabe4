package Reservierungskomponente;

public class ZusatzleistungTyp {

	private Integer nr;
	private String lstgArt;

	private ZusatzleistungTyp(Integer nr, String lstgArt) {
		
		this.nr = nr;
		this.lstgArt = lstgArt;
	}

	public static ZusatzleistungTyp zusatzleistung(Integer nr, String lstgArt) {
		
		return new ZusatzleistungTyp(nr, lstgArt);
	}

	public Integer nr() {
		
		return nr;
	}

	public String leistungsart() {
		
		return lstgArt;
	}

	@Override
	public String toString() {
		
		return "Zusatzleistung{nr=" + nr + ", lstgArt=" + lstgArt + "}";
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
		return z.nr().compareTo(this.nr()) == 0;
	}
}
