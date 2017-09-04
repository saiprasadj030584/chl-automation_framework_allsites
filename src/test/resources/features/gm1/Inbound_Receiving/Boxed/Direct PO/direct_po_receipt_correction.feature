@receipt_correction
Feature: Purchase order Putaway Receipt correction
  As a warehouse user
  I want to putaway the received articles and do the receipt correction for the putaway stock
  So that I can correct the inventory stock which updated through putaway

  @boxed @direct_po @inbound_receiving @receipt_correction @boxed_direct_po_inbound_receiving_receipt_correction @complete
  Scenario Outline: Putaway process in JDA WMS for Boxed type
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at location "<Location>"
    When I receive all skus for the purchase order at location "<Location>"
    When I do normal putaway for all tags received into putaway location
    Then the inventory should be displayed for all putaway tags received
    When I do stock adjustments after putaway for receipt reversal with siteId "<SiteId>" and PO "<PreAdviceID>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id with IE reason code
    Then the reason code should be updated

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location | SiteId | ReasonCode |
      | PO2627891650 | PO050440235738075610 | PO01618640 | REC002   |   5649 | IE         |
