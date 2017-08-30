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
      #|   2010002111 | 00050456000511235601 | 0000100508 | REC001 |
      #| PO2010002001 | PO050456000511235610 | PO00100500 | REC001   |
      #| PO2010002002 | PO050456000511235611 | PO00100501 | REC001   |
      #| PO2010003001 | PO050456000511235710 | PO00100600 | REC001   |
      | PO2010002004 | PO050456000511235613 | PO00100503 | REC001   |

@po_receive_multiple_urn_single_trailer
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Hanging" with multiple UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the purchase order with multiple upi at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | PalletId                                  | ASN        | Location |
      |  9090002070 | 00050456000249606127,00050456000248606127 | 0000004789 | REC001   |

<<<<<<< HEAD:src/test/resources/features/gm/po/po_receiving.feature
  @multiple_po_receive_multiple_urn_single_trailer
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the multiple PO "<PreAdviceID>" of type "Hanging" with multiple UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the multiple PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the multiple purchase order with multiple upi at location "<Location>"
    And the goods receipt should be generated for the multiple stock received in inventory transaction
    Then the po status should be displayed as "Complete" for all the po

    Examples: 
      | PreAdviceID           | PalletId                                  | ASN        | Location |
      | 5050008870,5050008890 | 00050456000259616198,00050456000259616188 | 0000002099 | REC001   |
=======
      
       @po_validate_full_pallet
  Scenario Outline: Validate pallet build for full pallet - Boxed type
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>" with full pallet "<FullPallet>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location | FullPallet |
      | PO2010002227 | PO050456000511235649| PO00100576| REC001   |  N         |
      | PO2010002003 | PO000504560005112357| PO00001005| REC001   |  Y      |
      
      
    
>>>>>>> 2a2ded89c086c96c6d08a74c8623187f9ad88044:src/test/resources/features/gm/Receiving/Boxed/DirectPO/po_receiving.feature
