@receipt_correction
Feature: Purchase order Putaway Receipt correction
  As a warehouse user
  I want to putaway the received articles and do the receipt correction for the putaway stock
  So that I can correct the inventory stock which updated through putaway

  @hanging @receipt_reversal @inbound_receiving @hanging_inbound_receiving_receipt_reversal_perform_a_receipt_correction_stock_adjustment_function_after_putaway_of_receipt @complete @check17
  Scenario: Perform a receipt correction (stock adjustment function) after putaway of receipt
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status
    When I receive all "Hanging" skus for the purchase order at location "REC001"
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction
    When I do stock adjustments after putaway for receipt reversal with siteId and PO
    And I choose the reason code as "INCOMPLETE"
    Then the reason code should be updated
