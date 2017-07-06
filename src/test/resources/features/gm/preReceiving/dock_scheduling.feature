@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door
  So that I can receive the PO in the scheduled dock door

  @wip_dock
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<Type>" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | Type    |
      #| PO20170201   | PO40000000000000001  | PO111001   | Hanging |
      | PO2010002003 | PO000504560005112356 | PO00001015 | Hanging |
