@dock_scheduler
Feature: Dock Scheduling
  As a warehouse user
  I want to pre receive the stock

  @boxed @pre_receiving @direct_po @boxed_pre_receiving_direct_po_validate_complinace_flag_uploaded @complete
  Scenario: Validate whether compliance flag can be uploaded for Pre advice line
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I update the compliance flag in database
    Then the compliance details should be updated

   
     
      
   