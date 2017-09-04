@direct_po_receiving_lock_code
Feature: Purchase order receiving with Lock code
  As a warehouse user
  I want to receive the locked articles
  But i cannot putaway the purchase order

  @boxed_receiving_direct_po_receiving_with_lock_code_QAFTS @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QAFTS lock code
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002750 | 00050456200454277620 | PO00106810 | QAFTS    | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QACOMP @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QACOMP lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002802 | PO050456000511235802 | PO00100802 | QACOMP   | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QAPC @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QAPC lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002803 | PO050456000511235803 | PO00100813 | QAPC     | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_FWL @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with FWL lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002814 | PO050456000511236814 | PO00100214 | FWL      | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_REWORK @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with REWORK lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002805 | PO050456000511235805 | PO00100805 | REWORK   | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QAFTSFWL @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QAFTSFWL lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002806 | PO050456000511235806 | PO00100806 | QAFTSFWL | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QACNIFWL @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QACNIFWL lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002810 | PO050456000511235810 | PO00100810 | QACNIFWL | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QAPCFWL @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QAPCFWL lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002807 | PO050456000511235807 | PO00100807 | QAPCFWL  | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QAFTSRW @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QAFTSRW lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002808 | PO050456000511235808 | PO00100808 | QAFTSRW  | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QACOMPRW @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QACOMPRW lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002809 | PO050456000511235809 | PO00100809 | QACOMPRW | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QAPCRW @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QAPCRW lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002810 | PO050456000511235810 | PO00100810 | QAPCRW   | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_FWLRW @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with FWLRW lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002710 | PO050456000511235610 | PO00100710 | FWLRW    | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QAFTSFWLRW @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QAFTSFWLRW lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode   | Location |
      | PO2010002711 | PO050456000511235611 | PO00100711 | QAFTSFWLRW | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QACOMFWLRW @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QACOMFWLRW lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode   | Location |
      | PO2010002712 | PO050456000511235613 | PO00100712 | QACOMFWLRW | REC001   |

  @boxed_receiving_direct_po_receiving_with_lock_code_QAPCFWLRW @boxed @receiving @direct_po @complete
  Scenario Outline: Validate receiving process with QAPCFWLRW lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode  | Location |
      | PO2010002713 | PO050456000511235614 | PO00100713 | QAPCFWLRW | REC001   |
