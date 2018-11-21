@Sprint02
Feature: Receiving
  As a Exit DC user should be able to login
   so that I validate the  master data setup

@tag1
Scenario: Title of your scenario
@TC01_FSV_receiving
  Scenario: Validate FSV Receiving
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT
    And Validation of List Id generated with prefix as IDT
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter ListId and TagId for IDT
    And I navigate to Order header screen to verify the status in Ready to Load
