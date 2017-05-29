@vehicle_loading
Feature: Vehicle Unloading for stock transfer order
  As a warehouse user
  I want to perform vehicle Unloading
  So that I can load the pallets in different trailer

  @wip01
  Scenario Outline: Vehicle load for stock transfer order
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And the trailer "<trailerNo>" consists of contents
    When I navigate to vehicle unloading page
    And I enter the siteId,consignment as "<consignment>" and pallet as "<pallet>"
    And I select the pallets to unload
    And I proceed to complete the process
    Then vehicle unload ITL should be generated
    Examples: 
      | trailerNo  | consignment          | pallet |
      | 6666164804 | WAVE30----1620170317 |        |
