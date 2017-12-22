import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import interfaceMain.Email;
import interfaceMain.Rule;

public class MailTest {

	private static Email email;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		email = new Email("ola");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testName() {
		assertEquals(email.getName(),"ola");
	}
	
	@Test
	public void testArraySize() {
		

		email.addRule("A");
		email.addRule("B");
		email.addRule("C");
		email.addRule("D");
		email.addRule("E");
		
		assertEquals(email.getRulesList().size(),5);
	}

}
