//package UnitTest;
//
//
//import junit.framework.TestCase;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import capital.CheckSumCreditCard;
//
//public class CheckSumCreditCardTester extends TestCase {
//
//	private CheckSumCreditCard checkSum;
//
//	@Before
//	public void setUp() throws Exception {
//		checkSum = new CheckSumCreditCard();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		checkSum = null;
//	}
//
//	@Test
//	public void test() {
//		// VALID: 4388576018410707l
//		// INVALID: 4388576018402626l
//		boolean isValid1 = checkSum.isValid(4388576018410707l);
//		boolean isValid2 = checkSum.isValid(4388576018402626l);
//		assertTrue(isValid1);
//		assertFalse(isValid2);
//	}
//
//}
