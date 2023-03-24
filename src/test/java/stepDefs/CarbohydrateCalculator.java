package stepDefs;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import cucumber.ContextMap;
import enums.ContextEnum;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarbCalcPage;
import runner.TestRunner;

/**
 * Step definitions for the Carbohydrate Calculator page of the application
 * @author sudosingh
 *
 */
public class CarbohydrateCalculator extends TestRunner{
	
	WebDriver driver;
	ContextMap context;
	
	public CarbohydrateCalculator(TestRunner test, ContextMap brain) {
		this.driver = TestRunner.driver;
		this.context = brain;
	}
	
	@Given("^A user wants to calculate Carbohydrates$")
	public void naviagteToCarbohydratePage() {
		CarbCalcPage ccp = new CarbCalcPage(driver, context);
		context.scenarioContext.setContext(ContextEnum.CURRENT_CLASS_OBJECT, ccp);
		ccp.pageLoader();
    }
	
	@When("^the user switches to (.*) tab$")
    public void switchToATab(String userType) {
		CarbCalcPage ccp = (CarbCalcPage) context.scenarioContext.getContext(ContextEnum.CURRENT_CLASS_OBJECT);
		ccp.clickUSUnitsTab();
		context.scenarioContext.setContext(ContextEnum.CURRENT_CLASS_OBJECT, ccp);
    }
	
	@When("^the user enters (.*) (.*) data$")
    public void enterTestData(String dataType, String indType) throws InterruptedException, StreamReadException, DatabindException, IOException {
		CarbCalcPage ccp = (CarbCalcPage) context.scenarioContext.getContext(ContextEnum.CURRENT_CLASS_OBJECT);
		ccp.addCharacterData(dataType, indType);
		context.scenarioContext.setContext(ContextEnum.INVALID_ENTRY, dataType);
		context.scenarioContext.setContext(ContextEnum.CURRENT_CLASS_OBJECT, ccp);
    }
	
	@When("^the user calculates with (.*)$")
    public void calculate(String estFormula) throws InterruptedException {
		CarbCalcPage ccp = (CarbCalcPage) context.scenarioContext.getContext(ContextEnum.CURRENT_CLASS_OBJECT);
		ccp.calculateResults(estFormula);
		context.scenarioContext.setContext(ContextEnum.CURRENT_CLASS_OBJECT, ccp);
    }
	
	@Then("^comprehensive results should be visible$")
    public void verifyPositiveResults() throws InterruptedException {
		CarbCalcPage ccp = (CarbCalcPage) context.scenarioContext.getContext(ContextEnum.CURRENT_CLASS_OBJECT);
		ccp.assertSuccessfulCalculation();
    }
	
	@Then("^the user should see appropriate error message$")
    public void verifyErrorMessage() throws InterruptedException {
		CarbCalcPage ccp = (CarbCalcPage) context.scenarioContext.getContext(ContextEnum.CURRENT_CLASS_OBJECT);
		ccp.assertErrorMessage();
    }
}
