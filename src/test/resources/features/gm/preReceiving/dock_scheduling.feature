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
      | PO2010002003 | PO000504560005112356 | PO00100506 | Hanging |

  @delete_booking
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | DataType |
      #| PO2010002003 | PO000504560005112356 | PO00100506 | Hanging  |
      #| PO2010002017 | PO000504560005112361 | PO00100520 | Hanging  |
      | PO2010002018 | PO000504560005112362 | PO00100521 | Hanging  |

  @move_booking_diff_time_sameday
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | DataType |
      | PO2010002032 | PO000504560005112376 | PO00100535 | Hanging  |

  @change_status_of_booking_to_complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking
    Then the booking id details with updated status should be displayed on the page

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | DataType |
      | PO2010002026 | PO000504560005112370 | PO00100529 | Hanging  |

  @move_booking_diff_time_sameday_FSV_PO
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - FSV PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | PreAdviceID  | DataType |
      | PO2010002049 | Hanging  |

  @delete_booking_FSV_PO
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - FSV PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
     | PreAdviceID  | DataType |
      | PO2010002037 | Hanging  |

  @change_status_of_booking_to_complete_FSV_PO
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - FSV PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking
    Then the booking id details with updated status should be displayed on the page

    Examples: 
      | PreAdviceID  | DataType |
      | PO2010002039 | Hanging  |
