@all_data_direct_po_receiving
Feature: All Data - Receiving
  As a warehouse user
  I want to validate receiving

  @jenkinsC @unique_all_data_receiving_receiving_validate_stock_unavailable_report @all_data @receiving @complete @ds @no_ds @jenkinssc
  Scenario: Validate stock unavailable report
    Given I am on report selection page
    When I choose the print to screen option
    And I search for "*Proactive Allocation Shortage*" report
    And I select M&S proactive allocation shortage report
    Then the proactive allocation shortage report should be generated
