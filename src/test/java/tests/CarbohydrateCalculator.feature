#Author: your.email@your.domain.com
#Keywords Summary :

@regression
Feature: Other Units Functionality on Carbohydrate Calculator

  @regression 
  @smoke
  Scenario: Verify that results are shown on entering valid data
    Given A user wants to calculate Carbohydrates
    When the user switches to "US Units" tab
    And the user enters "valid" "Adult_Male" data
    And the user calculates with "Mifflin St Jeor"
    Then comprehensive results should be visible   
    

  @regression 
  @sanity
  Scenario: Verify that appropriate error messages are shown on entering invalid data
    Given A user wants to calculate Carbohydrates
    When the user switches to "US Units" tab
    And the user enters "invalid" "Adult_Male" data
    And the user calculates with "Mifflin St Jeor"
    Then the user should see appropriate error message
