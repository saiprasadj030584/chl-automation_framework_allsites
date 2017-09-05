@dock_scheduler_NON_RMS
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for Returns Non RMS type
  So that I can receive the same in the scheduled dock door

  @pre_receiving @returns_(non_rms) @boxed @boxed_pre_receiving_returns_(non_rms)_dock_scheduling @complete @ds
  Scenario: Validate whether ASN can be assigned using the Container ID
    Given the UPI , ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @pre_receiving @returns_(non_rms) @boxed @boxed_pre_receiving_returns_(non_rms)_dock_scheduling_carrier_info @complete @ds
  Scenario: Validate whether Booking details can be captured - Carrier Information
    Given the UPI , ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @pre_receiving @returns_(non_rms) @boxed @boxed_pre_receiving_returns_(non_rms)_dock_scheduling_service_level_info @complete @ds
  Scenario: Validate whether Booking details can be captured - Service level information
    Given the UPI , ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @pre_receiving @returns_(non_rms) @boxed @boxed_pre_receiving_returns_(non_rms)_dock_scheduling_trailer_type_info @complete @ds
  Scenario: Validate whether Booking details can be captured - Trailer Type information
    Given the UPI , ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @pre_receiving @returns_(non_rms) @boxed @boxed_pre_receiving_returns_(non_rms)_delete_booking @complete @ds
  Scenario: Validate whether the Booking can be deleted
    Given I have done the dock scheduler booking with the UPI, ASN of type "Boxed" at site for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

  @pre_receiving @returns_(non_rms) @boxed @boxed_pre_receiving_returns_(non_rms)_move_booking_diff_time_sameday @complete @ds
  Scenario: Validate whether booking can be moved to different time on the same day
    Given I have done the dock scheduler booking with the UPI, ASN of type "Boxed" at site for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

  @pre_receiving @returns_(non_rms) @boxed @boxed_pre_receiving_returns_(non_rms)_change_status_of_booking_to_complete @complete @ds
  Scenario: Validate whether booking can be made to Complete status
    Given I have done the dock scheduler booking with the UPI, ASN of type "Boxed" at site for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "Complete"
    Then the booking id details with updated status "Complete" should be displayed on the page
    
    @pre_receiving @returns_(non_rms) @boxed @boxed_pre_receiving_returns_(non_rms)_change_status_of_booking_to_in_progress @complete @ds
  Scenario: Validate whether Booking status can be updated to capture the arrival time (Scheduled to In progress)
    Given I have done the dock scheduler booking with the UPI, ASN of type "Boxed" at site for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "In Progress"
    Then the booking id details with updated status "In Progress" should be displayed on the page

  @pre_receiving @returns_(non_rms) @boxed @boxed_pre_receiving_returns_(non_rms)_move_booking_different_date @complete @ds
  Scenario Outline: Validate whether Booking can be moved to different date
    Given I have done the dock scheduler booking with the UPI, ASN of type "Boxed" at site for NON RMS
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page
