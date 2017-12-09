@boxed_fsv_po_dock_scheduling
Feature: Boxed - FSV PO - Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for FSV PO
  So that I can receive the same in the scheduled dock door

  @jenkinsA @jenkinsrun @pre_receiving @fsv_po @boxed @unique_boxed_pre_receiving_fsv_po_dock_schedule @complete @ds @jenkinsfsv
  Scenario: Validate whether PO can be assigned using the Pre advice ID
    Given the PO of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type preadvice
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

   @jenkinsfsv @jenkinsA @pre_receiving @fsv_po @boxed @unique_boxed_pre_receiving_fsv_po_validate_whether_booking_details_can_be_captured_carrier_information @complete @ds 
  Scenario: Validate whether Booking details can be captured - Carrier Information
    Given the PO of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type preadvice
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @jenkinsA  @pre_receiving @fsv_po @boxed @unique_boxed_pre_receiving_fsv_po_validate_whether_booking_details_can_be_captured_service_level_information @complete @ds 
  Scenario: Validate whether Booking details can be captured - Service level information
    Given the PO of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type preadvice
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @jenkinsA @pre_receiving @fsv_po @boxed @unique_boxed_pre_receiving_fsv_po_validate_whether_booking_details_can_be_captured_trailer_type_information @complete @ds  
  Scenario: Validate whether Booking details can be captured - Trailer Type information
    Given the PO of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type preadvice
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @jenkinsA @pre_receiving @fsv_po @boxed @unique_boxed_pre_receiving_fsv_po_assign_dock_door_for_each_trailer_to_unload_it @complete @ds  
  Scenario: Assign dock door for each trailer to unload it
    Given the PO of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type preadvice
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @jenkinsD @pre_receiving @fsv_po @boxed @unique_boxed_pre_receiving_fsv_po_validate_whether_booking_can_be_moved_to_different_time_on_the_same_day @complete @ds 
  Scenario: Validate whether booking can be moved to different time on the same day
    Given I have done the dock scheduler booking with the PO of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

  @jenkinsA @pre_receiving @fsv_po @unique_boxed_pre_receiving_fsv_po_validate_whether_the_booking_can_be_deleted @complete @ds 
  Scenario: Validate whether the Booking can be deleted
    Given I have done the dock scheduler booking with the PO of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

  @jenkinsA  @pre_receiving @fsv_po @unique_boxed_pre_receiving_fsv_po_validate_whether_booking_can_be_made_to_complete_status @complete @ds 
  Scenario: Validate whether booking can be made to Complete status
    Given I have done the dock scheduler booking with the PO of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "Complete"
    Then the booking id details with updated status "Complete" should be displayed on the page

  @jenkinsA  @jenkinsrun @pre_receiving @fsv_po @unique_boxed_pre_receiving_fsv_po_validate_whether_booking_status_can_be_updated_to_capture_the_arrival_time_scheduled_to_in_progress @complete @ds  
  Scenario: Validate whether Booking status can be updated to capture the arrival time (Scheduled to In progress)
    Given I have done the dock scheduler booking with the PO of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "In Progress"
    Then the booking id details with updated status "In Progress" should be displayed on the page

  @jenkinsA  @jenkins_analysis @boxed @pre_receiving @fsv_po @unique_boxed_pre_receiving_fsv_po_validate_whether_compliance_flag_can_be_uploaded_for_pre_advice_line @complete @ds 
  Scenario: Validate whether compliance flag can be uploaded for Pre advice line
    Given the PO should be in "Released" status
    And the PO line should have sku, quantity due details
    When I update the compliance flag in database
    Then the compliance details should be updated

  @jenkinsA @unique_boxed_pre_receiving_fsv_po_validate_whether_booking_can_be_moved_to_different_date @boxed @pre_receiving @fsv_po @complete @ds 
  Scenario: Validate whether Booking can be moved to different date
    Given I have done the dock scheduler booking with the PO of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page
