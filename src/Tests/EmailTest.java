package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Typen.EmailTyp;

public class EmailTest {

	@Test
	public void testEmail() {
		EmailTyp email = EmailTyp.email("matthias", "gmail", "com");
		assertEquals("matthias", email.getName());
		assertEquals("gmail", email.getServer());
		assertEquals("com", email.getDomain());
		assertEquals("matthias@gmail.com", email.toString());
	}

}
