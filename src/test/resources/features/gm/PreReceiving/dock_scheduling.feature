@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door
  So that I can receive the PO in the scheduled dock door

  @boxed @pre_receiving @direct_po @boxed_pre_receiving_direct_po_dock_schedule_asn @complete
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

  @boxed @pre_receiving @direct_po @boxed_pre_receiving_direct_po_delete_booking_asn @complete
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

  @boxed @pre_receiving @direct_po @boxed_pre_receiving_direct_po_move_booking_diff_time_sameday_asn @complete
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

  @boxed @pre_receiving @direct_po @boxed_pre_receiving_direct_po_change_status_of_booking_to_complete_asn @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | DataType | SiteId | BookingStatus |
      | PO2010002026 | PO000504560005112370 | PO00100529 | Hanging  |   5649 | Complete      |
      | PO2010002026 | PO000504560005112370 | PO00100529 | Hanging  |   5649 | In Progress   |

  @boxed @pre_receiving @fsv_po @boxed_pre_receiving_fsv_po_move_booking_diff_time_sameday @complete
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

  @boxed @pre_receiving @fsv_po @boxed_pre_receiving_fsv_po_delete_booking @complete
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

  @boxed @pre_receiving @fsv_po @boxed_pre_receiving_fsv_po_change_status_of_booking_to_complete @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - FSV PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | PreAdviceID  | DataType | SiteId |
      | PO2010002039 | Hanging  |   5649 |

  @boxed @pre_receiving @returns_rms @boxed_pre_receiving_returns_rms_delete_booking @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId |
      | 58850008387770077010083877700300 | 0000838777 | Hanging  |   5885 |

  @boxed @pre_receiving @returns_rms @boxed_pre_receiving_returns_rms_move_booking_diff_time_sameday @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId |
      | 58850008388770077010083887700300 | 0000838877 | Hanging  |   5885 |

  @boxed @pre_receiving @returns_rms @boxed_pre_receiving_returns_rms_change_status_of_booking_to_complete @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId |BookingStatus|
      | 58850008389770077010083897700300 | 0000838977 | Hanging  |   5885 |Complete|

  @boxed @pre_receiving @returns_non_rms @boxed_pre_receiving_returns_non_rms_delete_booking @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns NON RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | UPIId               | ASNId   | DataType | SiteId |
      | 3000000000000000017 | 1220072 | Hanging  |   5885 |

  @boxed @pre_receiving @returns_non_rms @boxed_pre_receiving_returns_non_rms_move_booking_diff_time_sameday @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns NON RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | UPIId               | ASNId   | DataType | SiteId |
      | 3000000000000000018 | 1220073 | Hanging  |   5885 |

  @boxed @pre_receiving @returns_non_rms @boxed_pre_receiving_returns_non_rms_change_status_of_booking_to_complete @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns NON RMS
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking
    Then the booking id details with updated status should be displayed on the page

    Examples: 
      | UPIId               | ASNId   | DataType | SiteId |
      | 3000000000000000019 | 1220074 | Hanging  |   5885 |

  @move_booking_diff_date_Direct_PO
  Scenario Outline: Validate whether Booking can be moved to different date for Direct PO - Boxed
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | PreAdviceID  | UPIId                | ASNId      | DataType | SiteId |
      #| P02010002039 | P0000504560005112376 | P000100535 | Hanging  |   5649 |
      | GM7010002039 | GM700504560005112376 | GM70100535 | Hanging  |   5649 |

  @move_booking_diff_date_FSV_PO @in_review
  Scenario Outline: Validate whether Booking can be moved to different date for FSV PO - Boxed
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | PreAdviceID  | DataType | SiteId | BookingStatus |
      # | PO2019802049 | Boxed    |   5885 | Complete      |
      | PO2019902049 | Boxed    |   5885 | Complete      |


