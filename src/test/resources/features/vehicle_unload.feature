@vehicle_unloading
Feature: Vehicle unloading for stock transfer order
  As a warehouse user
  I want to perform vehicle unloading
  So that I can load the pallets in different trailer

  @wip01
  Scenario: Vehicle unload for stock transfer order
    Given the vehicle loading has been done for order "365241"
    When I navigate to vehicle unloading page
    And I enter the siteId,consignment and pallet
    And I select the pallet to unload
    And I proceed to complete the process
    Then the vehicle unloading should be updated in the inventory transaction
