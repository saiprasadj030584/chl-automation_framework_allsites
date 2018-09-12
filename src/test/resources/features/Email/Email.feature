@tag
Feature: As a user I have to trigger the automation email
 
  @Email
  Scenario: Triggering automation email
    Given Insert the metrics details in automation metrics DB
    Then I update the cucumber reports for the js files
    Then I trigger email to all the stakeholders
