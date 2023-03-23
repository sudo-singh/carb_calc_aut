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
	 * </p>
	 * @param driver driver instance for current scenario
	 * @param brain Context Map for current Scenario
	 */
	public CarbCalcPage(WebDriver driver, ContextMap brain) {
		super(ThisResources.CARBOHYDRATE_CALCULATOR, driver);
		this.driver = driver;
		this.brain = brain;
	}
	
	private WebElement usUnitsHeader;
	public WebElement usUnitsHeader(){
		usUnitsHeader = driver.findElement(By.xpath("//a[contains(text(),'US Units')]"));
		return usUnitsHeader;
	}
	
	private WebElement age;
	public WebElement age(){
		age = driver.findElement(By.id("cage"));
		return age;
	}
	
	private WebElement gender;
	public WebElement gender(String selectedGender){
		gender = driver.findElement(By.xpath("//label[text() = '"+selectedGender+"']"));
		return gender;
	}
	
	private WebElement calculate;
	public WebElement calculate(){
		calculate = driver.findElement(By.xpath("//input[@value = 'Calculate']"));
		return calculate;
	}
	
	private WebElement resultsHeader;
	public WebElement resultsHeader(){
		resultsHeader = driver.findElement(By.xpath("//h2[contains(text(), 'Result')]"));
		return resultsHeader;
	}
	
	private WebElement resultsTable;
	public WebElement resultsTable(){
		resultsTable = driver.findElement(By.xpath("//table[@class = 'cinfoT']"));
		return resultsTable;
	}
	
	private WebElement errorMessage;
	public WebElement errorMessage(String message){
		errorMessage = driver.findElement(By.xpath("//font[@color = 'red'][contains(text(), '"+message+"')]"));
		return errorMessage;
	}
	
	
	public void clickUSUnitsTab() {
		ElementActions.clickElement(usUnitsHeader());
		brain.scenarioContext.setContext(ContextEnum.ACTIVE_UNIT_TYPE, "usunits_");
	}
	
	public void addCharacterData(String dataType, String indType) {
		JsonObject indData;
		if(dataType.equals("\"valid\"")) {
			indData = new JSONFileParser("./"+ThisResources.DIR_PATH+brain.scenarioContext.getContext(ContextEnum.ACTIVE_UNIT_TYPE)+"positive.json")
					.getAKey(indType);
		}
		else {
			indData = new JSONFileParser("./"+ThisResources.DIR_PATH+brain.scenarioContext.getContext(ContextEnum.ACTIVE_UNIT_TYPE)+"negative.json")
					.getAKey(indType);
		}
		ElementActions.sendKeys(age(), indData.get("Age".replaceAll("\"", "")).getAsString());
		ElementActions.clickElement(gender(indData.get("Gender".replaceAll("\"", "")).getAsString()));
	}
	
	public void calculateResults(String estType) {
		if(estType.equals("\"Mifflin St Jeor\"")) {
			ElementActions.jsClick(calculate(), driver);
		}
	}
	
	public void assertSuccessfulCalculation() {
		Assertions.assertElementVisible(resultsHeader());
		Assertions.assertElementVisible(resultsTable());
	}
	
	public void assertErrorMessage() {
		Assertions.assertElementVisible(errorMessage(ThisResources.INVALID_AGE_ERROR));
	}
}
