@purchase_order
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the sku from full service vendor supplier
  So that I can putaway the full service vendor purchase order

  @boxed_inbound_receiving_fsv_po_over_receive_with_lock_code @boxed @inbound_receiving @fsv_po @complete
  Scenario Outline: Validate Over receiving with lock code
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO with "<Lockcode>" should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    And I lock the product with lock code "<LockCode>"
    When I perform "Over Receiving" for all skus for the FSV purchase order at location "<Location>"
    Then the error message should be displayed as cannot over receipt failed

    Examples: 
      | PreAdviceID | SiteID | Location | LockCode |
      | 25300130368 |   5649 | REC001   | QAFTS    |

  @boxed_inbound_receiving_fsv_po_under_receive_with_lock_code @boxed @inbound_receiving @fsv_po @complete
  Scenario Outline: Validate Under receiving with lock code
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO with "<Lockcode>" should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    And I lock the product with lock code "<LockCode>"
    When I perform "Under Receiving" for all skus for the FSV purchase order at location "<Location>"
    Then the FSV po status should be displayed as "In Progress"

    Examples: 
      | PreAdviceID | SiteID | Location | LockCode |
      | 25300140368 |   5649 | REC001   | QAFTS    |
