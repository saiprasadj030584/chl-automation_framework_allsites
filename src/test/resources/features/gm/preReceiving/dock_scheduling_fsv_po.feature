@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door
  So that I can receive the FSV PO in the scheduled dock door

  @dock_schedule_fsv_po @pre_receiving @wip @boxed
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given the PO "<PreAdviceID>" of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type preadvice
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | SiteId |
      | PO2010002240 |   5649 |
