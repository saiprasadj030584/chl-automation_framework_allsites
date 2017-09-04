@picking
Feature: Picking
  As a warehouse user
  I want to receive the returned articles

  @boxed_picking_picking_validate_multi_part_set_warning @picking @picking @boxed @complete
  Scenario Outline: Validate Multi Part setWarning is displayed when picking the part set product- Boxed
    Given the "<OrderId>" should be in "Released" status in order header maintenance
    When I navigate to system allocation page
    And I allocate the stocks
    Then the "<OrderId>" should be in "Allocated" status in order header maintenance
    When I proceed with picking
    Then the part set warning should be displayed
    And the picking should be completed

    Examples: 
      | OrderId |
      | 0603865 |

  @boxed_picking_picking_validate_multi_part_set_instruction @picking @picking @boxed @complete
  Scenario Outline: Validate Multi Part set Instruction is displayed when picking the part set product- Boxed
    Given the "<OrderId>" should be in "Released" status in order header maintenance
    When I navigate to system allocation page
    And I allocate the stocks
    Then the "<OrderId>" should be in "Allocated" status in order header maintenance
    When I proceed with picking to validate multi part set instruction
    And the picking should be completed

    Examples: 
      | OrderId |
      | 0603485 |
