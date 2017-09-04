@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @boxed_receiving_direct_po_receiving_without_lock_code @receiving @direct_po @boxed @complete
  Scenario Outline: Validate receipting process without lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002004 | PO050456000511235613 | PO00100503 | REC001   |

  @boxed_receiving_direct_po_receiving_automatic_document_closure @receiving @direct_po @boxed @complete
  Scenario Outline: Validate automatic document closure ASN
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002004 | PO050456000511235613 | PO00100503 | REC001   |

  @boxed_direct_po_receiving_preadvice_qty_greater_than_upi_qty @po @complete @boxed @receiving @direct_po
  Scenario Outline: Receiving when Pre advice line quantity is greater than the UPI line quantity
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "greater" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "In Progress" while upi and asn status should "Complete"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002004 | PO050456000511235613 | PO00100503 | REC001   |

  @boxed_receiving_direct_po_preadvice_qty_less_than_upi_qty @boxed @receiving @direct_po @complete
  Scenario Outline: Receiving when Pre advice line quantity is less than the UPI line quantity
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "less" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2090002003 | PO000504560005192694 | PO00107513 | REC001   |

  @boxed @direct_po @receiving @complete @boxed_direct_po_receiving_articles_with_different_putaway_group
  Scenario Outline: Receiving process in JDA WMS for Boxed type having different putaway group
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus having different putaway group for the purchase order at location "<Location>"
    Then the inventory should be displayed for all received tags for two putaway group
    And the goods receipt should be generated for received stock in inventory transaction for two putaway group
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | PalletId         | ASN          | Location |
      | PO148991278 | PO05102456112891 | PO0160174853 | REC001   |
