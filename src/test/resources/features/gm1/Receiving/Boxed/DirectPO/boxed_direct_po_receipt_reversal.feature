@boxed_direct_po_receipt_reversal
Feature: Boxed - Direct PO - Receipt reversal
  As a warehouse user
  I want to receive the articles
  So that I can reverse the received purchase order

  @jenkinsA @jenkinsfly @unique_boxed_receiving_direct_po_validate_receipt_reversal_process_without_lock_code @boxed @direct_po @receiving @complete @ds @maven_check_1 @putty_check_1 @done_check @check9
  Scenario: Receipt reversal process in JDA WMS for Boxed type without lock code
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001"
    When I have logged in as warehouse user in JDA dispatcher GM application
    And I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag

  @jenkinsA @unique_boxed_receiving_direct_po_validate_receipt_reversal_process_with_qafts_lock_code @boxed @direct_po @receiving @complete @ds @maven_check_1 @putty_check_1 @check9
  Scenario: Receipt reversal process in JDA WMS for Boxed type with lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status and locked with code "QAFTS"
    And the PO should be received at location "REC001"
    When I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag with lockcode
