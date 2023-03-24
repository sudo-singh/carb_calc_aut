package pages;

import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.ContextMap;
import enums.ContextEnum;
import utils.Assertions;
import utils.ElementActions;
import utils.JSONFileParser;
import utils.ThisResources;


public class CarbCalcPage extends Page{
	
	public WebDriver driver;
	public ContextMap brain;
	
	/**
	 * <p>
	 * Constructor to initialize driver and Scenario context
	 * @author sudosingh
	 * </p>
	 * @param driver driver instance for current scenario
	 * @param brain Context Map for current Scenario 
	 */
	public CarbCalcPage(WebDriver driver, ContextMap brain) {
		super(ThisResources.CARBOHYDRATE_CALCULATOR, driver);
		this.driver = driver;
		this.brain = brain;
	}
	
	/**
	 * US Units tab element
	 */
	private WebElement usUnitsHeader;
	public WebElement usUnitsHeader(){
		usUnitsHeader = driver.findElement(By.xpath("//a[contains(text(),'US Units')]"));
		return usUnitsHeader;
	}
	
	/**
	 * Age TextBox element
	 */
	private WebElement age;
	public WebElement age(){
		age = driver.findElement(By.id("cage"));
		return age;
	}
	
	/**
	 * Gender radio selector element
	 */
	private WebElement gender;
	public WebElement gender(String selectedGender){
		gender = driver.findElement(By.xpath("//label[text() = '"+selectedGender+"']"));
		return gender;
	}
	
	/**
	 * Weight TextBox element
	 */
	private WebElement weight;
	public WebElement weight(){
		weight = driver.findElement(By.id("cpound"));
		return weight;
	}
	
	/**
	 * Calculate button element
	 */
	private WebElement calculate;
	public WebElement calculate(){
		calculate = driver.findElement(By.xpath("//input[@value = 'Calculate']"));
		return calculate;
	}
	
	/**
	 * Title of a successful result
	 */
	private WebElement resultsHeader;
	public WebElement resultsHeader(){
		resultsHeader = driver.findElement(By.xpath("//h2[contains(text(), 'Result')]"));
		return resultsHeader;
	}
	
	/**
	 * Results Table element
	 */
	private WebElement resultsTable;
	public WebElement resultsTable(){
		resultsTable = driver.findElement(By.xpath("//table[@class = 'cinfoT']"));
		return resultsTable;
	}
	
	/**
	 * Place holder element for error message
	 */
	private WebElement errorMessagePlaceholder;
	public WebElement errorMessagePlaceholder(){
		errorMessagePlaceholder = driver.findElement(By.xpath("//font[@color = 'red']"));
		return errorMessagePlaceholder;
	}
	
	/**
	 * Error Message String Element
	 */
	private WebElement errorMessage;
	public WebElement errorMessage(String message){
		errorMessage = driver.findElement(By.xpath("//font[@color = 'red'][contains(text(), '"+message+"')]"));
		return errorMessage;
	}
	
	/**
	 * Clicks on the USUnits Tab
	 */
	public void clickUSUnitsTab() {
		ElementActions.clickElement(usUnitsHeader());
		brain.scenarioContext.setContext(ContextEnum.ACTIVE_UNIT_TYPE, "usunits_");
	}
	
	/**
	 * Add users data to the form
	 * @param dataType Valid or Invalid data
	 * @param indType Type of user for which the data is
	 */
	public void addCharacterData(String dataType, String indType) {
		JsonObject indData = null;
		switch(dataType) {
		case "\"valid\"":
			indData = new JSONFileParser("./"+ThisResources.DIR_PATH+brain.scenarioContext.getContext(ContextEnum.ACTIVE_UNIT_TYPE)+"positive.json")
			.getAKey(indType);
			break;
			
		case "\"invalid_age\"":
			indData = new JSONFileParser("./"+ThisResources.DIR_PATH+brain.scenarioContext.getContext(ContextEnum.ACTIVE_UNIT_TYPE)+"negative.json")
			.getAKey(indType);
			break;
			
		case "\"invalid_weight\"":
			indData = new JSONFileParser("./"+ThisResources.DIR_PATH+brain.scenarioContext.getContext(ContextEnum.ACTIVE_UNIT_TYPE)+"negative.json")
			.getAKey(indType);
			break;
			
		default:
			break;
		}
		
		ElementActions.sendKeys(age(), indData.get("Age".replaceAll("\"", "")).getAsString());
		ElementActions.clickElement(gender(indData.get("Gender".replaceAll("\"", "")).getAsString()));
		ElementActions.sendKeys(weight(), indData.get("Weight".replaceAll("\"", "")).getAsString());
	}
	
	/**
	 * Click on the Calculate button
	 * @param estType Type of Calculator
	 */
	public void calculateResults(String estType) {
		if(estType.equals("\"Mifflin St Jeor\"")) {
			ElementActions.jsClick(calculate(), driver);
		}
	}
	
	/**
	 * Asserting successful results
	 */
	public void assertSuccessfulCalculation() {
		Assertions.assertElementVisible(resultsHeader());
		Assertions.assertElementVisible(resultsTable());
	}
	
	/**
	 * Assert error messages based on the type of invalid data
	 */
	public void assertErrorMessage() {
		switch ((String)brain.scenarioContext.getContext(ContextEnum.INVALID_ENTRY)) {
		case "\"invalid_age\"":
			Assertions.assertElementVisible(errorMessagePlaceholder());
			Assertions.assertElementVisible(errorMessage(ThisResources.INVALID_AGE_ERROR));
			break;
			
		case "\"invalid_weight\"":
			Assertions.assertElementVisible(errorMessagePlaceholder());
			Assertions.assertElementVisible(errorMessage(ThisResources.INVALID_WEIGHT_ERROR));
			break;
			
		default:
			break;
		}
	}
}
