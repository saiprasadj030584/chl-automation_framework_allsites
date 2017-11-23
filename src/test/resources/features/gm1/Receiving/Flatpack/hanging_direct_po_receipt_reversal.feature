@receipt_reversal
Feature: Receipt reversal
  As a warehouse user
  I want to receive the articles
  So that I can return the received articles

  @hanging_receiving_direct_po_validate_receipt_reversal_process_without_lock_code @hanging @direct_po @receiving @complete
  Scenario: Receipt reversal process in JDA WMS for Boxed type without lock code
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC001"
    When I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag

  @hanging_receiving_direct_po_validate_receipt_reversal_process_with_qafts_lock_code @hanging @direct_po @receiving @complete
  Scenario: Receipt reversal process in JDA WMS for Boxed type with lock code
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status and locked with code "QAFTS"
    And the PO of type "Hanging" should be received at location "REC001"
    When I navigate to receipt reversal page
    And I do receipt reversal for the random tag received
    #Then the inventory transaction should be updated with reversed receipt tag for random tags received
    #And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag with lockcode 
