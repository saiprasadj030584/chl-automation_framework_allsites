@receipt_reversal
Feature: Purchase order receipt reversal
  As a warehouse user
  I want to receive the articles
  So that I can reverse the received purchase order

  @boxed_receiving_direct_po_receipt_reversal_boxed_without_lockcode @boxed @direct_po @receiving @complete @ds
  Scenario: Receipt reversal process in JDA WMS for Boxed type without lock code
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001"
    When I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag

  @boxed_receiving_direct_po_receipt_reversal_boxed_with_lockcode @boxed @direct_po @receiving @complete @ds
  Scenario: Receipt reversal process in JDA WMS for Boxed type with lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Complete" status and locked with code "QAFTS"
    And the PO should be received at location "REC001"
    When I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag with lockcode
