package cucumber;

public class ContextMap {
public ScenarioContext scenarioContext;
	
	/**
	 * <p>
	 * Assigns object references to the context reference variables
	 * </p> 
	 */
	public ContextMap(){
		scenarioContext = new ScenarioContext();
	}
	
	/**
	 * <p>
	 * To get the {@link HashMap} object for {@link ScenarioContext}
	 * </p>
	 * @return the reference for the {@link HashMap} object
	 */
	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}
}
