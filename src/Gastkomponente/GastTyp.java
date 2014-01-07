package Gastkomponente;

public class GastTyp {

	private Integer nr;
	private String name;
	private EmailTyp email;
	private Boolean stammkunde;

	private GastTyp(Integer nr, String name, EmailTyp email, Boolean stammkunde) {
		this.nr = nr;
		this.name = name;
		this.email = email;
		this.stammkunde = stammkunde;
	}

	public static GastTyp gast(Integer nr, String name, EmailTyp email,
			Boolean stammkunde) {
		return new GastTyp(nr, name, email, stammkunde);
	}

	public Integer nr() {
		return nr;
	}

	public String name() {
		return name;
	}

	public EmailTyp email() {
		return email;
	}

	public Boolean istStammkunde() {
		return stammkunde;
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
		if (!(obj instanceof GastTyp))
			return false;
		GastTyp g = (GastTyp) obj;
		return Integer.compare(g.nr(), nr()) == 0;
	}

	@Override
	public String toString() {
		return "Gast(" + nr() + ", " + name() + ", " + istStammkunde() + ")";
	}

}