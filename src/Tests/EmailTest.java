package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static Gastkomponente.EmailTyp.email;
import Gastkomponente.EmailTyp;

public class EmailTest {

	@Test
	public void testEmail() {
		EmailTyp email = email("swaneet", "hotmail", "com");
		assertEquals("swaneet", email.name());
		assertEquals("hotmail", email.server());
		assertEquals("com", email.country());
		assertEquals("swaneet@hotmail.com", email.toString());
	}
}
