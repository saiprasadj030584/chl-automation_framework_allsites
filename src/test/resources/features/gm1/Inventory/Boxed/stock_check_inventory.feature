@inventory_stock_check
Feature: Report generation
  As a warehouse user
  I want to validate stock in inventory

    @boxed  @boxed_inventory_inventory_validate_whether_quantity_field_in_stock_check_screen_is_restricted_restrict_quantity_value @inventory @complete @ds
    Scenario: Validate quantity field in the stock check screen to be restricted so that there is no risk of entering a UPC as a quantity
    Given I have to datasetup for restrict quantity
    And I have to check restrict quantity
    
    @hanging  @hanging_inventory_inventory_validate_whether_quantity_field_in_stock_check_screen_is_restricted_restrict_quantity_value @inventory @complete @ds
    Scenario: Validate quantity field in the stock check screen to be restricted so that there is no risk of entering a UPC as a quantity
    
    Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to stock check list generation page
    And I select 'Generate by inventory'
    And I enter the tag ID as on inventory tab for site id 
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    And I proceed to next tab
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
  #  When I navigate to stock check query page
    And I get the list id
    And I have to datasetup for restrict quantity
    And I have to check restrict quantity

    @flatpack  @flatpack_inventory_inventory_validate_whether_quantity_field_in_stock_check_screen_is_restricted_restrict_quantity_value @inventory @complete @ds
    Scenario: Validate quantity field in the stock check screen to be restricted so that there is no risk of entering a UPC as a quantity
    Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to stock check list generation page
    And I select 'Generate by inventory'
    And I enter the tag ID as on inventory tab for site id 
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    And I proceed to next tab
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
  #  When I navigate to stock check query page
    And I get the list id
    And I have to datasetup for restrict quantity
    And I have to check restrict quantity
    
  @goh @goh_inventory_inventory_validate_whether_quantity_field_in_stock_check_screen_is_restricted_restrict_quantity_value @inventory @complete @ds
    Scenario: Validate quantity field in the stock check screen to be restricted so that there is no risk of entering a UPC as a quantity
   Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to stock check list generation page
    And I select 'Generate by inventory'
    And I enter the tag ID as on inventory tab for site id 
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    And I proceed to next tab
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
  #  When I navigate to stock check query page
    And I get the list id
    And I have to datasetup for restrict quantity
    And I have to check restrict quantity
    
    
    @hanging @hanging_picking_picking_remove_stock_check_putaway_picking_relocate_&_replenishment_v2 @inventory @complete @ds
    Scenario: Validate quantity field in the stock check screen to be restricted so that there is no risk of entering a UPC as a quantity
  
    #Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    When I navigate to scheduler program page
    And I run the program
    And I perform picking
    When I navigate to order container page
    Then the urn id should be updated in order container page
   
   
    When I navigate to stock check list generation page
    And I select 'Generate by inventory'
    And I enter the tag ID as on inventory tab for site id 
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    And I proceed to next tab
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
  #  When I navigate to stock check query page
    And I get the list id
    And I have to datasetup for restrict quantity
    And I have to check restrict quantity
    
    