@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door
  So that I can receive the FSV PO in the scheduled dock door

@wip_fsv
  Scenario Outline: Dock Scheduling with Pre-Advice Booking type
    Given the PO "<PreAdviceID>" of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type preadvice
    And I select the slot
    And I create a booking
    Then booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | SiteId |
      | PO2010002240 |   5649 |