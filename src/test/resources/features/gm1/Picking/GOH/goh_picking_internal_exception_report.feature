@goh_picking_report
Feature: GOH - Picking - Report Generation
  As a warehouse user
  I want to generate internal exception report

  @jenkinsC @stoke @goh @picking @unique_goh_picking_picking_validate_pick_internal_exception_reports @ds @complete @no_ds
  Scenario: Validate Pick Internal Exception Reports
    Given I am on report selection page
    When I choose the print to screen option
    And I search for "*INTERNAL EXCEPTION*" report
    And I Select M&S internal exception report of type "Goods on Hanger"
    Then the internal exception report should be generated
