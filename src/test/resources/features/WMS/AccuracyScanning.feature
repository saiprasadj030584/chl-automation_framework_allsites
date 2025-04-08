#Author: girija.panda@nokia.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@AccuracyScanning
Feature: Title of your feature
  I want to use this feature to perform accuracy scanning

  @perform_accuracy_scanning_with_BEL
  Scenario: Login until Accuracy Scanning
    Given Login to terminal
    And Go to Main menu
    And Perform accuracy scanning with BEL "07708100784760588500233100000010","07674044","1"
