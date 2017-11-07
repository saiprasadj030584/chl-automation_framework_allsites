@all_data_picking_replenishment_report
Feature: Picking and Replenishment workload report
  As a warehouse user
  I want to validate picking and replenish workload report

  @picking @all_data @all_data_picking_picking_validate_picking_and_replenishment_workload_report @complete @ds @no_ds
  Scenario: Validate Picking and Replenishment Workload  Report
    Given I am on report selection page
    When I choose the print to screen option
    And I search for "*Picking & Replen workload*" report
    And I select M&S picking and replenish workload report for "All" type
    Then the picking and replenish workload report should be generated
