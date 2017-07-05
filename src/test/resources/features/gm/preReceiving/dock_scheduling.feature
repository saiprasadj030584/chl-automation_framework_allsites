@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door
  So that I can receive the PO in the scheduled dock door

  @wip_dock
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      |
      | PO2010002121 | PO000504560005112356 | PO00001005 |

  @delete_booking
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "Boxed"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      |
      | PO2010002121 | PO000504560005112356 | PO00001005 |
      
      
      @move_booking_diff_time_sameday
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "Boxed"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page 

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      |
      | PO2010002121 | PO000504560005112356 | PO00001005 |
