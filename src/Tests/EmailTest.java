package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Gastkomponente.EmailTyp;

public class EmailTest {

	@Test
	public void testEmail() {
		EmailTyp email = EmailTyp.email("matthias", "gmail", "com");
		assertEquals("matthias", email.name());
		assertEquals("gmail", email.server());
		assertEquals("com", email.country());
		assertEquals("matthias@gmail.com", email.toString());
	}

}
