@inventory_report_generation
Feature: Report generation
  As a warehouse user
  I want to generate the report in inventory

  @stock_report_generation
  Scenario: report generation feature
  # Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to report selection page
    And I select the print to screen and I search for the stock
    And I enter the siteID "5649"
    Then the report should be generated
