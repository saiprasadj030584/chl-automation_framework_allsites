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

  @complete
  Scenario: Validate kit line table in JDA WMS dispatcher for BOM Article
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on kit line maintenance page
    When I search with SKU id "20001590"
    Then the kit line details should be displayed for the given SKU id

  @complete
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

  @complete @address_table
  Scenario: Load the site details
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to address maintenance page
    And I search the address id "0010"
    Then the address type, name, address line 1 and country should be displayed
    When I navigate to customs & excise tab	in address maintenance
    Then the CE & warehouse type should be displayed
    When I navigate to user defined tab in address maintenance
    Then I should see the is site flag is updated as site
    And the site category should be displayed

  @complete @address_table 
  Scenario: Load the vendor details
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to address maintenance page
    And I search the address id "F01502"
    Then the address type, name, address line 1 and country should be displayed
    When I navigate to customs & excise tab	in address maintenance
    Then the CE & warehouse type should be displayed
    When I navigate to user defined tab in address maintenance
    Then I should see the is site flag is updated as vendor
