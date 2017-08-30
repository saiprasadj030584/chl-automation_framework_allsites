@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  

  @boxed @putaway @fsv_po @boxed_putaway_fsv_po_putaway_validate_mezz_shelving_putaway @complete
  Scenario Outline: Validate Mezz/Shelving putaway
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have MEZZ sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>" for MEZZ putaway
    When I choose existing relocate
    And I proceed with entering the upc and location of FSV PO
    When I perform normal putaway after relocation for FSV PO
    Then the goods receipt should be generated for putaway FSV stock in inventory transaction

    Examples: 
      | PreAdviceID | Location | SiteID |
      | 25300121579 | REC002   |   5649 |


  @boxed @putaway @fsv_po @boxed_putaway_fsv_po_sampling_qa_build @complete
  Scenario Outline: Validate Sampling/QA Pallet build
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due and qa details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV sampling purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
    When I choose existing relocate
    And I proceed with entering the upc and location of FSV PO
    When I perform normal putaway 
    Then the goods receipt should be generated for putaway FSV stock in inventory transaction

    Examples: 
      | PreAdviceID | Location | SiteID |
      | 45300100127 | REC002   |   5649 |
      
      