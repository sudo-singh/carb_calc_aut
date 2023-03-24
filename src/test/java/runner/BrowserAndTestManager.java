package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.testng.TestNGCucumberRunner;
import utils.HelperFunctions;
import utils.ThisResources;

/**
 * Instantiates Browser and driver
 * @author sudosingh
 *
 */
public class BrowserAndTestManager {
	
	protected TestNGCucumberRunner testNGCucumberRunner;
	public static WebDriver driver;
	
	/**
	 * Sets browser type with desired capabilities
	 * @param browserType The input from xml, can be overridden from shell file 
	 * @throws Exception
	 */
	public void initialiseBrowserInstance(String browserType) throws Exception {
		if (HelperFunctions.getOSName().toLowerCase().indexOf("win") >= 0){
			switch(browserType) {
			case "Chrome":
				ChromeOptions ops = new ChromeOptions();
				ops.addArguments("--remote-allow-origins=*");
				System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "Firefox":
				System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			}
		}
		else {
			switch(browserType) {
			case "Chrome":
				ChromeOptions ops = new ChromeOptions();
				ops.addArguments("--remote-allow-origins=*");
				System.setProperty("webdriver.chrome.driver", "./chromedriver");
				driver = new ChromeDriver(ops);
				break;
			case "Firefox":
				System.setProperty("webdriver.gecko.driver", "./geckodriver");
				driver = new FirefoxDriver();
				break;
			}
		}
		driver.manage().window().maximize();
	}
	
	/**
	 * Sets up environment, brings up the home page of AUT
	 * @param env
	 */
	public void setupEnvironment(String env) {
		switch(env) {
		case "QA":
			driver.get(ThisResources.BASE_URL);
			break;
			
		default:
			break;
		}
	}
	
}
