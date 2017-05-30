@vehicle_loading
Feature: Vehicle Unloading for stock transfer order
  As a warehouse user
  I want to perform vehicle Unloading
  So that I can load the pallets in different trailer

  @complete
  Scenario Outline: Vehicle unload for stock transfer order
    Given I have logged in as warehouse user in JDA dispatcher food application
    And the trailer "<trailerNo>" consists of contents
    When I navigate to vehicle unloading page
    And I enter the siteId,consignment as "<consignment>" and pallet as "<pallet>"
    And I select the pallet to unload
    And I proceed to complete the process
    Then vehicle unload ITL should be generated

    Examples: 
      | trailerNo  | consignment          | pallet            |
      | 4323       | WAVE30----1620170317 | 8004370150589     |
