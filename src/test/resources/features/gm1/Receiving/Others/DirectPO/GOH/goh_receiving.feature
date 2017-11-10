@receiving
Feature: Receiving
  As a warehouse user
  I want to receive the articles

  @goh_receiving_direct_po_validate_urgent_delivery_po @goh @direct_po @receiving @review
  Scenario Outline: Validate the urgent delivery PO
    Given the PO "<PreAdviceID>" of type "GOH" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I navigate to pre-advice line maintenance page
    And I mark it as urgent PO
    Then the PO should be updated for urgent delivery

    Examples: 
      | PreAdviceID | PalletId             | ASN        |
      |  1012004039 | 00050426003981497960 | 0000004024 |

  @goh_receiving_direct_po_validate_damaged_on_receipt_from_supplier @direct_po @goh @receiving @complete @allocation7_check
  Scenario: Receipt reversal process in JDA WMS for Hanging type without lock code - Boxed
    Given the PO of type "GOH" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
     When I receive all "GOH" skus for the purchase order at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"
     When I navigate to inventory update page
    And I select the update type as "Lock Status Change"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "Locked" and lock code as "1Damaged"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged

   
