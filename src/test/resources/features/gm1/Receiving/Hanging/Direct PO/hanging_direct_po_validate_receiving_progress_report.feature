@hanging_receiving_progress_report
Feature: Hanging - Direct PO - Receiving progress report validation
  As a warehouse user
  I want to validate receiving progress report

  @hanging_receiving_direct_po_validate_the_receiving_progress_report @hanging @receiving @direct_po @complete @ds @no_ds @jenkinssc
  Scenario: Validate the receiving progress report
    Given I am on report selection page
    When I choose the print to screen option
    And I search for "*receiving*" report
    And I choose M&S-Receiving summary as report type at site for "Hanging" type
    Then the receiving progress report should be generated
