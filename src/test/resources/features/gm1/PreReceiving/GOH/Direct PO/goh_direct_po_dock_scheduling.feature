@goh_direct_po_dock_scheduler
Feature: GOH - Direct PO - Dock Scheduling
  As a warehouse user
  I want to schedult a dock door for Direct PO
  So that I can receive the same in the scheduled dock door

 @jenkinsdb @jenkins_analysis @goh @pre_receiving @direct_po @goh_pre_receiving_direct_po_validate_whether_compliance_flag_can_be_uploaded_for_pre_advice_line @complete @ds
 Scenario: Validate whether compliance flag can be uploaded for Pre advice line
    Given the PO of type "GOH" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I update the compliance flag in database
    Then the compliance details should be updated

