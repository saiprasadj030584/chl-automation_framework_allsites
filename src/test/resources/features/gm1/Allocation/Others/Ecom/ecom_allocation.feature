@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks


      
      @allocation @e_com @goh @goh_allocation_e_com_allocation_rules @complete
  Scenario: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id of type "Ecom" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

      
      