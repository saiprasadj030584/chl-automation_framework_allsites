@goh_manual_document_closure
Feature: GOH - Manual Document Closure of ASN
  As a warehouse user
  I want to perform document closure of ASN in Delivery management

  @jenkins_analysis @goh_receiving_direct_po_validate_manual_document_closure_asn @complete @goh @direct_po @receiving @ds @no_ds
  Scenario: Validate manual document closure ASN
    Given I have an ASN Id of type "GOH" with delivery status as "Released"
    When I navigate to delivery management page
    And I search ASN ID to update status
    And I choose the delivery status as "Complete"
    When I navigate to delivery page
    And I enter an ASN ID
    Then the delivery status should be "Complete"
