@dock_scheduler
Feature: Dock booking operations for STO
  As a warehouse user
  I want to perform various operations in dock scheduler
  So that I can link the trailer to the dock door

  @wip01
  Scenario: Delete dock booking
    Given I have done the dock scheduler booking for the consignment
    When I navigate to dock scheduler start page
    And I select view existing bookings
    And I search the booking id
    Then the booking id details should be displayed
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

  @complete
  Scenario: Search booking details by notes
    Given I have done the dock scheduler booking for the consignment
    When I navigate to dock scheduler start page
    And I select view existing bookings
    And I search the booking id by notes
    Then the booking details should be displayed

  @complete
  Scenario: Multiple dock scheduler booking with the same slot by move bookings
    Given I have done the two dock scheduler booking for the consignment
    When I navigate to dock scheduler start page
    And I select view existing bookings
    And I search the first booking id
    And I move the booking
    And I select the second booked slot
    And I proceed to next
    Then the error message "This booking overruns an existing booking" should be displayed
