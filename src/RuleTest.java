import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import interfaceMain.Rule;

public class RuleTest {
	
	private static Rule rule;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rule = new Rule("Rule", 5.0);
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
		assertEquals(rule.getName(), "Rule");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPeso() {
		assertEquals(rule.getPeso(), 5.0, 00.1);
	}
	
	

}
