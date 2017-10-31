@goh_receiving_progress_report
Feature: GOH - Direct PO - Receiving progress report validation
  As a warehouse user
  I want to validate receiving progress report

  @jenkins_analysis @goh_receiving_direct_po_validate_the_receiving_progress_report @goh @receiving @direct_po @complete @ds @no_ds
  Scenario: Validating Receiving progress report
      Given I am on report selection page
    When I choose the print to screen option
    And I search for "*receiving*" report
    And I choose M&S-Receiving summary of type "Goods on Hanger"
    Then the receiving progress report should be generated
