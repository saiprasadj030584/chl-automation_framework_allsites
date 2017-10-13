@picking
Feature: Boxed - Retail - Validae Part Set products
  As a warehouse user
  I want to receive the returned articles

  @boxed_picking_retail_validate_multi_part_set_warning @picking @boxed @complete @retail @ds
  Scenario: Validate Multi Part setWarning is displayed when picking the part set product- Boxed
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the order should be in "Allocated" status in order header maintenance
    When I proceed with picking
    Then the part set warning should be displayed
    And the picking should be completed

  @boxed_picking_retail_validate_multi_part_set_instruction @picking @boxed @complete @retail @ds
  Scenario: Validate Multi Part set Instruction is displayed when picking the part set product- Boxed
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the order should be in "Allocated" status in order header maintenance
    When I proceed with picking to validate multi part set instruction
    And the picking should be completed
