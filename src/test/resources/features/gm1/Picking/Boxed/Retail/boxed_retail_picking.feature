@boxed_retail_picking
Feature: Boxed - Retail - Picking
  As a warehouse user
  I want to pick the allocated stocks

  @boxed @picking @boxed_picking_retail_validate_whether_retail_urn_is_generated_for_tote_cage_or_pallet @complete @ds @retail
  Scenario: Validate whether Retail URN is generated for Tote Cage or Pallet
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    When I navigate to order container page
    Then the urn id should be updated in order container page

  @boxed @picking @retail @boxed_picking_retail_validate_whether_urn_are_associated_to_master_urn_automatically @complete @ds
  Scenario: Validate whether URN  are associated to Master URN automatically 
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    And I perform picking
    When I navigate to order container page
    Then the urn id and pallet id should be updated in order container page

  @boxed @picking @boxed_picking_retail_validate_the_unpick_process @complete @ds @retail
  Scenario: Validate the Unpick process
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    When I navigate to order container page
    Then the urn id and pallet id should be updated in order container page
    And order header should be updated for picked stock
    When I navigate to unpicking and unshipping page
    And I perform unpicking
    Then order header should be updated for unpicked stock

  @boxed @retail @picking @boxed_picking_retail_validate_keying_wrong_upc @complete @ds @jenkinsw
  Scenario: Validate keying wrong UPC
    Given the order of "Retail" should be in "Released" status in order header maintenance
    # Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the status should be allocated for the orderID
    When I enter the invalid  UPC
    Then the error message should be displayed as invalid details

  @jenkinsA @boxed @retail @picking @boxed_picking_retail_validate_whether_boxed_location_is_made_as_pickable_preferred_location @complete @ds @no_ds
  Scenario: Validate whether Boxed location is made as Pickable Preferred Location
    Given check the loc type for the boxed preffered zones
    
    
    @boxed @picking @boxed_picking_picking_validate_whether_retail_order_is_picked_from_preferred_aisle_and_non_retail_order_from_normal_storage_aisle @complete @ds @retail
  Scenario: Validate whether Retail Order is picked from Preferred Aisle and Non Retail Order from Normal Storage Aisle
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
