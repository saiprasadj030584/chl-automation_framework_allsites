@trailer_creation_and_dock_scheduling
Feature: Dock booking for STO
  As a warehouse user
  I want to create a trailer and link it to a dock door 
  So that I can despatch the order to the store or DCS

  @wip01
    Scenario: Create dock booking for STO
    #Given I have logged in as warehouse user in JDA dispatcher food application
    #And the order "<93482394823>" should be in "Ready to Load" status

    When I create a trailer in trailer Maintenance page
    Then the trailer should be created

    When I create new dock booking
    And I select booking type and consignment
    And I select the slot 
    And I enter booking details
    And I proceed to complete the process
    Then the booking details should be appeared in the dock scheduler booking
