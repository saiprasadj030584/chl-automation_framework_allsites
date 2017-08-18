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
    And I create a booking for the asn
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

  @change_status_of_booking_asn_direct_po @pre_receiving @complete
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

  @boxed_pre_receiving_fsv_po_move_booking_diff_time_sameday @pre_receiving @complete @boxed @fsv_po
  Scenario Outline: Validate whether Booking can be moved to different date
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

  @change_status_of_booking_FSV_PO @pre_receiving @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - FSV PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | PreAdviceID  | DataType | SiteId | BookingStatus |
      | PO2010002039 | Hanging  |   5649 | Complete      |

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
      | UPIId                            | ASNId      | DataType | SiteId |
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
      | UPIId                            | ASNId      | DataType | SiteId |
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
      | UPIId                            | ASNId      | DataType | SiteId |
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
      | UPIId               | ASNId   | DataType | SiteId |
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
      | UPIId               | ASNId   | DataType | SiteId |
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

  @move_booking_diff_date_RMS_returns @in_review
  Scenario Outline: Validate whether Booking can be moved to different date for RMS Returns - Boxed
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId |
      #  | 58850008388770077010083887700300 | 0000838877 | Boxed  |   5885 |
      #| 58850008988770077010089887700300 | 0000898877 | Boxed    |   5885 |
      # | 58850008998770077010089987700300 | 0000899877 | Boxed    |   5885 |
      | 58850001188770077010011887700300 | 0000118877 | Boxed    |   5885 |
      | 58850001288770077010012887700300 | 0000128877 | Boxed    |   5885 |

  @move_booking_diff_date_Non_RMS_returns @in_review1
  Scenario Outline: Validate whether Booking can be moved to different date for Non RMS Returns - Boxed
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | UPIId               | ASNId   | DataType | SiteId |
      | 3000000000000980018 | 1229873 | Boxed    |   5885 |

  #| 3000000000000990018 | 1229973 | Boxed    |   5885 |
  @change_status_of_booking_asn_FSV_PO @pre_receiving
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - FSV PO
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | PreAdviceID  | DataType | SiteId | BookingStatus |
      # | PO2010002039 | Hanging  |   5649 | Complete      |
      #| PO2019902039 | Boxed    |   5885 | In Progress   |
      #| PO2019802039 | Boxed    |   5885 | In Progress   |
      | PO2011102039 | Boxed    |   5885 | In Progress   |

  @change_status_of_booking_asn_non_rms_returns @pre_receiving @in_review
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | UPIId               | ASNId   | DataType | SiteId | BookingStatus |
      # | 3000000000000000019 | 1220074 | Hanging  |   5885 |Complete      |
      | 3000000000000980019 | 1229874 | Boxed    |   5885 | In Progress   |

  #| 3000000000000990019 | 1229974 | Boxed    |   5885 | In Progress   |
  @change_status_of_booking_asn_rms_returns @pre_receiving @in_review
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId | BookingStatus |
      #   | 58850008389770077010083897700300 | 0000838977 | Hanging  |   5885 |Complete      |
      #| 58850008999770077010089997700300 | 0000899977 | Boxed    |   5885 | In Progress   |
      | 58850008989770077010089897700300 | 0000898977 | Boxed    |   5885 | In Progress   |
