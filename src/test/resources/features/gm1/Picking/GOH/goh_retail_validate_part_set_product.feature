@goh_picking_retail_validate_part_set
Feature: GOH - Retail - Validate Part Set products
  As a warehouse user
  I want to receive the returned articles

  @unique_goh_picking_picking_validate_multi_part_setwarning_is_displayed_when_picking_the_part_set_product @picking @boxed @complete @retail @ds
  Scenario: Validate Multi Part setWarning is displayed when picking the part set product
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the order should be in "Allocated" status in order header maintenance
    When I perform picking for "GOH"
    Then the part set warning should be displayed
    And the picking should be completed
    
  @unique_goh_picking_picking_validate_multi_part_set_instructions_is_displayed_when_picking_the_part_set_product @picking @boxed @complete @retail @ds
  Scenario: Validate Multi Part set Instruction is displayed when picking the part set product
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the order should be in "Allocated" status in order header maintenance
    When I perform picking for "GOH"
    When I proceed with picking to validate multi part set instruction
    And the picking should be completed
