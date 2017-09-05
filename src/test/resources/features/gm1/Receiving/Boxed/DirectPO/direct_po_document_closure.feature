@document_closure
Feature: Document Closure of ASN
  As a warehouse user
  I want to perform document closure of ASN in Delivery management

  @boxed_receiving_direct_po_manual_document_closure @complete @boxed @direct_po @receiving @ds
  Scenario: Validate manual document closure ASN
    Given I have an ASN Id with delivery status as "In Progress"
    When I navigate to delivery management page
    And I search ASN ID to update status
    And I choose the delivery status as "Complete"
    When I navigate to delivery page
    And I enter an ASN ID
    Then the delivery status should be "Complete"
