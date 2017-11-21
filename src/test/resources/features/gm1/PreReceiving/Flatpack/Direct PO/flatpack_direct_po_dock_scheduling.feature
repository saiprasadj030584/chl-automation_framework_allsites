@flatpack_direct_po_dock_scheduler
Feature: Flatpack - Direct PO - Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for Direct PO
  So that I can receive the same in the scheduled dock door

 @jenkinsC @jenkins_analysis @flatpack @pre_receiving @direct_po @flatpack_pre_receiving_direct_po_validate_whether_compliance_flag_can_be_uploaded_for_pre_advice_line @complete @ds
 Scenario: Validate whether compliance flag can be uploaded for Pre advice line
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I update the compliance flag in database
    Then the compliance details should be updated
    
    
   @jenkinsC @pre_receiving @direct_po @flatpack @flatpack_pre_receiving_direct_po_assign_dock_door_for_each_trailer_to_unload_it @complete @ds
  Scenario: Assign dock door for each trailer to unload it
    Given the PO, UPI, ASN of type "Flatpack" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking
    

