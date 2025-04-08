@DeploymentAutomation
Feature: User have to be automated the whole deployment process

  @DeployAuto
  Scenario: Deploy the automated for specific region
    Given user to launch telnet server for specific region
    And user checking the space in given environment
    And user stopping the app services
    Then execute the given package for release version "11.02"
    And user  start the app services
    Then user validate the post deployment
