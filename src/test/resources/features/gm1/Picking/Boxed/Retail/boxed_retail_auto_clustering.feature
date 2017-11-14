@boxed_picking_auto_clustering
Feature: Boxed - Picking - Retail - Auto clustering
  As a warehouse user
  I want to validate the auto clustering 
  So that the generated list id can be used for picking

   @jenkinsA @boxed_picking_retail_validate_whether_auto_clustering_process_is_done_after_the_allocation_process @boxed @picking @complete @retail @ds
  Scenario: Validate whether auto clustering process is done after the allocation process
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the order should be in "Allocated" status in order header maintenance
    And the order details should be displayed in order header
    And the order line details should be displayed
    Then the list Id should be generated in move task management
