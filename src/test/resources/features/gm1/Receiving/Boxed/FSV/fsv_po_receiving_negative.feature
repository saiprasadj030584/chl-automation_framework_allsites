@po_receiving_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles that are damaged

  @boxed_receiving_fsv_po_validate_damage_receipt_from_supplier @boxed @fsv_po @receiving @complete
  Scenario Outline: Validate damaged on receipt (From supplier) for FSV PO - Boxed
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO with "<LockCode>" should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged

    Examples: 
      | PreAdviceID | SiteID | Location | LockCode |
      | 25300720368 |   5649 | REC001   | DMGD     |
