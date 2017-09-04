@dock_scheduler_RMS
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for Returns RMS type
  So that I can receive the same in the scheduled dock door

  @pre_receiving @returns_(rms) @boxed @boxed_pre_receiving_returns_(rms)_dock_scheduling @complete
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - returns RMS 
    Given the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId |
      #| 58850008387770077010083877700300 | 0000838777 | Hanging  |   5885 |
      | 58850008386670077010083866700300 | 0000838667 | Boxed  |   5885 |
      
      @pre_receiving @returns_(rms) @boxed @boxed_pre_receiving_returns_(rms)_dock_scheduling_carrier_info @complete
  Scenario Outline: Validate whether Booking details can be captured - Carrier Information
    Given the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId |
      #| 58850008387770077010083877700300 | 0000838777 | Hanging  |   5885 |
      | 58850008386670077010083866700300 | 0000838667 | Boxed  |   5885 |
      
      @pre_receiving @returns_(rms) @boxed @boxed_pre_receiving_returns_(rms)_dock_scheduling_service_level_info @complete
  Scenario Outline: Validate whether Booking details can be captured - Service level information
    Given the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId |
      #| 58850008387770077010083877700300 | 0000838777 | Hanging  |   5885 |
      | 58850008386670077010083866700300 | 0000838667 | Boxed  |   5885 |
      
      @pre_receiving @returns_(rms) @boxed @boxed_pre_receiving_returns_(rms)_dock_scheduling_trailer_type_info @complete
  Scenario Outline: Validate whether Booking details can be captured - Trailer Type information
    Given the UPI "<UPIId>", ASN "<ASNId>" of type "<DataType>" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    Examples: 
      | UPIId                            | ASNId      | DataType | SiteId |
      #| 58850008387770077010083877700300 | 0000838777 | Hanging  |   5885 |
      | 58850008386670077010083866700300 | 0000838667 | Boxed  |   5885 |

  @pre_receiving @returns_(rms) @boxed @boxed_pre_receiving_returns_(rms)_delete_booking @complete
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

  @pre_receiving @returns_(rms) @boxed @boxed_pre_receiving_returns_(rms)_move_booking_diff_time_sameday @complete
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

  @pre_receiving @returns_(rms) @boxed @boxed_pre_receiving_returns_(rms)_change_status_of_booking_to_complete @complete
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
