@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for Direct PO
  So that I can receive the same in the scheduled dock door

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_dock_schedule_asn @review
  Scenario: Validate whether ASN can be assigned using the Container ID
    Given the PO, UPI, ASN of type "Hanging" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_dock_schedule_carrier_information @review
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
      | PreAdviceID | UPIId                | ASNId      | Type    | SiteId |
      |  1010002077 | 00050453000278617222 | 0000001269 | Hanging |   5649 |

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_dock_schedule_service_level_info @review
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
      | PreAdviceID | UPIId                | ASNId      | Type    | SiteId |
      |  1010002077 | 00050453000278617222 | 0000001269 | Hanging |   5649 |

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_dock_schedule_trailer_type_info @review
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
      | PreAdviceID | UPIId                | ASNId      | Type    | SiteId |
      |  1010002077 | 00050453000278617222 | 0000001269 | Hanging |   5649 |

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_assign_dock_door_to_unload @review
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
      | PreAdviceID | UPIId                | ASNId      | Type    | SiteId |
      |  1010002077 | 00050453000278617222 | 0000001269 | Hanging |   5649 |

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_delete_booking_asn_direct_po @review
  Scenario Outline: Validate whether the Booking can be deleted
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | PreAdviceID | UPIId                | ASNId      | DataType | SiteId |
      |  1010002179 | 00050453000278617213 | 0000001271 | Hanging  |   5649 |

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_move_booking_diff_time_sameday_asn_direct_po @review
  Scenario Outline: Validate whether booking can be moved to different time on the same day
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | PreAdviceID | UPIId                | ASNId      | DataType | SiteId |
      |  1010002180 | 00050453000278617214 | 0000001272 | Hanging  |   5649 |

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_change_status_of_booking_to_complete_asn_direct_po @review
  Scenario Outline: Validate whether booking can be made to Complete status
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | PreAdviceID | UPIId                | ASNId      | DataType | SiteId | BookingStatus |
      |  1010002181 | 00050453000278617215 | 0000001273 | Hanging  |   5649 | Complete      |

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_change_status_of_booking_to_inprogress_asn_direct_po @review
  Scenario Outline: Validate whether booking can be made to Complete status
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | PreAdviceID | UPIId                | ASNId      | DataType | SiteId | BookingStatus |
      |  1010002037 | 00050453000278617123 | 0000001170 | Hanging  |   5649 | InProgress    |

  @pre_receiving @direct_po @hanging @hanging_pre_receiving_direct_po_move_booking_diff_date_Direct_PO @review
  Scenario Outline: Validate whether Booking can be moved to different date
    Given I have done the dock scheduler booking with the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>"
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | PreAdviceID | UPIId                | ASNId      | DataType | SiteId |
      |  1010002182 | 00050453000278617216 | 0000001274 | Hanging  |   5649 |
