package Tests;

import static org.junit.Assert.*;
import Utilities.InvalidEmailException;

import org.junit.Test;

import static Gastkomponente.EmailTyp.email;
import Gastkomponente.EmailTyp;

public class EmailTest {

	@Test
	public void testEmailPositive() {
		EmailTyp email;
		try {
			email = email("swaneet", "hotmail", "com");
			assertEquals("swaneet", email.name());
			assertEquals("hotmail", email.server());
			assertEquals("com", email.country());
			assertEquals("swaneet@hotmail.com", email.toString());
		} catch (InvalidEmailException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = InvalidEmailException.class)
	public void testEmailNegative() throws InvalidEmailException {
		email("swaneet", "hotmail-", "com");
	}
}
