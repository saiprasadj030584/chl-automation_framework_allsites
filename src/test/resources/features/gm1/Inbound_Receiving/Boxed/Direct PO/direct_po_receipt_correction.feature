@receipt_correction
Feature: Purchase order Putaway Receipt correction
  As a warehouse user
  I want to putaway the received articles and do the receipt correction for the putaway stock
  So that I can correct the inventory stock which updated through putaway

  @jenkins_analysis @boxed @direct_po @inbound_receiving @receipt_correction @boxed_direct_po_inbound_receiving_receipt_correction @complete @ds
  Scenario: Perform a receipt correction (stock adjustment function) after putaway of receipt
    Given the PO of type "Boxed" with UPI and ASN should be received at location "REC002"
    When I receive all skus for the purchase order at location "REC002"
   
    #When I choose existing relocate
    #And I proceed with entering the upc and location
    #When I perform normal putaway after relocation
    #Then the inventory should be displayed for all putaway tags received
    #When I have logged in as warehouse user in JDA dispatcher GM application
   And I do stock adjustments after putaway for receipt reversal with siteId and PO
    And I choose the reason code as "IE"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id with IE reason code
    Then the reason code should be updated
    
    
     