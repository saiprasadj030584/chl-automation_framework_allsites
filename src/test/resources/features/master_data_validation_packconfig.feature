@master_data
Feature: Master data validation
  As a warehouse user
  I want to validate master data 
  So that I can use them for PO receiving putaway and allocation

  @complete
  Scenario: Validate Pack config table in JDA WMS dispatcher for I016
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on pack config maintenance page
    When I search pack config id "20001452O01"
    Then the tag volume, volume at each details should be displayed
    When I navigate to tracking levels page
    Then the tracking levels and ratios should be displayed
    When I navigate to RDT page
    Then the RDT tracking levels 1 and 2 should be displayed
