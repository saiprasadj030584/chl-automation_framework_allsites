@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door
  So that I can receive the PO in the scheduled dock door

  @dock_schedule_asn_direct_po @pre_receiving @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<Type>" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type and ASN
    And I select the slot
    And I create a booking
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | Type    | SiteId |
      | PO2010002003 | PO000504560005112356 | PO00100506 | Hanging |   5649 |

  @delete_booking_asn_direct_po @pre_receiving @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | DataType | SiteId |
      #| PO2010002003 | PO000504560005112356 | PO00100506 | Hanging  |5649 |
      #| PO2010002017 | PO000504560005112361 | PO00100520 | Hanging  |5649 |
      | PO2010002018 | PO000504560005112362 | PO00100521 | Hanging  |   5649 |

  @move_booking_diff_time_sameday_asn_direct_po @pre_receiving @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | DataType | SiteId |
      | PO2010002032 | PO000504560005112376 | PO00100535 | Hanging  |   5649 |

  @change_status_of_booking_to_complete_asn_direct_po @pre_receiving @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking
    Then the booking id details with updated status should be displayed on the page

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | DataType | SiteId |
      | PO2010002026 | PO000504560005112370 | PO00100529 | Hanging  |   5649 |

  @move_booking_diff_time_sameday_FSV_PO @pre_receiving @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - FSV PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | PreAdviceID  | DataType | SiteId |
      | PO2010002049 | Hanging  |   5649 |

  @delete_booking_FSV_PO @pre_receiving @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - FSV PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | PreAdviceID  | DataType | SiteId |
      | PO2010002037 | Hanging  |   5649 |

  @change_status_of_booking_to_complete_FSV_PO @pre_receiving @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - FSV PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking
    Then the booking id details with updated status should be displayed on the page

    Examples: 
      | PreAdviceID  | DataType | SiteId |
      | PO2010002039 | Hanging  |   5649 |
      
      
      @delete_booking_returns_RMS
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | UPIId                						 | ASNId      | DataType | SiteId |
      | 58850008387770077010083877700300 | 0000838777 | Hanging  |   5885 |

  @move_booking_diff_time_sameday_returns_RMS
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | UPIId                						 | ASNId      | DataType | SiteId |
      | 58850008388770077010083887700300 | 0000838877 | Hanging  |   5885 |

  @change_status_of_booking_to_complete_returns_RMS
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking
    Then the booking id details with updated status should be displayed on the page

    Examples: 
      | UPIId                						 | ASNId      | DataType | SiteId |
      | 58850008389770077010083897700300 | 0000838977 | Hanging  |   5885 |

      @delete_booking_returns_NON_RMS
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns NON RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | UPIId                | ASNId      | DataType | SiteId |
      | 3000000000000000017 | 1220072 | Hanging  |   5885 |

  @move_booking_diff_time_sameday_returns_NON_RMS
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns NON RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | UPIId                | ASNId      | DataType | SiteId |
      | 3000000000000000018 | 1220073 | Hanging  |   5885 |

  @change_status_of_booking_to_complete_returns_NON_RMS
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns NON RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking
    Then the booking id details with updated status should be displayed on the page

    Examples: 
      | UPIId                | ASNId      | DataType | SiteId |
      | 3000000000000000019 | 1220074 | Hanging  |   5885 |

      
      
