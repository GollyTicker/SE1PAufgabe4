package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import a10.gastkomponente.Email;

public class EmailTest {

	@Test
	public void testEmail() {
		Email email = Email.email("matthias", "gmail", "com");
		assertEquals("matthias", email.getName());
		assertEquals("gmail", email.getServer());
		assertEquals("com", email.getDomain());
		assertEquals("matthias@gmail.com", email.toString());
	}

}
