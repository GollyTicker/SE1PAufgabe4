package Gastkomponente;


public class GastTyp {

	private Integer nr;
	private String name;
	private EmailTyp email;
	private Boolean istStammkunde;

	private GastTyp(Integer nr, String name, EmailTyp email, Boolean istStammkunde) {
		this.nr = nr;
		this.name = name;
		this.email = email;
		this.istStammkunde = istStammkunde;
	}
	
	public static GastTyp gast(Integer nr, String name, EmailTyp email, Boolean istStammkunde) {
		return new GastTyp(nr, name, email, istStammkunde);
	}

	@Override
	public String toString() {
		return "Gast{nr=" + nr + ", name=" + name + ", email=" + email
				+ ", istStammkunde=" + istStammkunde + "}";
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmailTyp getEmail() {
		return email;
	}

	public void setEmail(EmailTyp email) {
		this.email = email;
	}

	public Boolean istStammkunde() {
		return istStammkunde;
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
		return g.getNr().compareTo(this.getNr()) == 0;
	}

}