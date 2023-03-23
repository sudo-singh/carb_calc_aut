package runner;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(
		features = "src/test/java/tests",
		glue = {"stepDefs","runner"},
		tags = "@regression")

public class TestRunner extends BrowserAndTestManager {
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"browser", "env"})
	public void setUpClass(String browser, String env) throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		initialiseBrowserInstance(browser);
		setupEnvironment(env);	
	}
	
	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
	public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) throws Throwable {
	    testNGCucumberRunner.runScenario(pickle.getPickle());
	}
	
	@DataProvider
	public Object[][] scenarios() {
		return testNGCucumberRunner.provideScenarios();
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
		driver.quit();
	}
}
