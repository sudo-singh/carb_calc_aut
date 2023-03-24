package pages;

import org.openqa.selenium.WebDriver;

import utils.ThisResources;

/**
 * Parent Page class
 * @author sudosingh
 *
 */
public class Page {
	
	protected String pageUrl;
	public WebDriver driver;
	
	/**
	 * <p>
	 * Super class constructor for all the page classes
	 * </p>
	 * @param pageUrl end-point for child page
	 * @param driver driver instance for the running scenario
	 */
	public Page(String pageUrl, WebDriver driver) {
		this.pageUrl = pageUrl;
		this.driver = driver;
	}
	
	/**
	 * <p>
	 * Loads the page initialized in the constructor
	 * </p>
	 */
	public void pageLoader() {
		driver.get(ThisResources.BASE_URL + pageUrl);
	}
	
}
