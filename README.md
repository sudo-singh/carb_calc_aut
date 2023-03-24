# Carbohydrate Calculator - BDD Framework
Basic E2E UI flow Automation framework using Selenium-Java.
- This framework incorporates TestNG and Cucumber for writing End to End tests.
- This framework uses TestNG annotations with Cucumber's easy to read Gherkin syntax to write Acceptance tests
- We can specify tags on Cucumber Scenarios and specify during runtime which Scenarios we want to execute
- Uses Maven for building Projects and Dependency management

### Pre - Requisites
Please install the listed versions of software's to avoid any exceptions while executing the Test Suite

>NOTE: This is considering you are working on a 64-bit OS with Eclipse IDE installed and configured beforehand

- **Java** : Version 8
- **Apache Maven** : Version 3.8.2
- **Chrome** : Version 111
- **Firefox** : Version 111

OS Specific Requirements:

|OS        |Requirement |Resource         |
|----------------|----------------------------|--------|
|MacOS        |Unzip   | ```brew install unzip```|
|MacOS|   wget | ```brew install wget```|
|Linux|  Unzip | ```sudo apt-get install unzip```|
|Linux | wget | ```sudo apt-get install wget```|
|Windows | cURL | Install Git for Windows|


  
```bash
 git clone git@github.com:sudo-singh/carb_calc_aut.git
 cd carb_calc_aut
 ```


### Writing Tests
#### Features
- Cucumber lets anyone write Acceptance tests and closes the gap between the Stakeholders/Product Owners and teams who have more of a technical responsibility.
- The above is achieved by specifying a set of rules to be followed while writing tests in Gherkin
- For Example:

```gherkin
@regression 
  @smoke
  Scenario: Verify that results are shown on entering valid data
    Given A user wants to calculate Carbohydrates
    When the user switches to "US Units" tab
    And the user enters "valid" "Adult_Male" data
    And the user calculates with "Mifflin St Jeor"
    Then comprehensive results should be visible
```

|Keywords                |Usage                                               |
|----------------|----------------------------|
|Scenario            |It specifies the rule or what the steps in Scenario have to achieve|
|Given            |It specifies the preconditions that should be set before actual test|
|When|It specifies the actual actions that have to be performed in test |	
|Then|It is used to assert whether the result of all the actions is as expected|
- For more info on the significance of Gherkin keywords and other ways to write Scenario's, please refer to the [Cucumber Documentation](https://cucumber.io/docs/gherkin/reference/)

#### Glue Code
```java
@Given("^A user wants to calculate Carbohydrates$")
	public void naviagteToCarbohydratePage() {
		CarbCalcPage ccp = new CarbCalcPage(driver, context);
		context.scenarioContext.setContext(ContextEnum.CURRENT_CLASS_OBJECT, ccp);
		ccp.pageLoader();
    }
```

### Running Scripts

- Setting up drivers for test execution 

If on a Windows OS

> setupDrivers.bat

If on a Linux/MacOS
> ./setupDrivers.sh
- After the Drivers have been downloaded and extracted, its time to execute the suite

If on a Windows OS
> simpleRunner.bat test1

Here tagName specifies all the cucumber scenario's with that partiacular tag

If on a Linux/MacOS
>./simpleRunner.sh tagName

### TO-DO
- Add Stringified keywords as Enums in Page Classes to improve readability
- Improve the JSONParserUtil, and the hierarchy structure of test data itself
- Add more values to Context at runtime to reduce whatever hardcoding is there
- Add multiple Capabilities to Driver Instance, to incorporate headless browsing etc.
- Improve shell script to provide multiple cucumber overriding options
- Set global wait times and other variables to remove flakiness
- Add reporting and Email Util