@picking_auto_clustering
Feature: Auto clustering
  As a warehouse user
  I want to validate the auto clustering 
  So that the generated list id can be used for picking

  @boxed_picking_retail_auto_clustering @boxed @picking @retail @complete
  Scenario Outline: Validate whether auto clustering process is done after the allocation process
    Given the "<orderId>" should be in "Released" status in order header maintenance
    When I navigate to system allocation page
    And I allocate the stocks
    Then the "<orderId>" should be in "Allocated" status in order header maintenance
    And the order details should be displayed in order header
    And the order line details should be displayed
    Then the list Id should be generated in move task management

    Examples: 
      | orderId  |
      | 03041718 |
