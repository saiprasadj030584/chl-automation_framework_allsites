@boxed_sortation_report_generation_repacking
Feature: Boxed - Sortation - Report generation for repacking
  As a warehouse user
  I want to generate repacking report generation

  @jenkinsD @boxed @sortation @unique_boxed_sortation_sortation_validate_whether_report_is_generated_for_repacking @complete @no_ds @ds
  Scenario: Validate whether report is generated for Repacking
    Given I am on report selection page
    When I choose the print to screen option
    And I search for "*REPACK*" report
    And I Select M&S Repack report
    Then the repacking report should be generated
