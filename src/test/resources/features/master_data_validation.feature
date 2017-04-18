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

  Scenario: Validate whether Article data is successfully loaded into SKU table
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to SKU maintenance page
    And I search for the SKU id "20001249"
    Then the SKU id and SKU description fields should be displayed
    And the product group, EAN, UPC, allocation group, each quantity, tag merge fields should be displayed in settings1 tab
    When I navigate to setting4 tab
    Then the new product field should be displayed
    When I navigate to customs & excise tab
    Then the C&E warehouse type, C&E VAT code, C&E SKU, C&E alcoholic strength fields should be displayed
    When I navigate to linking tab
    Then the site id should be displayed
    When I navigate to batch & expiry tab
    Then the expiry required should be displayed
    When I navigate to user defined tab
    Then the base UOM, SAP creation status should be displayed
    When I navigate to supplier SKU tab
    Then the supplier SKU id should be displayed
