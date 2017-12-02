@hanging_manual_document_closure
Feature: Hanging - Manual Document Closure of ASN
  As a warehouse user
  I want to perform document closure of ASN in Delivery management

  @jenkinsD @hanging @receiving @direct_po @unique_hanging_receiving_direct_po_validate_manual_document_closure_asn @complete @ds @no_ds @jenkinssc
  Scenario: Validate manual document closure ASN
    Given I have an ASN Id of type "Hanging" with delivery status as "In Progress"
    When I navigate to delivery management page
    And I search ASN ID to update status
    And I choose the delivery status as "Complete"
    When I navigate to delivery page
    And I enter an ASN ID
    Then the delivery status should be "Complete"
