@wish_list_quick_view
Feature: As a user
  I want to add the item in to wish list
  So that I should get item in my wish list

  @completeWishlistDemo
  Scenario: Add item into wishlist through quick view
    Given I have logged in as a user
    When I search for sshipProduct details
    Then the item should be displayed in the PLP
    When I navigate to quick view page
    Then the color, size and add to bag options should be displayed
    When I add the item into wishlist
    Then the item should be added to my wishlist
    When I remove the item from the wishlist
    Then the item should be removed from wish list
