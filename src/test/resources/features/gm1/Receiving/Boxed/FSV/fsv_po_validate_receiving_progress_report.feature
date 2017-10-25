@receiving_progress_report
Feature: Receiving progress report validation
  As a warehouse user
  I want to validate receiving progress report

  @boxed_receiving_fsv_po_validate_the_receiving_progress_report @boxed @receiving @fsv_po @complete @ds @boxed_jenkins
  Scenario Outline: Validating Receiving progress report
    Given I am on report selection page
    When I choose the print to screen option
    And I search for "*receiving*" report
    And I choose M&S-Receiving summary as report type at site "<siteId>" for "Boxed" type
    Then the receiving progress report should be generated

    Examples: 
      | siteId |
      |   5649 |
