@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for FSV PO
  So that I can receive the same in the scheduled dock door

  @pre_receiving @fsv_po @boxed @boxed_pre_receiving_fsv_po_dock_schedule @complete
  Scenario Outline: Validate whether PO can be assigned using the Pre advice ID
    Given the PO "<PreAdviceID>" of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type preadvice
    And I select the slot
    And I create a booking
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | SiteId |
      | PO2010002240 |   5649 |

  @pre_receiving @fsv_po @boxed @boxed_pre_receiving_fsv_po_dock_schedule_carrier_information @complete
  Scenario Outline: Validate whether Booking details can be captured - Carrier Information
    Given the PO "<PreAdviceID>" of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type preadvice
    And I select the slot
    And I create a booking
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | SiteId |
      | PO2010002240 |   5649 |

  @pre_receiving @fsv_po @boxed @boxed_pre_receiving_fsv_po_dock_schedule_service_level_info @complete
  Scenario Outline: Validate whether Booking details can be captured - Service level information
    Given the PO "<PreAdviceID>" of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type preadvice
    And I select the slot
    And I create a booking
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | SiteId |
      | PO2010002240 |   5649 |

  @pre_receiving @fsv_po @boxed @boxed_pre_receiving_fsv_po_dock_schedule_trailer_type_info @complete
  Scenario Outline: Validate whether Booking details can be captured - Trailer Type information
    Given the PO "<PreAdviceID>" of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type preadvice
    And I select the slot
    And I create a booking
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | SiteId |
      | PO2010002240 |   5649 |
      
       @pre_receiving @fsv_po @boxed @boxed_pre_receiving_fsv_po_assign_dock_door_for_trailer @complete
  Scenario Outline: Assign dock door for each trailer to unload it
    Given the PO "<PreAdviceID>" of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type preadvice
    And I select the slot
    And I create a booking
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | PreAdviceID  | SiteId |
      | PO2010002240 |   5649 |

  @pre_receiving @fsv_po @boxed @boxed_pre_receiving_fsv_po_move_booking_diff_time_sameday @complete
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

  @pre_receiving @fsv_po @boxed_pre_receiving_fsv_po_delete_booking @complete
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

  @pre_receiving @fsv_po @boxed_pre_receiving_fsv_po_change_status_of_booking_to_complete @complete
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

  @boxed @pre_receiving @fsv_po @boxed_pre_receiving_fsv_po_validate_complinace_flag_uploaded @complete
  Scenario Outline: Validate whether compliance flag can be uploaded for Pre advice line
    Given the PO "<PreAdviceID>" should be in "Released" status
    And the PO line should have sku, quantity due details
    When I update the compliance flag in database
    Then the compliance details should be updated

    Examples: 
      | PreAdviceID |
      | 25300121679 |
