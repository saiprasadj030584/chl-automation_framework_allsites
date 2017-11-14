@flatpack_picking_auto_clustering
Feature: Flatpack - Picking - Retail - Auto clustering
  As a warehouse user
  I want to validate the auto clustering 
  So that the generated list id can be used for picking

  @flatpack_picking_retail_validate_whether_auto_clustering_process_is_done_after_the_allocation_process @jenkinsac @flatpack @picking @retail @ds @complete
  Scenario: Validate whether auto clustering process is done after the allocation process
    Given the order of "Retail" should be in "Released" status in order header maintenance
    When I navigate to system allocation page
    And I allocate the stocks
    Then the order should be in "Allocated" status in order header maintenance
    And the order details should be displayed in order header
    And the order line details should be displayed
    Then the list Id should be generated in move task management
