package Gastkomponente;

import java.lang.IllegalArgumentException;

public final class EmailTyp {

	private String name;
	private String server;
	private String country;

	private EmailTyp(String name, String server, String country) {
		assertValidEmail(name, server, country);
		this.name = name;
		this.server = server;
		this.country = country;
	}

	public static EmailTyp email(String name, String server, String country) {
		return new EmailTyp(name, server, country);
	}

	public String name() {
		return this.name;
	}

	public String server() {
		return this.server;
	}

	public String country() {
		return this.country;
	}

	private void assertValidEmail(String name, String server, String country) {
		try {
			if (!validEmail(name, server, country))
				throw new IllegalArgumentException("invalid email");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private boolean validEmail(String name, String server, String country) {
		if (name.matches("[a-z]+") && server.matches("[a-z]+")
				&& country.matches("[a-z]+")) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int acc = 0;
		for (byte b : this.toString().getBytes())
			acc += b;
		long longBits = Double.doubleToLongBits(acc);
		return (int) (longBits ^ (longBits >>> 32));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof EmailTyp))
			return false;
		EmailTyp temp = (EmailTyp) obj;
		return temp.name() == this.name() && temp.server() == this.server()
				&& temp.country() == country();
	}

	@Override
	public String toString() {
		return this.name() + "@" + this.server() + "." + country();
	}
}
