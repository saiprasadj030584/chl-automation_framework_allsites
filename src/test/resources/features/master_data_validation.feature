@master_data
Feature: Master data validation
  As a warehouse user
  I want to validate master data 
  So that I can use them for PO receiving putaway and allocation

  @complete
  Scenario: Validate Supplier SKU table in JDA WMS dispatcher for I016
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on supplier SKU maintenance page
    When I search SKU id "20001265" and supplier "F02007"
    Then the description, supplier SKU details should be displayed for the given SKU id

  @complete
  Scenario: Validate Supplier SKU table in JDA WMS dispatcher when no records are found
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on supplier SKU maintenance page
    When I search SKU id "20001265" and supplier "D02007"
    Then No records should be displayed on Supplier SKU maintenance page

  @sara
  Scenario: Validate kit line table in JDA WMS dispatcher for BOM Article
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on kit line maintenance page
    When I search with SKU id "20001265"
    Then the kit line details should be displayed for the given SKU id
