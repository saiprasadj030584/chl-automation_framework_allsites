@dock_scheduler_RMS
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for Returns RMS type
  So that I can receive the same in the scheduled dock door

  @pre_receiving @idt @hanging @hanging_pre_receiving_idt_dock_scehduling @review
  Scenario: Validate whether ASN can be assigned using the Container ID
    Given the UPI and ASN of type "Hanging" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @pre_receiving @idt @hanging @hanging_pre_receiving_idt_dock_scehduling_carrier_info @review
  Scenario Outline: Validate whether Booking details can be captured - Carrier Information
    Given the UPI "<UPIId>" and ASN "<ASNId>" of type "<DataType>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | UPIId                            | ASNId  | DataType | SiteId |
      | 56490002468740299900395765000210 | 242538 | Hanging  |   5649 |

  @pre_receiving @idt @hanging @hanging_pre_receiving_idt_dock_scehduling_service_level_info @complete
  Scenario Outline: Validate whether Booking details can be captured - Service level information
    Given the UPI "<UPIId>" and ASN "<ASNId>" of type "<DataType>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | UPIId                            | ASNId  | DataType | SiteId |
      | 56490002468740299900395765000210 | 242538 | Hanging  |   5649 |

  @pre_receiving @idt @hanging @hanging_pre_receiving_idt_dock_scehduling_trailer_type_info @complete
  Scenario Outline: Validate whether Booking details can be captured - Trailer Type information
    Given the UPI "<UPIId>" and ASN "<ASNId>" of type "<DataType>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | UPIId                            | ASNId  | DataType | SiteId |
      | 56490002468740299900395765000210 | 242538 | Hanging  |   5649 |

  @pre_receiving @idt @hanging @hanging_pre_receiving_idt_delete_booking @complete
  Scenario Outline: Validate whether the Booking can be deleted
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

    Examples: 
      | UPIId                            | ASNId  | DataType | SiteId |
      | 56490002468740299900395745000210 | 242536 | Hanging  |   5649 |

  @pre_receiving @idt @hanging @hanging_pre_receiving_idt_move_booking_diff_time_sameday @complete
  Scenario Outline: Validate whether booking can be moved to different time on the same day
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | UPIId                            | ASNId  | DataType | SiteId |
      | 56490002468740299900395735000210 | 242535 | Hanging  |   5649 |

  @pre_receiving @idt @hanging @hanging_pre_receiving_idt_change_status_of_booking_to_complete @complete
  Scenario Outline: Validate whether booking can be made to Complete status
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | UPIId                            | ASNId  | DataType | SiteId | BookingStatus |
      | 56490002468740299900395755000210 | 242537 | Hanging  |   5649 | Complete      |

  @pre_receiving @idt @hanging @hanging_pre_receiving_idt_change_status_of_booking_to_inprogress @complete
  Scenario Outline: Validate whether Booking status can be updated to capture the arrival time (Scheduled to In progress)
    Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId | BookingStatus |
      | 58850008389770077010083897700300 | 0000838977 | Hanging  |   5885 | InProgress    |
      
       @pre_receiving @idt @hanging @hanging_pre_receiving_idt_move_booking_diff_date @review
  Scenario Outline: Validate whether Booking can be moved to different date
     Given I have done the dock scheduler booking with the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" at site "<SiteId>" for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page

    Examples: 
      | UPIId               | ASNId   | DataType | SiteId |
      | 56490002468740299900395775000312 | 242542 | Hanging    |   5649 |
      

 