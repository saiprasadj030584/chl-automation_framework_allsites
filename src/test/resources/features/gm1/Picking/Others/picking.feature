@picking
Feature: order allocation and picking
  As a warehouse user
  I want to pick the allocated stocks

  @flatpack @picking @flatpack_picking_picking_validate_whether_retail_urn_is_generated_for_tote_cage_or_pallet @complete @ds @retail @tote_check1
  Scenario: Validate whether Retail URN is generated for Tote Cage or Pallet
    Given the order id of type "Retail" with "Flatpack" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    When I navigate to order container page
    Then the urn id should be updated in order container page
    
    @goh @picking @goh_picking_picking_retail_urn_generated @complete @ds @retail @tote_check
  Scenario: Validate whether Retail URN is generated for Tote Cage or Pallet
    Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    When I navigate to order container page
    Then the urn id should be updated in order container page

  

  