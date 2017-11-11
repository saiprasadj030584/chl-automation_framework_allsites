@boxed_direct_po_dock_scheduler
Feature: Boxed - Direct PO - Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for Direct PO
  So that I can receive the same in the scheduled dock door

  @jenkins_analysis @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_dock_schedule_asn @complete @ds @maven_check_1 @dock_check @allocation_check
  Scenario: Validate whether ASN can be assigned using the Container ID
    Given the PO, UPI, ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @jenkins_analysis @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_validate_whether_booking_details_can_be_captured_carrier_information @complete @ds @maven_check_1 @allocation_check
  Scenario: Validate whether Booking details can be captured - Carrier Information
    Given the PO, UPI, ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

    @jenkinshpr @jenkins_analysis @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_validate_whether_booking_details_can_be_captured_service_level_information @complete @ds @maven_check_1 @allocation_check
  Scenario: Validate whether Booking details can be captured - Service level information
    Given the PO, UPI, ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @jenkins_analysis @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_validate_whether_booking_details_can_be_captured_trailer_type_information @complete @ds @maven_check_1 @allocation_check
  Scenario: Validate whether Booking details can be captured - Trailer Type information
    Given the PO, UPI, ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @jenkins_analysis @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_validate_whether_the_booking_can_be_deleted @complete @ds @maven_check_1 @allocation_check
  Scenario: Validate whether the Booking can be deleted
    Given I have done the dock scheduler booking with the PO, UPI, ASN of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I delete the booking
    Then the booking details should be deleted in the dock scheduler booking

  @jenkinshpr  @jenkins_analysis @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_validate_whether_booking_can_be_moved_to_different_time_on_the_same_day @complete @ds @maven_check_1 @dock @allocation_check
  Scenario: Validate whether booking can be moved to different time on the same day
    Given I have done the dock scheduler booking with the PO, UPI, ASN of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time
    Then the booking id details with updated time should be displayed on the page

  @jenkins_analysis @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_validate_whether_booking_can_be_made_to_complete_status @complete @ds @maven_check_1 @maven_check_2 @allocation_check
  Scenario: Validate whether booking can be made to Complete status
    Given I have done the dock scheduler booking with the PO, UPI, ASN of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "Complete"
    Then the booking id details with updated status "Complete" should be displayed on the page


  @jenkins_analysis @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_validate_whether_booking_status_can_be_updated_to_capture_the_arrival_time_scheduled_to_in_progress @complete @ds
  Scenario: Validate whether Booking status can be updated to capture the arrival time (Scheduled to In progress)
    Given I have done the dock scheduler booking with the PO, UPI, ASN of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the status of booking to BookingStatus "In Progress"
    Then the booking id details with updated status "In Progress" should be displayed on the page

 @jenkinsA @jenkins_analysis @boxed @pre_receiving @direct_po @boxed_pre_receiving_direct_po_validate_compliance_flag_uploaded @complete @ds @maven_check_1 @allocation_check
 Scenario: Validate whether compliance flag can be uploaded for Pre advice line
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I update the compliance flag in database
    Then the compliance details should be updated

  @jenkinsA @boxed @pre_receiving @direct_po @boxed_pre_receiving_direct_po_validate_whether_compliance_flag_can_be_uploaded_for_pre_advice_header @complete @ds
  Scenario: Validate whether compliance flag can be uploaded for Pre advice header
    Given the PO of type "Boxed" should be in "Released" status with line items,supplier details
    When I update the compliance flag in pre advice header
    Then the compliance details should be updated in preadvice header

  @jenkinshpr @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_assign_dock_door_for_each_trailer_to_unload_it @complete @ds
  Scenario: Assign dock door for each trailer to unload it
    Given the PO, UPI, ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @jenkins_analysis @boxed_pre_receiving_direct_po_validate_whether_booking_can_be_moved_to_different_date @pre_receiving @direct_po @boxed @complete @ds @maven_check_1 @allocation_check
  Scenario: Validate whether Booking can be moved to different date
    Given I have done the dock scheduler booking with the PO, UPI, ASN of type "Boxed" at site
    When I navigate to dock scheduler start page
    When I select view existing bookings
    When I search the booking id
    Then the booking id details should be displayed on the page
    When I change the booking time to different date
    Then the booking id details with updated time should be displayed on the page
