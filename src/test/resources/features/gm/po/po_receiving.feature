@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @po_receive_hanging @receiving @direct_po @boxed @complete @1
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      #| PO2010002006 | PO050456000511235615 | PO00100505 | REC001   |
        | PO2010002613 | PO050456000511235713 | PO00100733 | REC001   |

  @po_receive_boxed @po @complete @2
  Scenario Outline: Receiving process in JDA WMS for Boxed type
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

  @rcv_boxed_po_qty_greater_than_upi_qty @po @complete @boxed
  Scenario Outline: Receiving when Pre advice line quantity is greater than the UPI line quantity
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity greater than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "In Progress" while upi and asn status should "Complete"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002813 | PO050456000511235813 | PO00100833 | REC001   |
       | PO2010002613 | PO050456000511235713 | PO00100733 | REC001   |
       #| PO2010002614 | PO050456000511235714 | PO00100734 | REC001   |
      
