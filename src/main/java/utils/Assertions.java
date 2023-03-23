package utils;

import org.openqa.selenium.WebElement;
import org.testng.asserts.Assertion;

/**
 * <p>
 * Wrapper class for Assertions to be used throughout the framework
 * </p>
 * @author Singh
 *
 */
public class Assertions {
	
	/**
	 * <p>
	 * Asserts whether the two provided String values are equal
	 * </p>
	 * @param actual
	 * @param expected
	 * @throws AssertionError
	 */
	public static void verifyAssertEqual(String actual, String expected) throws AssertionError {
        //TODO: Add Implementation 
    }
	
	public static void assertElementVisible(WebElement element) {
		Assertion assertCondition = new Assertion();
		assertCondition.assertTrue(element.isDisplayed());
	}
	
	public static void assertElementNotVisible(WebElement element) {
		Assertion assertCondition = new Assertion();
		assertCondition.assertFalse(element.isDisplayed());
	}
}