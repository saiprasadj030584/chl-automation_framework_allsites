@picking
Feature: order allocation and picking
  As a warehouse user
  I want to pick the allocated stocks


   @yes @flatpack @picking @unique_flatpack_picking_picking_validate_whether_retail_urn_is_generated_for_tote_cage_or_pallet @complete @ds @retail @tote_check1 @pck

  Scenario: Validate whether Retail URN is generated for Tote Cage or Pallet
    Given the order id of type "Retail" with "Flatpack" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    When I navigate to order container page
    Then the urn id should be updated in order container page



  @yes @goh @picking @unique_goh_picking_picking_validate_whether_retail_urn_is_generated_for_tote_cage_or_pallet @complete @ds @retail @tote_check @pck1

  Scenario: Validate whether Retail URN is generated for Tote Cage or Pallet
    Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    When I navigate to order container page
    Then the urn id should be updated in order container page


 
   @yes @boxed @non_retail @picking  @unique_boxed_picking_picking_validate_whether_only_one_upc_is_picked_in_tote_for_the_following_order_conventry_tesam_external_reprocessing_international_franchises @complete @ds
    
  Scenario: Validate one UPC is picked
    Given the order id of type "Retail" with "Boxed" skus should be in "Released" status
    When I navigate to "system allocation" page
    And I allocate the stocks
    When I navigate to "scheduler program" page
    And I run the program
    Then Navigate to order to check order is allocated
    And I check clustering
    And I perform nonretail picking
    Then Navigate to order to check order is picked
    
   
     @yes @picking @unique_boxed_picking_picking_validate_whether_retail_order_is_picked_from_preferred_aisle_and_non_retail_order_from_normal_storage_aisle @ds
    Scenario: Validate whether Retail Order is picked from Preferred Aisle and Non Retail Order from Normal Storage Aisle
    Given I have to datsetup with preferred and non preferred location
    And I allocate the multiple stocks
    And I validate the aisle for the sku 
    And I perform picking for Hanging Retail
    When I navigate to order container page
    Then the urn id should be updated in order container page

 @yes @goh @picking @unique_goh_picking_picking_validate_the_unpick_process @complete @pck1
  Scenario: Validate the Unpick process
    Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking for Hanging Retail
    When I navigate to order container page
    Then the urn id and pallet id should be updated in order container page
    And order header should be updated for picked stock
    When I navigate to unpicking and unshipping page
    And I perform unpicking
    Then order header should be updated for unpicked stock

 @yes @flatpack @picking @unique_flatpack_picking_picking_validate_the_unpick_process @complete @pck1
  Scenario: Validate the Unpick process
    Given the order id of type "Retail" with "Flatpack" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking for Hanging Retail
    When I navigate to order container page
    Then the urn id and pallet id should be updated in order container page
    And order header should be updated for picked stock
    When I navigate to unpicking and unshipping page
    And I perform unpicking
    Then order header should be updated for unpicked stock

