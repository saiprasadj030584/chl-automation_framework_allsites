@flatpack_receiving_progress_report
Feature: Flatpack - Direct PO - Receiving progress report validation
  As a warehouse user
  I want to validate receiving progress report

  @jenkinsC @flatpack_receiving_direct_po_validate_the_receiving_progress_report @flatpack @receiving @direct_po @complete @ds @no_ds @jenkinssc
  Scenario: Validating Receiving progress report
    Given I am on report selection page
    When I choose the print to screen option
    And I search for "*receiving*" report
    And I choose M&S-Receiving summary of type "Flatpack"
    Then the receiving progress report should be generated
