@inventory_report_generation
Feature: Report generation
  As a warehouse user
  I want to generate the report for the stock in inventory

  @stock_report_generation @boxed @inventory
  Scenario Outline: Validate whether report is generated based on Stock Accuracy by Location
    Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to report selection page
    And I select print to screen and I search for the stock
    And I enter the siteID "<SiteId>"
    Then the report should be generated for stock in inventory

    Examples: 
      | SiteId |
      |   5649 |
