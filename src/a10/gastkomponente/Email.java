package a10.gastkomponente;

import javax.management.InvalidAttributeValueException;

public final class Email {

	private String name;
	private String server;
	private String domain;

	// Initialization
	private Email(String name, String server, String domain) {
		emailIsValidOrThrow(name, server, domain);
		this.name = name;
		this.server = server;
		this.domain = domain;
	}

	public static Email email(String name, String server, String domain) {
		return new Email(name, server, domain);
	}

	@Override
	public String toString() {
		return this.getName() + "@" + this.getServer() + "." + this.getDomain();
	}

	// Selector
	public String getName() {
		return this.name;
	}

	public String getServer() {
		return this.server;
	}

	public String getDomain() {
		return this.domain;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Email))
			return false;
		Email temp = (Email) obj;
		return temp.getName() == this.getName()
				&& temp.getServer() == this.getServer()
				&& temp.getDomain() == this.getDomain();
	}

	@Override
	public int hashCode() {
		int acc = 0;
		for (byte b : this.toString().getBytes())
			acc += b;
		long longBits = Double.doubleToLongBits(acc);
		return (int) (longBits ^ (longBits >>> 32));
	}

	// Exception Handling
	private void emailIsValidOrThrow(String name, String server, String domain) {
		try {
			if (!emailIsValid(name, server, domain))
				throw new InvalidAttributeValueException(
						"name, server and domain shall match regexp [a-z]+");
		} catch (InvalidAttributeValueException iave) {
			iave.printStackTrace();
		}
	}

	private boolean emailIsValid(String name, String server, String domain) {
		if (name.matches("[a-z]+") && server.matches("[a-z]+")
				&& domain.matches("[a-z]+")) {
			return true;
		}
		return false;
	}

}
