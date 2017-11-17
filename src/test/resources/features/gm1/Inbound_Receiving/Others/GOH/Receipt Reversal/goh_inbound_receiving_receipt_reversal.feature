@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

      
  @goh @receipt_reversal @inbound_receiving @goh_inbound_receiving_receipt_reversal_perform_a_receipt_correction_stock_adjustment_function_after_putaway_of_receipt @complete @check17
  Scenario: Perform a receipt correction (stock adjustment function) after putaway of receipt
    Given the PO of type "GOH" with UPI and ASN should be in "Released" status
    When I receive all "GOH" skus for the purchase order at location "REC002"
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction
    When I do stock adjustments after putaway for receipt reversal with siteId and PO
    And I choose the reason code as "INCOMPLETE"
    #When I navigate to inventory transaction query
    #And I choose the code as "Adjustment" and search the sku id with IE reason code
    Then the reason code should be updated

    #Examples: 
      #| PreAdviceID  | PalletId             | ASN        | Location | SiteId | ReasonCode |
      #|  1010017435 | 00050426003981847122 | 0000032219 | REC002   |   5649 | IE         |
