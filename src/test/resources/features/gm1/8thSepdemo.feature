@inbound
Feature: Dock Scheduling, Receiving , Putaway
  As a warehouse user
  I want to receive and putaway the articles
  So that I can complete the purchase order

  @boxed @putaway @fsv_po @boxed_putaway_fsv_po_validate_putaway_process @complete @8thSep_demo1
  Scenario: Validate Putaway Process
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due and qa details
    And the PO should not be linked with UPI line
    And I update the advice id for all line items
    When I receive all skus for the FSV sampling purchase order at location "REC002"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
    When I choose existing relocate
    And I proceed with entering the upc and location of FSV PO
    When I perform normal putaway
    Then the goods receipt should be generated for putaway FSV stock in inventory transaction

  @pre_receiving @direct_po @boxed @boxed_pre_receiving_direct_po_dock_schedule_asn @complete @8thSep_demo
  Scenario: Validate whether ASN can be assigned using the Container ID
    Given the PO, UPI, ASN of type "Boxed" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type and ASN
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear in the dock scheduler booking

  @allocation @retail @boxed @boxed_allocation_retail_allocation_rules @complete
  Scenario Outline: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      #|  8964210898 |
      |  8964210858 |
