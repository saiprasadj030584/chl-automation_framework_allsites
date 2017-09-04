@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for Direct PO
  So that I can receive the same in the scheduled dock door

  @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_dock_schedule_asn @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID
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

  @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_dock_schedule_carrier_information @complete
  Scenario Outline: Validate whether Booking details can be captured - Carrier Information
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

  @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_dock_schedule_service_level_info @complete
  Scenario Outline: Validate whether Booking details can be captured - Service level information
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

  @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_dock_schedule_trailer_type_info @complete
  Scenario Outline: Validate whether Booking details can be captured - Trailer Type information
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

  @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_delete_booking_asn @complete
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
      | PO2010002018 | PO000504560005112362 | PO00100521 | Hanging  |   5649 |

  @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_move_booking_diff_time_sameday @complete
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

  @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_change_status_of_booking_to_complete @complete
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

  @boxed @pre_receiving @direct_po @boxed_pre_receiving_direct_po_validate_complinace_flag_uploaded @complete
  Scenario Outline: Validate whether compliance flag can be uploaded for Pre advice line
    Given the PO "<PreAdviceID>" should be in "Released" status
    And the PO line should have sku, quantity due details
    When I update the compliance flag in database
    Then the compliance details should be updated

    Examples: 
      | PreAdviceID  |
      | PO2138927931 |
      
      @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_assign_dock_door_for_trailer @complete
  Scenario Outline: Assign dock door for each trailer to unload it
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
