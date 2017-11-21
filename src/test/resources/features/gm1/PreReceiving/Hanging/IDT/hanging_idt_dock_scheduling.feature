@dock_scheduler_RMS
Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for Returns RMS type
  So that I can receive the same in the scheduled dock door

  @pre_receiving @jenkinsB @idt @hanging @hanging_pre_receiving_idt_validate_whether_asn_can_be_assigned_using_the_container_id @complete @ds @jenkinsidt
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

 @jenkinsB @pre_receiving @idt @hanging @hanging_pre_receiving_idt_validate_whether_booking_details_can_be_captured_carrier_information @complete @ds @jenkinsidt
  Scenario: Validate whether Booking details can be captured - Carrier Information
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

   @jenkinsB @pre_receiving @idt @hanging @hanging_pre_receiving_idt_validate_whether_booking_details_can_be_captured_service_level_information @complete @ds @jenkinsidt
  Scenario: Validate whether Booking details can be captured - Service level information
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

  @jenkinsB @pre_receiving @idt @hanging @hanging_pre_receiving_idt_validate_whether_booking_details_can_be_captured_trailer_type_information @complete @ds @jenkinsidt
  Scenario: Validate whether Booking details can be captured - Trailer Type information
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

  @jenkinsB @pre_receiving @idt @hanging @hanging_pre_receiving_idt_validate_whether_the_booking_can_be_deleted @complete @ds @jenkinsidt
  Scenario: Validate whether the Booking can be deleted
    Given I have done the dock scheduler booking with the UPI, ASN of type "Hanging" at site for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

   @jenkinsB @pre_receiving @idt @hanging @hanging_pre_receiving_idt_validate_whether_booking_can_be_moved_to_different_time_on_the_same_day @complete @ds @jenkinsidt
  Scenario: Validate whether booking can be moved to different time on the same day
    Given I have done the dock scheduler booking with the UPI, ASN of type "Hanging" at site for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

   @jenkinsB @pre_receiving @idt @hanging @hanging_pre_receiving_idt_validate_whether_booking_can_be_made_to_complete_status @complete @ds @jenkinsidt
  Scenario: Validate whether booking can be made to Complete status
    Given I have done the dock scheduler booking with the UPI, ASN of type "Hanging" at site for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

   @jenkinsB @pre_receiving @idt @hanging @hanging_pre_receiving_idt_validate_whether_booking_status_can_be_updated_to_capture_the_arrival_time_scheduled_to_in_progress @jenkinsidt @complete @ds
  Scenario: Validate whether Booking status can be updated to capture the arrival time (Scheduled to In progress)
    Given I have done the dock scheduler booking with the UPI, ASN of type "Hanging" at site for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "<BookingStatus>"
    Then the booking id details with updated status "<BookingStatus>" should be displayed on the page

  @pre_receiving @idt @hanging @hanging_pre_receiving_idt_validate_whether_booking_can_be_moved_to_different_date @complete @ds @jenkinsidt
  Scenario: Validate whether Booking can be moved to different date
    Given I have done the dock scheduler booking with the UPI, ASN of type "Hanging" at site for IDT
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page
